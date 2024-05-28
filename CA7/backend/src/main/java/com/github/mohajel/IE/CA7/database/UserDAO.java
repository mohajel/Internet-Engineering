package com.github.mohajel.IE.CA7.database;

import com.github.mohajel.IE.CA7.models.User;
import com.github.mohajel.IE.CA7.utils.HibernateDatabaseUtil;

import jakarta.persistence.EntityManager;

public class UserDAO {

    private static EntityManager getEntityManager() {
        return HibernateDatabaseUtil.getEntityManager();
    }

    // Add
    public static void addUser(User user) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Search
    public static User findUserByUserName(String userName) {
        EntityManager entityManager = getEntityManager();
        User user = entityManager.find(User.class, userName);
        entityManager.close();
        return user;
    }

    public static User findUserByEmail(String email) {
        try (EntityManager entityManager = getEntityManager()) {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
