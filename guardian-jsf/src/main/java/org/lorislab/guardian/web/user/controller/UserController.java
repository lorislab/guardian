/*
 * Copyright 2014 Andrej_Petras.
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
import org.lorislab.guardian.user.ejb.UserService;
import org.lorislab.guardian.user.model.User;

/**
 * The user controller.
 *
 * @author Andrej_Petras
 */
@Named("userC")
@SessionScoped
public class UserController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -5118547437686071828L;

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    
    @EJB
    private UserService service;

    private User user;

    @Produces
    public User getUser() {
        if (user == null) {
            try {
                load();
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error initialise the user data!", ex);
            }
        }
        return user;
    }

    public void load() throws Exception {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        if (principal != null) {
            user = service.getFullUserByPrincipal(principal.getName());
        }
    }

    public void save() throws Exception {
        User tmp = service.saveUser(user);
        user = service.getFullUserByPrincipal(tmp.getPrincipal());
    }
}
