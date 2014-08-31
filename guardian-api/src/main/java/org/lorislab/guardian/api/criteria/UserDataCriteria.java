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
 * User data profile criteria.
 *
 * @author Andrej Petras
 */
public class UserDataCriteria implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -6265743933935808634L;

    /**
     * The set of users
     */
    private Set<String> users;

    /**
     * The fetch meta data flag.
     */
    private boolean fetchMetadata;

    /**
     * The fetch configuration flag.
     */
    private boolean fetchConfig;

    /**
     * Gets the users.
     *
     * @return the users.
     */
    public Set<String> getUsers() {
        return users;
    }

    /**
     * Sets the users.
     *
     * @param users the users.
     */
    public void setUsers(Set<String> users) {
        this.users = users;
    }

    /**
     * Gets the fetch configuration flag.
     *
     * @return the fetch configuration flag.
     */
    public boolean isFetchConfig() {
        return fetchConfig;
    }

    /**
     * Sets the fetch configuration flag.
     *
     * @param fetchConfig the fetch configuration flag.
     */
    public void setFetchConfig(boolean fetchConfig) {
        this.fetchConfig = fetchConfig;
    }

    /**
     * Gets the fetch meta data flag.
     *
     * @return the fetch meta data flag.
     */
    public boolean isFetchMetadata() {
        return fetchMetadata;
    }

    /**
     * Sets the fetch meta data flag.
     *
     * @param fetchMetadata the fetch meta data flag.
     */
    public void setFetchMetadata(boolean fetchMetadata) {
        this.fetchMetadata = fetchMetadata;
    }

}
