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

/**
 * The user data model.
 *
 * @author Andrej Petras
 */
public class UserData implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1383569625147350985L;

    /**
     * The principal.
     */
    private final String principal;

    /**
     * The enabled flag.
     */
    private boolean enabled;
    
    /**
     * The user configuration.
     */
    private UserDataConfig config;

    /**
     * The user profile.
     */
    private UserDataProfile profile;

    /**
     * The default constructor.
     */
    public UserData() {
        this(null);
    }
    
    /**
     * The default constructor.
     *
     * @param principal the user principal.
     */
    public UserData(String principal) {
        this.principal = principal;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    /**
     * Gets the principal.
     *
     * @return the principal.
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * Gets the user data profile.
     *
     * @return the user data profile.
     */
    public UserDataProfile getProfile() {
        return profile;
    }

    /**
     * Sets the profile.
     *
     * @param profile the profile.
     */
    public void setProfile(UserDataProfile profile) {
        this.profile = profile;
    }

    /**
     * Sets the user configuration.
     *
     * @param config the user configuration.
     */
    public void setConfig(UserDataConfig config) {
        this.config = config;
    }

    /**
     * Gets user configuration.
     *
     * @return user configuration.
     */
    public UserDataConfig getConfig() {
        return config;
    }

}
