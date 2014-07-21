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

package org.lorislab.guardian.service.model;

import java.util.Locale;
import org.lorislab.guardian.api.model.ProfileData;

/**
 *
 * @author Andrej Petras
 */
public class DefaultProfileData implements ProfileData {
  /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -5753794627419101991L;

    /*
     * The first name.
     */
    private String firstName;

    /**
     * The middle name.
     */
    private String middleName;

    /**
     * The last name.
     */
    private String lastName;

    /**
     * The email.
     */
    private String email;

    /**
     * The locale.
     */
    private Locale locale;
    
    /**
     * Gets the first name.
     *
     * @return the first name.
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the middle name.
     *
     * @return the middle name.
     */
    @Override
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name.
     *
     * @param middleName the middle name to set.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name.
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email.
     *
     * @return the email.
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the locale.
     *
     * @return the locale.
     */
    @Override
    public Locale getLocale() {
        return locale;
    }

    /**
     * Sets the locale.
     *
     * @param locale the locale to set.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }    
}
