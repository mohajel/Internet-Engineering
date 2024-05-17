package com.github.mohajel.IE.CA5.database;

import com.github.mohajel.IE.CA5.models.Restaurant;
import com.github.mohajel.IE.CA5.utils.HibernateDatabaseUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

public class RestaurantDAO {

    private static EntityManager getEntityManager() {
        return HibernateDatabaseUtil.getEntityManager();
    }

    public static Restaurant findRestaurantByName(String name) {
        EntityManager entityManager = getEntityManager();
        Restaurant restaurant = entityManager.find(Restaurant.class, name);
        entityManager.close();
        return restaurant;
    }

    public static void addRestaurant(Restaurant restaurant) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(restaurant);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
