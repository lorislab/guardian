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

import javax.faces.context.FacesContext;
import org.lorislab.guardian.web.view.ContextOpenViewController;

/**
 * The context open action.
 *
 * @author Andrej Petras
 * @param <T> the context open view controller.
 */
public class ContextOpenAction<T extends ContextOpenViewController> extends AbstractContextControllerAction<T> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -401771961900364439L;

    /**
     * The GUID.
     */
    private String guid;

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     * @param context the context.
     * @param action the action.
     */
    public ContextOpenAction(T parent, Enum context, Enum action) {
        super(parent, context, action);
    }

    /**
     * Sets the GUID.
     *
     * @param guid the GUID.
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected Object doExecute() throws Exception {
        String tmp = guid;
        guid = null;

        if (tmp == null) {
            tmp = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("guid");
        }
        return getParent().open(tmp);
    }
}
