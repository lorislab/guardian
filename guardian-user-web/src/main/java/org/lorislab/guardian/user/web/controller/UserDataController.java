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

package org.lorislab.guardian.user.web.controller;

import java.io.Serializable;
import java.security.Principal;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.guardian.api.model.UserData;
import org.lorislab.guardian.api.service.UserDataService;
import org.lorislab.jel.jsf.util.FacesResourceUtil;

/**
 * The user data controller.
 * 
 * @author Andrej Petras
 */
@Named("userVC")
@SessionScoped
public class UserDataController implements Serializable {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -4460344406458043865L;
    
    /**
     * The user data service.
     */
    @EJB
    private UserDataService service;
    
    /**
     * The principal
     */
    @Inject
    private Principal principal;
    
    /**
     * The user data model.
     */
    private UserData user;
    
    /**
     * Gets the user data model.
     * @return 
     */
    @Produces
    public UserData getUserData() {
        if (user == null) {
            load();
        }
        return user;
    }
    
    /**
     * Loads the user data model.
     */
    public void load() {
        if (principal != null) {
            try {
                user = service.getUser(principal.getName());
            } catch (Exception ex) {
                FacesResourceUtil.handleExceptionMessage(ex);
            }
        }
    }
}
