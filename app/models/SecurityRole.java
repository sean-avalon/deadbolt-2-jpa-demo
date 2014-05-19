package models;

import java.util.*;

import be.objectify.deadbolt.core.models.Role;
import play.db.jpa.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SecurityRole implements Role
{
    @Id
    public Long id;

    public String name;

    public String getName()
    {
        return name;
    }

    public static SecurityRole findByName(String name)
    {
        List<SecurityRole> securityRoles = JPA.em()
                .createQuery("from SecurityRole where name=?")
                .setParameter(1, name)
                .getResultList();
        if (!securityRoles.isEmpty()) {
            return securityRoles.get(0);
        } else {
            return null;
        }
    }
}
