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
import org.lorislab.guardian.api.criteria.UserDataSearchCriteria;
import org.lorislab.guardian.api.factory.ServiceFactory;
import org.lorislab.guardian.api.model.UserData;
import org.lorislab.guardian.api.model.UserDataConfig;
import org.lorislab.guardian.api.service.ApplicationDataService;
import org.lorislab.guardian.api.service.UserConfigService;
import org.lorislab.guardian.api.service.UserDataFactoryService;
import org.lorislab.guardian.api.service.UserDataService;
import org.lorislab.guardian.app.ejb.RoleService;
import org.lorislab.guardian.app.model.Permission;
import org.lorislab.guardian.app.model.Role;
import org.lorislab.guardian.service.model.AbstractUserData;
import org.lorislab.guardian.user.criteria.UserSearchCriteria;
import org.lorislab.guardian.user.ejb.UserService;
import org.lorislab.guardian.user.model.User;

/**
 * The default user service implementation.
 *
 * @author Andrej Petras
 */
public abstract class AbstractUserDataService<U extends AbstractUserData, C extends UserDataConfig> implements UserDataService<U> {

    @EJB
    private UserService userService;

    @EJB
    private RoleService roleService;

    @EJB
    private UserConfigService<C> configService;

    @Override
    public List<U> findUserData(UserDataSearchCriteria criteria) throws Exception {
        List<U> result = new ArrayList<>();

        UserSearchCriteria userCriteria = new UserSearchCriteria();
        userCriteria.setFetchProfile(true);
        userCriteria.setDeleted(criteria.isDeleted());
        userCriteria.setEnabled(criteria.isEnabled());
        userCriteria.setGuid(criteria.getGuid());
        userCriteria.setGuids(criteria.getGuids());
        userCriteria.setPrincipal(criteria.getPrincipal());        
        List<User> users = userService.getUsers(userCriteria);
        
        
        if (users != null) {

            UserDataFactoryService<U> factory = ServiceFactory.getUserDataFactoryService();
            
            Map<String, U> tmp = new HashMap<>();
            for (User user : users) {
                U item = factory.createUserData(user.getPrincipal());
                item.setUser(user);
                tmp.put(user.getGuid(), item);
                result.add(item);
            }
                
            if (criteria.isFetchConfig()) {
                List<C> configs = configService.getUserConfigs(tmp.keySet());
                if (configs != null) {
                    for (C config : configs) {
                        U user = tmp.get(config.getUser());
                        if (user != null) {                            
                            user.setConfig(config);
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public U getUserData(String principal, Class clazz) throws Exception {
        U result = null;

        User user = userService.getFullUser(principal);
        if (user != null) {

            if (!user.isEnabled()) {
                throw new Exception("User [" + principal + "] is not enabled for the application!");
            }

            // load configuration
            C config = configService.getUserConfig(user.getGuid());

            // laod roles and actions
            Set<String> roles = new HashSet<>();
            Set<String> actions = new HashSet<>();

            Set<String> userRoles = user.getRoles();

            if (userRoles != null) {

                ApplicationDataService appService = ServiceFactory.getApplicationDataService();
                if (appService == null) {
                    throw new Exception("Missing application data service implementation!");
                }

                List<Role> tmp = roleService.getRolesForUser(appService.getApplication(), userRoles);

                if (tmp != null) {
                    for (Role role : tmp) {
                        roles.add(role.getName());

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

            }

            UserDataFactoryService<U> factory = ServiceFactory.getUserDataFactoryService();
            result = factory.createUserData(principal, roles, actions);
            result.setConfig(config);
            result.setUser(user);
        }
        return result;
    }

}
