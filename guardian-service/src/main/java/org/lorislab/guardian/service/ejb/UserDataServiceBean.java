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

import java.util.logging.Logger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.guardian.api.model.UserPermission;
import org.lorislab.guardian.api.service.UserDataService;
import org.lorislab.guardian.app.ejb.RoleService;
import org.lorislab.guardian.app.model.Permission;
import org.lorislab.guardian.app.model.Role;
import org.lorislab.guardian.user.ejb.UserPasswordService;
import org.lorislab.guardian.user.ejb.UserService;
import org.lorislab.guardian.user.ejb.UserProfileService;
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
    public boolean setUserPassword(String principal, String newPassword) throws Exception {
        boolean result = false;
        User user = userService.getUserByPrincipal(principal);
        if (user != null) {
            UserPassword up = userPasswordService.getUserPasswordByUser(user.getGuid());
            if (up != null) {
                    up.setPassword(newPassword);
            } else {
                up = new UserPassword();
                up.setUser(user);
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
                up.setUser(user);
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
