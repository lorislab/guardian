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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.lorislab.guardian.user.model.User;
import org.lorislab.guardian.user.model.UserProfile;
import org.lorislab.guardian.user.model.UserProfile_;
import org.lorislab.guardian.user.model.User_;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The user profile service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserProfileService extends AbstractEntityServiceBean<UserProfile> {

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
     * Saves the user profile.
     *
     * @param profile the user profile.
     * @return the saved user profile.
     * @throws Exception if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserProfile saveProfile(UserProfile profile) throws Exception {
        return this.save(profile);
    }

    /**
     * Gets the user profile by principal.
     *
     * @param principal the user principal.
     * @return the corresponding user profile.
     * @throws Exception if the method fails.
     */
    public UserProfile getProfileByPrincipal(String principal) throws Exception {
        UserProfile result = null;

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<UserProfile> cq = getBaseEAO().createCriteriaQuery();
        Root<UserProfile> root = cq.from(UserProfile.class);

        Join<UserProfile, User> join = (Join<UserProfile, User>) root.fetch(UserProfile_.user, JoinType.LEFT);

        cq.where(cb.equal(join.get(User_.principal), principal));

        TypedQuery<UserProfile> query = getBaseEAO().createTypedQuery(cq);
        List<UserProfile> tmp = query.getResultList();
        if (tmp != null && !tmp.isEmpty()) {
            result = tmp.get(0);
        }
        return result;
    }

    /**
     * {@inheritDoc }
     */
    public List<UserProfile> getProfiles(Set<String> users) throws Exception {
        List<UserProfile> result = null;
        if (users != null && !users.isEmpty()) {
            CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
            CriteriaQuery<UserProfile> cq = getBaseEAO().createCriteriaQuery();
            Root<UserProfile> root = cq.from(UserProfile.class);

            Join<UserProfile, User> join = (Join<UserProfile, User>) root.fetch(UserProfile_.user, JoinType.LEFT);

            cq.where(
                    cb.and(
                            join.get(User_.guid).in(users),
                            cb.equal(join.get(User_.enabled), true)
                    )
            );

            TypedQuery<UserProfile> query = getBaseEAO().createTypedQuery(cq);
            result = query.getResultList();
        }
        return result;
    }

    /**
     * {@inheritDoc }
     */    
    public List<UserProfile> getProfiles() throws Exception {
        CriteriaQuery<UserProfile> cq = getBaseEAO().createCriteriaQuery();
        Root<UserProfile> root = cq.from(UserProfile.class);
        root.fetch(UserProfile_.user, JoinType.LEFT);
        TypedQuery<UserProfile> query = getBaseEAO().createTypedQuery(cq);
        List<UserProfile> result = query.getResultList();
        return result;
    }
}
