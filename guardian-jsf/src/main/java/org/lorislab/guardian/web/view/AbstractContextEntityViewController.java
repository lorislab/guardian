/*
 * Copyright 2014 lorislab.org.
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

import javax.inject.Inject;
import org.lorislab.guardian.api.model.UserPermission;
import org.lorislab.jel.jsf.view.AbstractEntityViewController;

/**
 * The abstract context entity view controller.
 * 
 * @param <T> the type of the entity.
 * 
 * @author Andrej Petras
 */
public abstract class AbstractContextEntityViewController<T> extends AbstractEntityViewController<T> implements ActionContextViewController {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -9171198009172337397L;
    
    /**
     * The user data.
     */
    @Inject
    protected UserPermission userData;
    
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
