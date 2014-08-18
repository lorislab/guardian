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
package org.lorislab.guardian.web.user.controller;

import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.lorislab.guardian.api.model.UserData;
import org.lorislab.guardian.api.service.UserDataService;

/**
 * The user controller.
 *
 * @author Andrej Petras
 */
@Named("userC")
@SessionScoped
public class UserController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 9212806894953594995L;

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    /**
     * The service.
     */
    @EJB
    private UserDataService service;

    /**
     * The user data model.
     */
    private UserData data;

    /**
     * Gest the user data.
     *
     * @return the user data.
     */
    @Produces
    public UserData getUser() {
        if (data == null) {
            load();
        }
        return data;
    }

    /**
     * Loads the user data by service.
     */
    private void load() {
        try {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                data = service.getUserData(principal.getName());
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error loading the user data!", ex);
        }
    }
}
