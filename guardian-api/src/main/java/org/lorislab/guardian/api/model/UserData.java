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

package org.lorislab.guardian.api.model;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Andrej Petras
 */
public abstract class UserData<P, C extends UserDataConfig> implements Serializable {
    
    private static final long serialVersionUID = -5900995309959406528L;
    
    private String principal;    
    
    private C config;

    private P user;
        
    private Set<String> roles;
    
    private Set<String> actions;
    
    public UserData(String principal, Set<String> roles, Set<String> actions) {
        this.principal = principal;
        this.roles = roles;
        this.actions = actions;
    }
                
    public String getPrincipal() {
        return principal;
    }

    public P getUser() {
        return user;
    }

    public void setUser(P user) {
        this.user = user;
    }
  
    public void setConfig(C config) {
        this.config = config;
    }
    
    public C getConfig() {
        return config;
    }
            
    public boolean hasRole(String role) {
        if (roles != null) {
            return roles.contains(role);
        }
        return false;
    }

    public boolean hasAction(Enum context) {
        return hasAction(context, null);
    }

    public boolean hasAction(Enum context, Enum action) {
        if (actions != null && context != null) {
            String tmp = null;
            if (action != null) {
                tmp = action.name();
            }
            return actions.contains(context.name() + tmp);
        }
        return false;
    }   
}
