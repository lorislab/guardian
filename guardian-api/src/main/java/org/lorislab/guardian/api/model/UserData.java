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
 *
 * @author Andrej Petras
 */
public class UserData implements Serializable {
    
    private static final long serialVersionUID = -1383569625147350985L;

    private final String principal;

    private UserDataConfig config;

    private UserMetaData metadata;
    
    private UserDataProfile profile;

    public UserData() {
        this(null);
    }
    
    public UserData(String principal) {
        this.principal = principal;
    }

    public UserMetaData getMetadata() {
        return metadata;
    }

    public void setMetadata(UserMetaData metadata) {
        this.metadata = metadata;
    }
    
    public String getPrincipal() {
        return principal;
    }

    public UserDataProfile getProfile() {
        return profile;
    }

    public void setProfile(UserDataProfile profile) {
        this.profile = profile;
    }

    public void setConfig(UserDataConfig config) {
        this.config = config;
    }

    public UserDataConfig getConfig() {
        return config;
    }

}
