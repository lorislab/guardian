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
package org.lorislab.guardian.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The permission.
 *
 * @author Andrej Petras
 */
@Entity(name = "GPermission")
@Table(name = "GU_PERM",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"C_APP_GUID", "C_CONTEXT", "C_ACTION"})})
public class Permission extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -6167422086623901710L;

    /**
     * The context.
     */
    @Column(name = "C_CONTEXT")
    private String context;

    /**
     * The action.
     */
    @Column(name = "C_ACTION")
    private String action;

    /**
     * The enabled flag.
     */
    @Column(name = "C_ENABLED")
    private boolean enabled;

    /**
     * The application.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_APP_GUID")
    private Application application;

    /**
     * Gets the context.
     *
     * @return the context.
     */
    public String getContext() {
        return context;
    }

    /**
     * Sets the context.
     *
     * @param context the context to set.
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * Gets the action.
     *
     * @return the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action.
     *
     * @param action the action to set.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Gets the enabled flag.
     *
     * @return the enabled flag.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled flag.
     *
     * @param enabled the enabled flag to set.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets the application.
     *
     * @return the application.
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Sets the application.
     *
     * @param application the application to set.
     */
    public void setApplication(Application application) {
        this.application = application;
    }

}
