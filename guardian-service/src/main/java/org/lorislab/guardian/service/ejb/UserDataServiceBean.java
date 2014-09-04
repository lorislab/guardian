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
import org.lorislab.guardian.api.service.UserService;
import org.lorislab.guardian.app.ejb.RoleService;
import org.lorislab.guardian.app.model.Permission;
import org.lorislab.guardian.app.model.Role;

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

    @EJB
    private UserService userService;

    @EJB
    private RoleService roleService;

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
                result.setUserProfile(profile);

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
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserData saveUserData(UserData profile) throws Exception {
        if (profile != null) {
            if (profile.getUserProfile() != null) {
                UserDataProfile tmp = userProfileService.saveProfile(profile.getUserProfile());

                UserDataConfig config = configService.saveUserConfig(profile.getConfig());

                UserMetaData metadata = userMetaDataService.saveUserMetaData(profile.getMetadata());

                profile.setUserProfile(tmp);
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
                    item.setUserProfile(profile);
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
            Set<String> actions = new HashSet<>();

            List<Role> tmp = roleService.getRolesForUser(userRoles);

            if (tmp != null) {
                for (Role role : tmp) {

                    Set<Permission> permissions = role.getPermissions();
                    if (permissions != null) {
                        for (Permission perm : permissions) {
                            if (perm.isEnabled()) {
                                actions.add(perm.getContext() + perm.getAction());
                            }
                        }
                    }
                }
            }

            result = new UserPermission(actions);
        }
        return result;
    }
}
