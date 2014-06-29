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

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.TraceablePersistent;

/**
 * The user model.
 *
 * @author Andrej Petras
 */
@Entity(name = "GUser")
@Table(name = "GU_USER_PROFILE")
public class User extends TraceablePersistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 3712978283234186921L;

    /**
     * The principal.
     */
    @Column(name = "C_PRINCIPAL")
    private String principalId;

    /**
     * The enabled flag.
     */
    @Column(name = "C_ENABLED")
    private boolean enabled;
    /**
     * The deleted flag.
     */
    @Column(name = "C_DELETED")
    private boolean deleted;
    
    /**
     * The members.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "user", orphanRemoval = true)
    private Set<UserMember> members = new HashSet<>();   
    
    /**
     * The parameters.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "user", orphanRemoval = true)
    private Set<UserParameter> parameters = new HashSet<>(); 
    
    /**
     * The user profile.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="C_PROFILE_GUID")    
    private UserProfile profile;
    
    
}
