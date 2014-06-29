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
import javax.persistence.ManyToOne;
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
            @UniqueConstraint(columnNames = {"C_APP_GUID", "C_NAME"})})
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
     * The application.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_APP_GUID")
    private Application application;

    /**
     * The permissions.
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "GU_ROLE_PERM",
            joinColumns = @JoinColumn(name = "GU_ROLE_GUID"),
            inverseJoinColumns = @JoinColumn(name = "GU_PERM_GUID"))
    private Set<Permission> permissions = new HashSet<>();

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
     * Gets the application.
     *
     * @return the application.
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Sets the application.
     *
     * @param application the application to set.
     */
    public void setApplication(Application application) {
        this.application = application;
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
