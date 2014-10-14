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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The user configuration.
 *
 * @author Andrej Petras
 */
@Entity(name = "GUserConfig")
@Table(name = "GU_USER_CONFIG")
public class UserConfig extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 7706500724718802209L;

    /**
     * The user.
     */
    @OneToOne(mappedBy = "config")
    private User user;

    /*
     * The first name.
     */
    @Column(name = "C_NOTIFY")
    private boolean notification;

    /**
     * The user key.
     */
    @Column(name = "C_KEY")
    private String key;

    /**
     * Gets the key.
     *
     * @return the key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key the key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the user notification flag.
     *
     * @return the user notification flag.
     */
    public boolean isNotification() {
        return notification;
    }

    /**
     * Sets the user notification flag.
     *
     * @param notification the user notification flag.
     */
    public void setNotification(boolean notification) {
        this.notification = notification;
    }

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

}
