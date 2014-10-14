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
package org.lorislab.guardian.api.user.service;

import java.util.List;
import org.lorislab.guardian.api.user.criteria.UserSourceSearchCriteria;
import org.lorislab.guardian.api.user.model.UserSourceData;

/**
 * The user source service.
 *
 * @author Andrej Petras
 */
public interface UserSourceService {

    /**
     * Finds the user by the criteria.
     *
     * @param criteria the user source search criteria.
     * @return the list of the user source data.
     * @throws java.lang.Exception if the method fails.
     */
    public List<UserSourceData> findUsers(UserSourceSearchCriteria criteria) throws Exception;
}
