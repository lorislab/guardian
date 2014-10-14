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
package org.lorislab.guardian.app.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The role.
 *
 * @author Andrej Petras
 */
@Entity(name = "GRole")
@Table(name = "GU_ROLE",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"C_NAME"})})
public class Role extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -4291089043418556813L;

    /**
     * The role name.
     */
    @Column(name = "C_NAME")
    private String name;

    /**
     * The system role.
     */
    @Column(name = "C_SYSTEM")
    private boolean system;

    /**
     * The enabled flag.
     */
    @Column(name = "C_ENABLED")
    private boolean enabled;

    /**
     * The permissions.
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "GU_ROLE_PERM",
            joinColumns = @JoinColumn(name = "C_ROLE_GUID"),
            inverseJoinColumns = @JoinColumn(name = "C_PERM_GUID"))
    private Set<Permission> permissions = new HashSet<>();

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
     * Gets the system role flag.
     *
     * @return the system role flag.
     */
    public boolean isSystem() {
        return system;
    }

    /**
     * Sets the system role flag.
     *
     * @param system the system role flag.
     */
    public void setSystem(boolean system) {
        this.system = system;
    }

    /**
     * Sets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the name.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
  
    /**
     * Gets the permissions.
     *
     * @return the permissions.
     */
    public Set<Permission> getPermissions() {
        return permissions;
    }

    /**
     * Sets the permissions.
     *
     * @param permissions the permissions to set.
     */
    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

}
