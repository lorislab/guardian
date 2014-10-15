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
package org.lorislab.guardian.user.model;

import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.TraceablePersistent;

/**
 * The user profile.
 *
 * @author Andrej Petras
 */
@Entity(name = "GUserProfile")
@Table(name = "GU_USER_PROFILE")
public class UserProfile extends TraceablePersistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 7706500724718802209L;

    /**
     * The user.
     */
    @OneToOne(mappedBy = "profile")
    private User user;

    /*
     * The first name.
     */
    @Column(name = "C_FIRSTNAME")
    private String firstName;
    /**
     * The middle name.
     */
    @Column(name = "C_MIDDLENAME")
    private String middleName;
    /**
     * The last name.
     */
    @Column(name = "C_LASTNAME")
    private String lastName;

    /**
     * The email.
     */
    @Column(name = "C_EMAIL", unique = true)
    private String email;
    /**
     * The locale.
     */
    @Column(name = "C_LANG")
    private Locale locale;
      
    /**
     * Gets the user.
     *
     * @return the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user the user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the first name.
     *
     * @return the first name.
     */
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
