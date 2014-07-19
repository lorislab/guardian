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
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.guardian.api.factory.ServiceFactory;
import org.lorislab.guardian.api.model.UserData;
import org.lorislab.guardian.api.service.ApplicationDataService;
import org.lorislab.guardian.api.service.UserDataService;
import org.lorislab.guardian.app.ejb.RoleService;
import org.lorislab.guardian.app.model.Permission;
import org.lorislab.guardian.app.model.Role;
import org.lorislab.guardian.service.model.DefaultProfileData;
import org.lorislab.guardian.service.model.DefaultUserData;
import org.lorislab.guardian.user.ejb.UserService;
import org.lorislab.guardian.user.model.User;
import org.lorislab.guardian.user.model.UserMember;
import org.lorislab.guardian.user.model.UserProfile;

/**
 * The default user service implementation.
 * 
 * @author Andrej Petras
 */
@Stateless
@Local(UserDataService.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class DefaultUserService implements UserDataService {

    @EJB
    private UserService userService;
    
    @EJB
    private RoleService roleService;
    
    @Override
    public UserData getUserData(String principal, Class clazz, String organization) throws Exception {
        DefaultUserData result = null;
        
        User user = userService.getFullUser(principal);
        if (user != null) {
            
            if (user.isDeleted()) {
                throw new Exception("User [" + principal + "] was deleted!");
            }
            if (!user.isEnabled()) {
                throw new Exception("User [" + principal + "] is not enabled for the application!");
            }
                      
            String org = organization;
            
            // load profile
            DefaultProfileData profileData = null;
            UserProfile profile = user.getProfile();
            if (profile != null) {
                profileData = new DefaultProfileData();
                profileData.setEmail(profile.getEmail());
                profileData.setFirstName(profile.getFirstName());
                profileData.setLastName(profile.getLastName());
                profileData.setLocale(profile.getLocale());
                profileData.setMiddleName(profile.getMiddleName());
                profileData.setOrganization(profile.getOrganization());
                
                if (org == null) {
                    org = profile.getOrganization();
                }
            }
            
            // load configuration
            Object config = null;
            
            
            // laod roles and actions
            Set<String> roles = new HashSet<>();
            Set<String> actions = new HashSet<>();
            
            Set<UserMember> members = user.getMembers();
            if (members != null) {
                
                UserMember member = null;
                
                // find member
                Iterator<UserMember> iter = members.iterator();
                while (iter.hasNext() && member == null) {
                    UserMember tmp = iter.next();
                    if (tmp.getOrganization() == null && org == null
                            || tmp.getOrganization().equals(org)) {
                        member = tmp;                        
                    }
                    
                }
                               
                
                if (member != null) {
                    
                    // load user roles for the application
                    Set<String> userGroups = member.getGroups();
                    Set<String> userRoles = member.getRoles();
                    
                    ApplicationDataService appService = ServiceFactory.getApplicationDataService();
                    if (appService == null) {
                        throw new Exception("Missing application data service implementation!");
                    }
                    
                    List<Role> tmp = roleService.getRolesForUser(appService.getApplication(), userGroups, userRoles);
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
            
            }
            
            result = new DefaultUserData(principal, profileData, config, roles, actions);
        }
        return result;
    }

    @Override
    public UserData getUserData(String principal, Class clazz) throws Exception {
        return getUserData(principal, clazz, null);
    }
    
}
