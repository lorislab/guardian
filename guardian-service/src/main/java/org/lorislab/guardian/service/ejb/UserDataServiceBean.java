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
import org.lorislab.guardian.user.ejb.UserService;

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
