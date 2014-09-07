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
package org.lorislab.guardian.web.user.controller;

import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.lorislab.guardian.api.model.UserData;
import org.lorislab.guardian.api.model.UserPermission;
import org.lorislab.guardian.api.service.UserDataService;
import org.lorislab.jel.jsf.api.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.permission.controller.PermissionController;

/**
 * The user data controller.
 *
 * @author Andrej Petras
 */
@Named("userDataC")
@Alternative
@SessionScoped
public class UserDataController implements PermissionController, Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -896179965223569147L;

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(UserDataController.class.getName());

    /**
     * The user data service.
     */
    @EJB
    private UserDataService service;

    /**
     * The user data.
     */
    private UserData data;

    /**
     * The user data model.
     */
    private UserPermission permissions;

    /**
     * The initialise method.
     */
    @PostConstruct
    protected void init() {
        load();
    }

    /**
     * Gets the user data.
     *
     * @return the user data.
     */
    @Produces
    public UserData getUserData() {
        if (data == null) {
            load();
        }
        return data;
    }

    /**
     * Loads the user data.
     */
    @FacesServiceMethod
    public void load() {
        try {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                data = service.loadUserData(principal.getName());
                if (data != null) {
                    permissions = service.getUserPermission(principal.getName());
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error loading the user data!", ex);
        }
    }

    /**
     * Saves the user data.
     *
     * @throws Exception if the method fails.
     */
    @FacesServiceMethod
    public void save() throws Exception {
        data = service.saveUserData(data);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean hasUserAction(Enum context, Enum permission) {
        return permissions.hasUserAction(context, permission);
    }
}
