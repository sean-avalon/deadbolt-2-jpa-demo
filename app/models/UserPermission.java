package models;

import java.util.*;

import be.objectify.deadbolt.core.models.Permission;
import play.db.jpa.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserPermission implements Permission
{
    @Id
    public Long id;

    @Column(name = "permission_value")
    public String value;

    public String getValue()
    {
        return value;
    }

    public static UserPermission findByValue(String value)
    {
        List<UserPermission> userPermissions = JPA.em()
                .createQuery("from UserPermission where value=?")
                .setParameter(1, value)
                .getResultList();
        if (!userPermissions.isEmpty()) {
            return userPermissions.get(0);
        } else {
            return null;
        }
    }
}
