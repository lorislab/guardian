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
package org.lorislab.guardian.user.api.model;

import java.io.Serializable;

/**
 * The user source data model.
 *
 * @author Andrej Petras
 */
public class UserSourceData implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -3402024024880372073L;

    /**
     * The principal.
     */
    private String principal;
    /**
     * The name.
     */
    private String name;
    /**
     * The surname.
     */
    private String surname;
    /**
     * The email.
     */
    private String email;

    /**
     * The user ID.
     */
    private String userId;

    /**
     * Gets the user ID.
     *
     * @return the user ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId the user ID.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the email.
     *
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets the principal.
     *
     * @param principal the principal.
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    /**
     * Gets the surname.
     *
     * @return the surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname.
     *
     * @param surname the surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
