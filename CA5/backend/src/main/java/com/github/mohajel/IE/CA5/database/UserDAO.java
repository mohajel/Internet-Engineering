package com.github.mohajel.IE.CA5.database;

import com.github.mohajel.IE.CA5.models.User;
import com.github.mohajel.IE.CA5.utils.HibernateDatabaseUtil;
import jakarta.persistence.EntityManager;

public class UserDAO {

    private static EntityManager getEntityManager() {
        return HibernateDatabaseUtil.getEntityManager();
    }

    public static User findUserByUserName(String userName) {
        EntityManager entityManager = getEntityManager();
        User user = entityManager.find(User.class, userName);
        entityManager.close();
        return user;
    }
}
