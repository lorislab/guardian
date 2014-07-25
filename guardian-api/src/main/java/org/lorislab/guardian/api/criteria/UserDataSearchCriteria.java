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

package org.lorislab.guardian.api.criteria;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Andrej Petras
 */
public class UserDataSearchCriteria implements Serializable {
    
    private static final long serialVersionUID = 4958264089912805080L;

    private String guid;
    
    private Set<String> guids;
    
    private String principal;
    
    private Boolean enabled;
    
    private Boolean deleted;

    private boolean fetchConfig;
    
    public Set<String> getGuids() {
        return guids;
    }

    public void setGuids(Set<String> guids) {
        this.guids = guids;
    }
    
    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public boolean isFetchConfig() {
        return fetchConfig;
    }

    public void setFetchConfig(boolean fetchConfig) {
        this.fetchConfig = fetchConfig;
    }
    
}
