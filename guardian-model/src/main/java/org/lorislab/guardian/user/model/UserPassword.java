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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The user password.
 *
 * @author Andrej Petras
 */
@Entity(name = "GUserPassword")
@Table(name = "GU_USER_PASSWORD")
public class UserPassword extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 727041496807700423L;

    /**
     * The user.
     */
    @Column(name = "C_USER_GUID")
    private String user;

    /**
     * The password.
     */
    @Column(name = "C_PASSWORD")
    private String password;

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
}
