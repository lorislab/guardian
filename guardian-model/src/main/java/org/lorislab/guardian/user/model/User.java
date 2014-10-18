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

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.TraceablePersistent;

/**
 * The user model.
 *
 * @author Andrej Petras
 */
@Entity(name = "GUser")
@Table(name = "GU_USER")
public class User extends TraceablePersistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 3712978283234186921L;

    /**
     * The principal.
     */
    @Column(name = "C_PRINCIPAL")
    private String principal;

    /**
     * The enabled flag.
     */
    @Column(name = "C_ENABLED")
    private boolean enabled;
    /**
     * The roles.
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "GU_USER_ROLES",
            joinColumns = @JoinColumn(name = "C_USER_GUID")
    )
    @Column(name = "C_ROLE")
    private Set<String> roles;

    /**
     * The user profile.
     */
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "C_PROFILE_GUID")
    private UserProfile profile;

    /**
     * The user configuration.
     */
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "C_CONFIG_GUID")
    private UserConfig config;

    /**
     * Gets the roles.
     *
     * @return the roles.
     */
    public Set<String> getRoles() {
        return roles;
    }

    /**
     * Sets the roles.
     *
     * @param roles the roles to set.
     */
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    /**
     * The principal.
     *
     * @return the principal
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * Sets the principal.
     *
     * @param principal the principal to set
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    /**
     * Gets the enabled flag.
     *
     * @return the enabled flag.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled flag.
     *
     * @param enabled the enabled flag to set.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets the user profile.
     *
     * @return the profile.
     */
    public UserProfile getProfile() {
        return profile;
    }

    /**
     * Sets the user profile.
     *
     * @param profile the profile to set.
     */
    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    /**
     * Gets the user configuration.
     *
     * @return the user configuration.
     */
    public UserConfig getConfig() {
        return config;
    }

    /**
     * Sets the user configuration.
     *
     * @param config the user configuration.
     */
    public void setConfig(UserConfig config) {
        this.config = config;
    }

}
