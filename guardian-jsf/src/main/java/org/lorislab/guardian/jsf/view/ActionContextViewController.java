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
package org.lorislab.guardian.jsf.view;

/**
 * The action context view controller interface.
 *
 * @author Andrej Petras
 */
public interface ActionContextViewController {

    /**
     * Returns {@code true} if the user has an action for the context.
     *
     * @param action the action.
     * @param context the context.
     * @return {@code true} if the user has an action for the context.
     */
    public boolean hasUserAction(Enum action, Enum context);
}
