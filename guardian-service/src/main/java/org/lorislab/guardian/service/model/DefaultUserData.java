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

package org.lorislab.guardian.service.model;

import java.util.Set;
import org.lorislab.guardian.api.model.ProfileData;
import org.lorislab.guardian.api.model.UserData;

/**
 *
 * @author Andrej Petras
 */
public class DefaultUserData implements UserData {
    
    private static final long serialVersionUID = -5900995309959406528L;
    
    private String principal;    
    
    private Object config;

    private ProfileData profile;
        
    private Set<String> roles;
    
    private Set<String> actions;
        
    public DefaultUserData(String principal, ProfileData profile, Object config, Set<String> roles, Set<String> actions) {
        this.principal = principal;
        this.config = config;
        this.profile = profile;
        this.roles = roles;
        this.actions = actions;
    }
    
    @Override
    public String getPrincipal() {
        return principal;
    }
        
    @Override
    public ProfileData getProfile() {
        return profile;
    }

    @Override
    public Object getConfig() {
        return config;
    }
            
    @Override
    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    @Override
    public boolean hasAction(String action) {
        return hasAction(action, null);
    }

    @Override
    public boolean hasAction(String action, String context) {
        return actions.contains(action + context);
    }   
}
