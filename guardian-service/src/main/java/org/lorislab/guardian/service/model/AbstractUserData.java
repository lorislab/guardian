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

package org.lorislab.guardian.service.model;

import java.util.Set;
import org.lorislab.guardian.api.model.UserData;
import org.lorislab.guardian.api.model.UserDataConfig;
import org.lorislab.guardian.user.model.User;

/**
 *
 * @author Andrej Petras
 */
public abstract class AbstractUserData<C extends UserDataConfig> extends UserData<User, C> {
    
    private static final long serialVersionUID = -3048282351131237445L;
   
    public AbstractUserData(String principal, Set<String> roles, Set<String> actions) {
        super(principal, roles, actions);
    }
    
}
