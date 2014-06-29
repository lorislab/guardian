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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The user parameter.
 * 
 * @author Andrej Petras
 */
public class UserParameter extends Persistent {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 560431714187681441L;
    
    /**
     * The user.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_USER_GUID")
    private User user;  
    
    /**
     * The type.
     */
    @Column(name = "C_TYPE")
    private String type;
    
    /**
     * The name.
     */
    @Column(name = "C_NAME")
    private String name;
    
    /**
     * The value.
     */
    @Column(name = "C_VALUE")
    private String value;
    
}
