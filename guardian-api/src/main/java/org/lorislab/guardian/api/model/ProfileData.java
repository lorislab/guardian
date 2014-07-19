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
package org.lorislab.guardian.api.model;

import java.io.Serializable;
import java.util.Locale;

/**
 *
 * @author Andrej Petras
 */
public interface ProfileData extends Serializable {

    /**
     * Gets the first name.
     *
     * @return the first name.
     */
    public String getFirstName();

    /**
     * Gets the middle name.
     *
     * @return the middle name.
     */
    public String getMiddleName();

    /**
     * Gets the last name.
     *
     * @return the last name.
     */
    public String getLastName();

    /**
     * Gets the email.
     *
     * @return the email.
     */
    public String getEmail();

    /**
     * Gets the locale.
     *
     * @return the locale.
     */
    public Locale getLocale();
}
