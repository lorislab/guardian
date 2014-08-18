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

package org.lorislab.guardian.web.view;

import java.io.Serializable;
import javax.inject.Inject;
import org.lorislab.guardian.api.model.UserData;

/**
 * The abstract action context view controller.
 * 
 * @author Andrej Petras
 */
public abstract class AbstractActionContextViewController implements ActionContextViewController, Serializable {
   
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6385247461490018495L;

    /**
     * The user data.
     */
    @Inject
    protected UserData userData;
    
    /**
     * {@inheritDoc }
     */
    @Override
    public boolean hasUserAction(Enum context, Enum action) {
        if (userData != null) {
            return userData.hasAction(context, action);
        }
        return false;
    }
    
}
