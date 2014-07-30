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

package org.lorislab.guardian.web.view.actions;

import org.lorislab.guardian.web.view.ActionContextViewController;
import org.lorislab.guardian.web.view.actions.AbstractContextControllerAction;

/**
 *
 * @author Andrej Petras
 */
public class ContextMenuAction<T extends ActionContextViewController> extends AbstractContextControllerAction<T> {
    
    private static final long serialVersionUID = -2570364728504344270L;

    private String navigation;
    
    public ContextMenuAction(T parent, Enum context, Enum action) {
        this(parent, context, action, null);
    }
    
    public ContextMenuAction(T parent, Enum context, Enum action, String navigation) {
        super(parent, context, action);
        this.navigation = navigation;
    }
    
    @Override
    public Object execute() {
        return navigation;
    }       
}
