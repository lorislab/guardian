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
 * @param <C>
 */
public abstract class UserData<C> implements Serializable {
    
    private static final long serialVersionUID = -5900995309959406528L;
    
    private String principal;    
    
    private C config;

    private ProfileData profile;
        
    private Set<String> roles;
    
    private Set<String> actions;
        
    public String getPrincipal() {
        return principal;
    }
        
    public ProfileData getProfile() {
        return profile;
    }

    public C getConfig() {
        return config;
    }

    public void setConfig(C config) {
        this.config = config;
    }
            
    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    public boolean hasAction(String action) {
        return hasAction(action, null);
    }

    public boolean hasAction(String action, String context) {
        return actions.contains(action + context);
    }
    
    public void addRole(String role) {
        roles.add(role);
    }
    
    public void addAction(String action, String context) {
        actions.add(action + context);
    }
}
