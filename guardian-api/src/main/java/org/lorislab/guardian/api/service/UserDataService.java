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
package org.lorislab.guardian.api.service;

import java.util.List;
import org.lorislab.guardian.api.criteria.UserDataCriteria;
import org.lorislab.guardian.api.model.UserData;
import org.lorislab.guardian.api.model.UserPermission;

/**
 * The user data profile service.
 *
 * @author Andrej Petras
 */
public interface UserDataService {

    /**
     * Gets the user permissions.
     *
     * @param principal the principal.
     * @return the use permissions.
     * @throws java.lang.Exception if the method fails.
     */
    public UserPermission getUserPermission(String principal) throws Exception;

    /**
     * Gets the user data profile.
     *
     * @param principal the user data profile.
     * @return the user data profile.
     * @throws java.lang.Exception if the method fails.
     */
    public UserData loadUserData(String principal) throws Exception;

    /**
     * Saves the user data.
     *
     * @param profile the user data.
     * @return the saved user data.
     * @throws java.lang.Exception if the method fails.
     */
    public UserData saveUserData(UserData profile) throws Exception;

    /**
     * Finds the user data profile by criteria.
     *
     * @param criteria the user data profile criteria.
     * @return the corresponding list of users profiles.
     * @throws java.lang.Exception if the method fails.
     */
    public List<UserData> findUserDataByCriteria(UserDataCriteria criteria) throws Exception;
}
