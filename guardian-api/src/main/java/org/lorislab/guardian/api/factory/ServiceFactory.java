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

package org.lorislab.guardian.api.factory;

import java.util.Iterator;
import java.util.ServiceLoader;
import org.lorislab.guardian.api.service.ApplicationDataService;
import org.lorislab.guardian.api.service.UserDataFactoryService;

/**
 *
 * @author Andrej Petras
 */
public final class ServiceFactory {
    
    private static ApplicationDataService APP_SERVICE;
    
    private static UserDataFactoryService USER_DATA_FACTORY_SERVICE;
    
    static {       
        ServiceLoader<ApplicationDataService> loader = ServiceLoader.load(ApplicationDataService.class);
        Iterator<ApplicationDataService> iter = loader.iterator();
        if (iter.hasNext()) {
            APP_SERVICE = iter.next();
        }        
        
        ServiceLoader<UserDataFactoryService> loader2 = ServiceLoader.load(UserDataFactoryService.class);
        Iterator<UserDataFactoryService> iter2 = loader2.iterator();
        if (iter2.hasNext()) {
            USER_DATA_FACTORY_SERVICE = iter2.next();
        }         
    }  
    
    private ServiceFactory() {
        // empty constructor
    }
    
    public static UserDataFactoryService getUserDataFactoryService() {
        return USER_DATA_FACTORY_SERVICE;
    }    
    
    public static ApplicationDataService getApplicationDataService() {
        return APP_SERVICE;
    }    
}
