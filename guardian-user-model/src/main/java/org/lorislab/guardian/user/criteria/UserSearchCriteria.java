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

package org.lorislab.guardian.user.criteria;

import org.lorislab.jel.base.criteria.AbstractSearchCriteria;

/**
 * The user search criteria.
 * 
 * @author Andrej Petras
 */
public class UserSearchCriteria extends AbstractSearchCriteria {
    
    private static final long serialVersionUID = 1444214779226441960L;

    private String guid;
    
    private String principal;
    
    private boolean fetchMember;
    
    private boolean fetchParameter;
    
    private boolean fetchProfile;
    
    @Override
    public void reset() {
        guid = null;
        principal = null;
        fetchMember = false;
        fetchParameter = false;
        fetchProfile = false;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty(guid, principal);
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

    public boolean isFetchMember() {
        return fetchMember;
    }

    public void setFetchMember(boolean fetchMember) {
        this.fetchMember = fetchMember;
    }

    public boolean isFetchParameter() {
        return fetchParameter;
    }

    public void setFetchParameter(boolean fetchParameter) {
        this.fetchParameter = fetchParameter;
    }

    public boolean isFetchProfile() {
        return fetchProfile;
    }

    public void setFetchProfile(boolean fetchProfile) {
        this.fetchProfile = fetchProfile;
    }
        
}