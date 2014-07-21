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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The application.
 *
 * @author Andrej Petras
 */
@Entity(name = "GApplication")
@Table(name = "GU_APP",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"C_NAME"})})
public class Application extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1360403119055746839L;

    /**
     * The application name.
     */
    @Column(name = "C_NAME")
    private String name;

    /**
     * The roles.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "application", orphanRemoval = true)
    private Set<Role> roles = new HashSet<>();

    /**
     * The permissions.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "application", orphanRemoval = true)
    private Set<Permission> permission = new HashSet<>();

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
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the roles.
     *
     * @return the roles.
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the roles.
     *
     * @param roles the roles to set.
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Gets the permissions.
     *
     * @return the permissions.
     */
    public Set<Permission> getPermission() {
        return permission;
    }

    /**
     * Sets the permissions
     *
     * @param permission the permission to set.
     */
    public void setPermission(Set<Permission> permission) {
        this.permission = permission;
    }
   
}
