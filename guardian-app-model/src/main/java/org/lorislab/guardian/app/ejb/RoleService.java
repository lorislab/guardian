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

package org.lorislab.guardian.app.ejb;

import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.lorislab.guardian.app.model.Role;
import org.lorislab.guardian.app.model.Role_;
import org.lorislab.guardian.app.model.Application_;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RoleService extends AbstractEntityServiceBean<Role> {
 
    /**
     * The entity manager.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }    
    
    public List<Role> getRolesForUser(String application, Set<String> roles) {
        List<Role> result = null;
        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<Role> cq = getBaseEAO().createCriteriaQuery();
        Root<Role> root = cq.from(Role.class);        
        
        root.fetch(Role_.permissions);
        
        
         cq.where(
                 cb.and(
                    cb.equal(
                        root.join(Role_.application).get(Application_.name), application
                    ),
                    cb.equal(
                        root.get(Role_.enabled), true
                    ),                    
                    root.get(Role_.name).in(roles)
                 )
        );
         
        return result;
    }
}