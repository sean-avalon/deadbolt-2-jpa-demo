/*
 * Copyright 2012 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package models;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;
import play.db.jpa.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
public class AuthorisedUser implements Subject
{
    @Id
    public Long id;

    public String userName;

    @ManyToMany
    public List<SecurityRole> roles;

    @ManyToMany
    public List<UserPermission> permissions;

    @Override
    public List<? extends Role> getRoles()
    {
        return roles;
    }

    @Override
    public List<? extends Permission> getPermissions()
    {
        return permissions;
    }

    @Override
    public String getIdentifier()
    {
        return userName;
    }

    public static AuthorisedUser findByUserName(String userName)
    {
        List<AuthorisedUser> authorisedUsers = JPA.em()
                .createQuery("from AuthorisedUser where userName=?")
                .setParameter(1, userName)
                .getResultList();
        if (!authorisedUsers.isEmpty()) {
            return authorisedUsers.get(0);
        } else {
            return null;
        }
    }
}
