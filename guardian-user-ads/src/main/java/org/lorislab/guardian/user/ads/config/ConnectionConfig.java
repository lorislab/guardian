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
package org.lorislab.guardian.user.ads.config;

/**
 * The LDAP connection configuration.
 *
 * @author Andrej Petras
 */
public class ConnectionConfig {

    /**
     * The URL connection.
     */
    private String url;

    /**
     * The authentication.
     */
    private String authentication;

    /**
     * The user.
     */
    private String user;

    /**
     * The password.
     */
    private String password;

    /**
     * The search base.
     */
    private String searchBase;

    /**
     * The user filter.
     */
    private String filter;

    /**
     * Gets the URL.
     *
     * @return the URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL.
     *
     * @param url the URL.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the authentication.
     *
     * @return the authentication.
     */
    public String getAuthentication() {
        return authentication;
    }

    /**
     * Sets the authentication.
     *
     * @param authentication the authentication.
     */
    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    /**
     * Gets the filter.
     *
     * @return the filter.
     */
    public String getFilter() {
        return filter;
    }

    /**
     * Sets the filter.
     *
     * @param filter the filter.
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * Gets the password.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the search base.
     *
     * @return the search base.
     */
    public String getSearchBase() {
        return searchBase;
    }

    /**
     * Sets the search base.
     *
     * @param searchBase the search base.
     */
    public void setSearchBase(String searchBase) {
        this.searchBase = searchBase;
    }

    /**
     * Gets the user.
     *
     * @return the user.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user the user.
     */
    public void setUser(String user) {
        this.user = user;
    }

}
