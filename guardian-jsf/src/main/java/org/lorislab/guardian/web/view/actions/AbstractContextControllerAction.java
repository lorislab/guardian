/*
 * Copyright 2012 Andrej Petras <andrej@ajka-andrej.com>.
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
import org.lorislab.jel.jsf.view.AbstractViewControllerAction;


/**
 * The abstract view controller action.
 *
 * @param <T> the view controller.
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public abstract class AbstractContextControllerAction<T extends ActionContextViewController> extends AbstractViewControllerAction<T> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -2802481738839679670L;

    /**
     * The action.
     */
    private Enum action;

    /**
     * The context.
     */
    private Enum context;

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     */
    public AbstractContextControllerAction(T parent) {
        this(parent, null, null);
    }

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     * @param action the action.
     * @param context the context.
     */
    public AbstractContextControllerAction(T parent, Enum action, Enum context) {
        super(parent);
        this.action = action;
        this.context = context;
    }

    /**
     * {@inheritDoc}
     * 
     * Check if the user has action and context.
     * 
     * @return returns {@code true} if the user has action and context enabled.
     */
    @Override
    public boolean isEnabled() {
        return getParent().hasUserAction(action, context);
    }

    /**
     * {@inheritDoc}
     * 
     * Check if the user has action and context.
     * 
     * @return returns {@code true} if the user has action and context enabled.
     */
    @Override
    public boolean isAvailable() {
        return getParent().hasUserAction(action, context);
    }

}
