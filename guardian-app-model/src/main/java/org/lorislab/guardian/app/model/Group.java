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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The group.
 *
 * @author Andrej Petras
 */
@Entity(name = "GGroup")
@Table(name = "GU_GROUP",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"C_APP_GUID", "C_NAME"})})
public class Group extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -4756268784133103558L;

    /**
     * The role name.
     */
    @Column(name = "C_NAME")
    private String name;

    /**
     * The system group.
     */
    @Column(name = "C_SYSTEM")
    private boolean system;
        
    /**
     * The enabled flag.
     */
    @Column(name = "C_ENABLED")
    private boolean enabled;
    
    /**
     * The application.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_APP_GUID")
    private Application application;

    /**
     * The roles.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)  
    @JoinTable(name = "GU_GROUP_ROLE",
            joinColumns = @JoinColumn(name = "GU_GROUP_GUID"),
            inverseJoinColumns = @JoinColumn(name = "GU_ROLE_GUID"))    
    private Set<Role> roles = new HashSet<>();

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
     * Gets the system group flag.
     *
     * @return the system group flag.
     */
    public boolean isSystem() {
        return system;
    }

    /**
     * Sets the system group flag.
     *
     * @param system the system group flag.
     */
    public void setSystem(boolean system) {
        this.system = system;
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
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the application.
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

}
