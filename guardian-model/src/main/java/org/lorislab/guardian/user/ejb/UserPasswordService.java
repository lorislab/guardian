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
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.lorislab.guardian.user.model.User;
import org.lorislab.guardian.user.model.UserPassword;
import org.lorislab.guardian.user.model.UserPassword_;
import org.lorislab.guardian.user.model.User_;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The user password service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserPasswordService extends AbstractEntityServiceBean<UserPassword> {

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

    /**
     * Gets the user password object.
     *
     * @param principal the principal.
     * @return the corresponding user password.
     * @throws ServiceException if the method fails.
     */
    public UserPassword getUserPasswordByPrincipal(String principal) throws ServiceException {
        UserPassword result = null;

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<UserPassword> cq = getBaseEAO().createCriteriaQuery();
        Root<UserPassword> root = cq.from(UserPassword.class);

        List<Predicate> predicates = new ArrayList<>();

        Join<UserPassword, User> join = root.join(UserPassword_.user, JoinType.LEFT);
        
        predicates.add(cb.equal(join.get(User_.principal), principal));

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        TypedQuery<UserPassword> query = getBaseEAO().createTypedQuery(cq);
        List<UserPassword> tmp = query.getResultList();
        if (tmp != null && !tmp.isEmpty()) {
            result = tmp.get(0);
        }

        return result;
    }

    /**
     * Saves the user password.
     *
     * @param userPassword the user password.
     * @return the saved user password.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserPassword saveUserPassword(UserPassword userPassword) throws ServiceException {
        return save(userPassword);
    }

    /**
     * Deletes the user password.
     * @param guid the user password GUID.
     * @return {@code true} if the user password was deleted.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteUserPassword(String guid) throws ServiceException {
        return delete(guid);
    }
}
