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
import java.util.Locale;

/**
 * The user profile.
 *
 * @author Andrej Petras
 */
public interface UserDataProfile extends Serializable {

    /**
     * Gets the user GUID.
     *
     * @return the user GUID.
     */
    public String getUserGuid();

    /**
     * Gets the first name.
     *
     * @return the first name.
     */
    public String getFirstName();

    /**
     * Sets the first name.
     *
     * @param firstName the first name.
     */
    public void setFirstName(String firstName);

    /**
     * Gets the middle name.
     *
     * @return the middle name.
     */
    public String getMiddleName();

    /**
     * Sets the middle name.
     *
     * @param middleName the middle name to set.
     */
    public void setMiddleName(String middleName);

    /**
     * Gets the last name.
     *
     * @return the last name.
     */
    public String getLastName();

    /**
     * Sets the last name.
     *
     * @param lastName the last name to set.
     */
    public void setLastName(String lastName);

    /**
     * Gets the email.
     *
     * @return the email.
     */
    public String getEmail();

    /**
     * Sets the email.
     *
     * @param email the email to set.
     */
    public void setEmail(String email);

    /**
     * Gets the locale.
     *
     * @return the locale.
     */
    public Locale getLocale();

    /**
     * Sets the locale.
     *
     * @param locale the locale to set.
     */
    public void setLocale(Locale locale);

}
