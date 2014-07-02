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
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The user member.
 *
 * @author Andrej Petras
 */
@Entity(name = "GUserMember")
@Table(name = "GU_MEMBER")
public class UserMember extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 1812989377615443359L;

    /**
     * The member groups.
     */
    @ElementCollection
    @CollectionTable(
            name = "GU_MEMBER_GROUPS",
            joinColumns = @JoinColumn(name = "GU_MEMEBER_GUID")
    )
    @Column(name = "GU_GROUP")
    private Set<String> groups;

    /**
     * The member roles.
     */
    @ElementCollection
    @CollectionTable(
            name = "GU_MEMBER_ROLES",
            joinColumns = @JoinColumn(name = "GU_MEMEBER_GUID")
    )
    @Column(name = "GU_ROLE")
    private Set<String> roles;

    /**
     * The organisation.
     */
    @Column(name = "C_ORG")
    private String organization;

    /**
     * The user.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_USER_GUID")
    private User user;

    /**
     * Gets the set of groups.
     *
     * @return the set of groups.
     */
    public Set<String> getGroups() {
        return groups;
    }

    /**
     * Sets the group.
     *
     * @param groups the group to set.
     */
    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }

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
     * Gets the organisation.
     *
     * @return the organisation.
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Sets the organisation.
     *
     * @param organization the organisation to set.
     */
    public void setOrganization(String organization) {
        this.organization = organization;
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
