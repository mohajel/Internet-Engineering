package com.github.mohajel.IE.CA5.database;

import com.github.mohajel.IE.CA5.models.Restaurant;
import com.github.mohajel.IE.CA5.utils.HibernateDatabaseUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

public class RestaurantDAO {

    private static EntityManager getEntityManager() {
        return HibernateDatabaseUtil.getEntityManager();
    }

    public static Restaurant getRestaurantByName(String name) {
        EntityManager entityManager = getEntityManager();
        Restaurant restaurant = entityManager.find(Restaurant.class, name);
        entityManager.close();
        return restaurant;
    }


}
