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

import org.lorislab.guardian.api.model.UserPermission;

/**
 * The user data profile service.
 *
 * @author Andrej Petras
 */
public interface UserDataService {
    
    /**
     * Deletes the user password.
     *
     * @param principal the user principal.
     * @return returns {@code true} if the password was deleted.
     * @throws Exception if the method fails.
     */
    public boolean deleteUserPassword(String principal) throws Exception;

    /**
     * Change the user password.
     *
     * @param principal the user principal.
     * @param oldPassword the old password.
     * @param newPassword the new password.
     * @return returns {@code true} if the password was changed.
     * @throws Exception if the method fails.
     */
    public boolean changeUserPassword(String principal, String oldPassword, String newPassword) throws Exception;

    /**
     * Gets the user permissions.
     *
     * @param principal the principal.
     * @return the use permissions.
     * @throws java.lang.Exception if the method fails.
     */
    public UserPermission getUserPermission(String principal) throws Exception;
   
}
