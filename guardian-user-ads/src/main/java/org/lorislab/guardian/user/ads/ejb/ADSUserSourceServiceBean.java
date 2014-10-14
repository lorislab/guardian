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

package org.lorislab.guardian.user.ads.ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import org.lorislab.barn.api.service.ConfigurationService;
import org.lorislab.guardian.user.ads.config.ConnectionConfig;
import org.lorislab.guardian.user.ads.util.LDAPUtil;
import org.lorislab.guardian.api.user.criteria.UserSourceSearchCriteria;
import org.lorislab.guardian.api.user.model.UserSourceData;
import org.lorislab.guardian.api.user.service.UserSourceService;

/**
 * The ADS user source service bean.
 * 
 * @author Andrej Petras
 */
@Stateless
@Local(UserSourceService.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ADSUserSourceServiceBean implements UserSourceService {

    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationService configService;
    
    /**
     * {@inheritDoc }
     */
    @Override
    public List<UserSourceData> findUsers(UserSourceSearchCriteria criteria) throws Exception {
        // load configuration
        ConnectionConfig config = configService.getConfiguration(ConnectionConfig.class);
        // create ldap context
        DirContext ldapContext = LDAPUtil.createContext(config);     
        // create search controls
        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);        
        // create criteria
        String ldaCriteria = LDAPUtil.createSearchCriteria(criteria);        
        // search
        NamingEnumeration<SearchResult> items = ldapContext.search(config.getSearchBase(), ldaCriteria, searchCtls);
        // map to the user source data
        List<UserSourceData> result = LDAPUtil.map(items);        
        return result;
    }
    
}
