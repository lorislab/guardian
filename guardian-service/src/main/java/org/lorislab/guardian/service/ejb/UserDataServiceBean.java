/*
 * Copyright 2014 Andrej Petras.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lorislab.guardian.service.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.guardian.api.criteria.UserDataCriteria;
import org.lorislab.guardian.api.model.UserDataProfile;
import org.lorislab.guardian.api.model.UserDataConfig;
import org.lorislab.guardian.api.model.UserData;
import org.lorislab.guardian.api.model.UserMetaData;
import org.lorislab.guardian.api.model.UserPermission;
import org.lorislab.guardian.api.service.UserDataProfileService;
import org.lorislab.guardian.api.service.UserConfigService;
import org.lorislab.guardian.api.service.UserDataService;
import org.lorislab.guardian.api.service.UserMetaDataService;
import org.lorislab.guardian.app.ejb.RoleService;
import org.lorislab.guardian.app.model.Permission;
import org.lorislab.guardian.app.model.Role;
import org.lorislab.guardian.user.ejb.UserPasswordService;
import org.lorislab.guardian.user.ejb.UserService;
import org.lorislab.guardian.user.model.User;
import org.lorislab.guardian.user.model.UserPassword;

/**
 * The default user data profile service.
 *
 * @author Andrej Petras
 */
@Stateless
@Local(UserDataService.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserDataServiceBean implements UserDataService {

    /**
     * The profile service.
     */
    @EJB
    private UserDataProfileService userProfileService;

    /**
     * The user configuration service.
     */
    @EJB
    private UserConfigService configService;

    /**
     * The user meta data service.
     */
    @EJB
    private UserMetaDataService userMetaDataService;

    /**
     * The user service.
     */
    @EJB
    private UserService userService;

    /**
     * The role service.
     */
    @EJB
    private RoleService roleService;

    /**
     * The user password service.
     */
    @EJB
    private UserPasswordService userPasswordService;

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean changeUserPassword(String principal, String oldPassword, String newPassword) throws Exception {
        boolean result = false;
        User user = userService.getUserByPrincipal(principal);
        if (user != null) {
            UserPassword up = userPasswordService.getUserPasswordByUser(user.getGuid());
            if (up != null) {
                if ((up.getPassword() != null && up.getPassword().equals(oldPassword))
                        || (up.getPassword() == null && oldPassword == null)) {
                    up.setPassword(newPassword);
                } else {
                    up = null;
                }

            } else {
                up = new UserPassword();
                up.setUser(user.getGuid());
                up.setPassword(newPassword);
            }

            if (up != null) {
                userPasswordService.saveUserPassword(up);
                result = true;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean deleteUserPassword(String principal) throws Exception {
        boolean result = false;
        User user = userService.getUserByPrincipal(principal);
        if (user != null) {
            UserPassword up = userPasswordService.getUserPasswordByUser(user.getGuid());
            if (up != null) {
                result = userPasswordService.deleteUserPassword(up.getGuid());
            }
        }
        return result;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public UserData loadUserData(String principal) throws Exception {
        UserData result = null;
        if (principal != null) {
            // load user profile
            UserDataProfile profile = userProfileService.getProfileByPrincipal(principal);
            if (profile != null) {
                result = new UserData(principal);
                result.setProfile(profile);

                String userId = profile.getUserGuid();
                // load user configuration
                UserDataConfig config = configService.getUserConfig(userId);
                result.setConfig(config);
                // load metadata
                UserMetaData metadata = userMetaDataService.loadUserMetaData(userId);
                result.setMetadata(metadata);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public UserData saveUserData(UserData profile) throws Exception {
        if (profile != null) {
            if (profile.getProfile() != null) {
                UserDataProfile tmp = userProfileService.saveProfile(profile.getProfile());

                UserDataConfig config = configService.saveUserConfig(profile.getConfig());

                UserMetaData metadata = userMetaDataService.saveUserMetaData(profile.getMetadata());

                profile.setProfile(tmp);
                profile.setConfig(config);
                profile.setMetadata(metadata);
            }
        }
        return profile;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<UserData> findUserDataByCriteria(UserDataCriteria criteria) throws Exception {
        List<UserData> result = null;
        if (criteria != null && criteria.getUsers() != null && !criteria.getUsers().isEmpty()) {

            // load user profiles
            List<? extends UserDataProfile> profiles = userProfileService.getProfiles(criteria.getUsers());
            if (profiles != null) {
                Map<String, UserData> tmp = new HashMap<>();
                result = new ArrayList<>(profiles.size());

                for (UserDataProfile profile : profiles) {
                    UserData item = new UserData();
                    item.setProfile(profile);
                    tmp.put(profile.getUserGuid(), item);
                    result.add(item);
                }

                // load user configuration
                if (criteria.isFetchConfig()) {
                    List<? extends UserDataConfig> configs = configService.getUserConfigs(criteria.getUsers());
                    if (configs != null) {
                        for (UserDataConfig config : configs) {
                            UserData data = tmp.get(config.getUser());
                            if (data != null) {
                                data.setConfig(config);
                            }
                        }
                    }
                }

                // load meta data
                if (criteria.isFetchMetadata()) {
                    List<? extends UserMetaData> metas = userMetaDataService.getUserMetaDatas(criteria.getUsers());
                    if (metas != null) {
                        for (UserMetaData meta : metas) {
                            UserData data = tmp.get(meta.getUser());
                            if (data != null) {
                                data.setMetadata(meta);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public UserPermission getUserPermission(String principal) throws Exception {
        UserPermission result = null;

        Set<String> userRoles = userService.getUserRoles(principal);
        if (userRoles != null) {

            // load roles and actions
            Set<String> permissions = new HashSet<>();

            List<Role> tmp = roleService.getRolesForUser(userRoles);

            if (tmp != null) {
                for (Role role : tmp) {

                    Set<Permission> items = role.getPermissions();
                    if (items != null) {
                        for (Permission perm : items) {
                            if (perm.isEnabled()) {
                                permissions.add(perm.getContext() + perm.getAction());
                            }
                        }
                    }
                }
            }

            result = new UserPermission(permissions);
        }
        return result;
    }
}
