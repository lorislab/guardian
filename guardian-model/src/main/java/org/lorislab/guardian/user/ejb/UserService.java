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
package org.lorislab.guardian.user.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.lorislab.guardian.user.criteria.UserSearchCriteria;
import org.lorislab.guardian.user.model.User;
import org.lorislab.guardian.user.model.User_;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The user service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserService extends AbstractEntityServiceBean<User> {

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

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User saveUser(User user) throws ServiceException {
        return save(user);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteUser(String guid) throws ServiceException {
        return delete(guid);
    }
    
    public User getUserWithRoles(String principal) throws ServiceException {
        User result = null;
        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<User> cq = getBaseEAO().createCriteriaQuery();
        Root<User> root = cq.from(User.class);
        root.fetch(User_.roles);
                        
        cq.where(
                cb.and(
                    cb.equal(root.get(User_.principal), principal),
                    cb.equal(root.get(User_.enabled), false)
                )
        );
        
        TypedQuery<User> query = getBaseEAO().createTypedQuery(cq);
        List<User> tmp = query.getResultList();        
        if (tmp != null && !tmp.isEmpty()) {
            result = tmp.get(0);
        }
        return result;
    }
  
    public User getUserByPrincipal(String principal) throws ServiceException {
        User result = null;
        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<User> cq = getBaseEAO().createCriteriaQuery();
        Root<User> root = cq.from(User.class);
 
        cq.where(cb.equal(root.get(User_.principal), principal));
        
        TypedQuery<User> query = getBaseEAO().createTypedQuery(cq);
        List<User> tmp = query.getResultList();        
        if (tmp != null && !tmp.isEmpty()) {
            result = tmp.get(0);
        }
        return result;
    }
    
    public User getFullUser(String guid) throws ServiceException {
        UserSearchCriteria uc = new UserSearchCriteria();
        uc.setGuid(guid);
        uc.setFetchRoles(true);
        uc.setFetchConfig(true);
        uc.setFetchProfile(true);
        uc.setFetchPassword(true);
        return getUser(uc);        
    }
    
    public User getFullUserByPrincipal(String principal) throws ServiceException {
        UserSearchCriteria uc = new UserSearchCriteria();
        uc.setPrincipal(principal);
        uc.setFetchRoles(true);
        uc.setFetchConfig(true);
        uc.setFetchProfile(true);
        return getUser(uc);
    }
    
    public User getUser(UserSearchCriteria criteria) throws ServiceException {
        User result = null;
        List<User> tmp = getUsers(criteria);
        if (tmp != null && !tmp.isEmpty()) {
            result = tmp.get(0);
        }
        return result;
    }

    public List<User> getUsers(UserSearchCriteria criteria) throws ServiceException {

        List<User> result;

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<User> cq = getBaseEAO().createCriteriaQuery();
        Root<User> root = cq.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria != null) {
                                         
            if (criteria.isFetchProfile()) {
                root.fetch(User_.profile, JoinType.LEFT);
            }
            
            if (criteria.isFetchConfig()) {
                root.fetch(User_.config, JoinType.LEFT);
            }
            
            if (criteria.isFetchRoles()) {
                root.fetch(User_.roles, JoinType.LEFT);
            }
            
            if (criteria.isFetchPassword()) {
                root.fetch(User_.password, JoinType.LEFT);
            }
            
            if (criteria.getPrincipal() != null) {
                predicates.add(cb.equal(root.get(User_.principal), criteria.getPrincipal()));
            }
            
            if (criteria.getGuid() != null) {
                predicates.add(cb.equal(root.get(User_.guid), criteria.getGuid()));
            }   
            
            if (criteria.getGuids() != null) {
                predicates.add(root.get(User_.guid).in(criteria.getGuids()));
            }
            
            if (criteria.isEnabled() != null) {
                predicates.add(cb.equal(root.get(User_.enabled), criteria.isEnabled()));
            }   
           
        }
        
        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        TypedQuery<User> query = getBaseEAO().createTypedQuery(cq);
        result = query.getResultList();

        return result;
    }

    public Set<String> getUserRoles(String principal) throws Exception {
        Set<String> result = null;
        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<User> cq = getBaseEAO().createCriteriaQuery();
        Root<User> root = cq.from(User.class);
        root.fetch(User_.roles);
                        
        cq.where(
                cb.and(
                    cb.equal(root.get(User_.principal), principal),
                    cb.equal(root.get(User_.enabled), true)
                )
        );
        
        TypedQuery<User> query = getBaseEAO().createTypedQuery(cq);
        List<User> tmp = query.getResultList();        
        if (tmp != null && !tmp.isEmpty()) {
            result = tmp.get(0).getRoles();
        }
        return result;
    }
}
