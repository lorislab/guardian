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
package org.lorislab.guardian.user.ads.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchResult;
import org.lorislab.guardian.user.ads.config.ConnectionConfig;
import org.lorislab.guardian.api.user.criteria.UserSourceSearchCriteria;
import org.lorislab.guardian.api.user.model.UserSourceData;
import org.lorislab.treasure.api.factory.PasswordServiceFactory;
import org.lorislab.treasure.api.service.PasswordService;

/**
 * The LDAP utility class.
 *
 * @author Andrej Petras
 */
public final class LDAPUtil {

    /**
     * The LDAP factory.
     */
    private static final String FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";

    /**
     * The user name.
     */
    private static final String ADS_NAME = "givenName";

    /**
     * The user principal name.
     */
    private static final String ADS_PRINCIPAL = "userPrincipalName";

    /**
     * The user surname.
     */
    private static final String ADS_SURNAME = "sn";

    /**
     * The user email.
     */
    private static final String ADS_EMAIL = "mail";

    /**
     * The user id.
     */
    public static final String ADS_USER_ID = "sAMAccountName";

    /**
     * The use member of group attribute.
     */
    public static final String ADS_MEMBER_OF = "memberOf";

    /**
     * The default constructor.
     */
    private LDAPUtil() {
        // empty constructor
    }

    /**
     * Creates active directory the search criteria.
     *
     * @param criteria the search criteria.
     * @return the corresponding search criteria string.
     */
    public static String createSearchCriteria(UserSourceSearchCriteria criteria) {
        StringBuilder sb = new StringBuilder();
        sb.append("(&(objectClass=person)");
        if (criteria != null && !criteria.isEmpty()) {
            if (criteria.getPrincipal() != null) {
                addCriteria(ADS_PRINCIPAL, criteria.getPrincipal(), sb);
            }
            if (criteria.getUserId() != null) {
                addCriteria(ADS_USER_ID, criteria.getUserId(), sb);
            }
            if (criteria.getSurname() != null) {
                addCriteria(ADS_SURNAME, criteria.getSurname(), sb);
            }
            if (criteria.getEmail() != null) {
                addCriteria(ADS_EMAIL, criteria.getEmail(), sb);
            }
            if (criteria.getName() != null) {
                addCriteria(ADS_NAME, criteria.getName(), sb);
            }
        }
        sb.append(')');

        return sb.toString();
    }

    /**
     * Adds the search attribute.
     *
     * @param name the name.
     * @param value the value.
     * @param sb the search criteria builder.
     * @return the updated search criteria builder.
     */
    private static StringBuilder addCriteria(String name, String value, StringBuilder sb) {
        return sb.append("&(").append(name).append("=").append(value).append(")");
    }

    /**
     * Maps the result to the list of user source data.
     *
     * @param ldapResult the active directory result.
     * @return the corresponding list of user source data.
     */
    public static List<UserSourceData> map(NamingEnumeration<SearchResult> ldapResult) {
        List<UserSourceData> result = null;
        if (ldapResult != null) {
            result = new ArrayList<>();
            while (ldapResult.hasMoreElements()) {
                Attributes attrs = ldapResult.nextElement().getAttributes();
                UserSourceData user = mapAttributes(attrs);
                if (user != null) {
                    result.add(user);
                }
            }
        }
        return result;
    }

    /**
     * Maps the user attributes.
     *
     * @param attrs the active directory attributes.
     * @return the corresponding user data.
     */
    public static UserSourceData mapAttributes(Attributes attrs) {
        UserSourceData result = null;
        if (attrs != null) {
            result = new UserSourceData();
            result.setName(map(attrs, ADS_NAME));
            result.setSurname(map(attrs, ADS_SURNAME));
            result.setPrincipal(map(attrs, ADS_PRINCIPAL));
            result.setEmail(map(attrs, ADS_EMAIL));
            result.setUserId(map(attrs, ADS_USER_ID));
        }
        return result;
    }

    /**
     * Maps the attribute.
     *
     * @param attrs the attribute.
     * @param name the name.
     * @return the attribute value.
     */
    private static String map(Attributes attrs, String name) {
        String result = null;
        try {
            Attribute userIdAttribute = attrs.get(name);
            Object object = userIdAttribute.get();
            result = object.toString();
        } catch (Exception e) {
            // on exception, the attribute will not be set
        }
        return result;
    }

    /**
     * Creates the LDAP context.
     *
     * @param config the configuration.
     * @return the LDAP context.
     * @throws java.lang.Exception if the method fails.
     */
    public static DirContext createContext(ConnectionConfig config) throws Exception {
        Hashtable<String, String> ldapEnv = new Hashtable<>(5);
        ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
        ldapEnv.put(Context.PROVIDER_URL, config.getUrl());
        ldapEnv.put(Context.SECURITY_AUTHENTICATION, config.getAuthentication());
        if (config.getUser() != null) {
            ldapEnv.put(Context.SECURITY_PRINCIPAL, config.getUser());
        }
        if (config.getPassword() != null) {
            PasswordService service = PasswordServiceFactory.getService();
            char[] tmp = service.getPassword(config.getPassword());
            ldapEnv.put(Context.SECURITY_CREDENTIALS, new String(tmp));
        }
        DirContext result = new InitialDirContext(ldapEnv);
        return result;
    }

}
