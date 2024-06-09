package com.github.mohajel.IE.CA8.database;

import jakarta.persistence.EntityManager;

import java.util.List;

import com.github.mohajel.IE.CA8.models.Restaurant;
import com.github.mohajel.IE.CA8.utils.HibernateDatabaseUtil;

public class RestaurantDAO {

    private static EntityManager getEntityManager() {
        return HibernateDatabaseUtil.getEntityManager();
    }

    public static void addRestaurant(Restaurant restaurant) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(restaurant);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static List<Restaurant> getAllRestaurants() {
        EntityManager entityManager = getEntityManager();
        List<Restaurant> restaurants;
        restaurants = entityManager.createQuery("SELECT r FROM Restaurant r", Restaurant.class)
                .setMaxResults(6)
                .getResultList();
        entityManager.close();
        return restaurants;
    }

    // Search
    public static Restaurant findRestaurantByName(String name) {
        EntityManager entityManager = getEntityManager();
        Restaurant restaurant = entityManager.find(Restaurant.class, name);
        entityManager.close();
        return restaurant;
    }

    public static List<Restaurant> getRestaurantsByManagerUserName(String managerUserName) {
        EntityManager entityManager = getEntityManager();
        List<Restaurant> restaurants;
        restaurants = entityManager.createQuery("SELECT r FROM Restaurant r WHERE r.managerUserName = :managerUserName", Restaurant.class)
                .setParameter("managerUserName", managerUserName)
                .getResultList();
        entityManager.close();
        return restaurants;
    }

    public static List<Restaurant> getRestaurantsByType(String restaurantType) {
        EntityManager entityManager = getEntityManager();
        List<Restaurant> restaurants;
        restaurants = entityManager.createQuery("SELECT r FROM Restaurant r WHERE r.type = :restaurantType", Restaurant.class)
                .setParameter("restaurantType", restaurantType)
                .getResultList();
        entityManager.close();
        return restaurants;
    }

    public static List<Restaurant> getRestaurantByCity(String city) {
        EntityManager entityManager = getEntityManager();
        List<Restaurant> restaurants;

        restaurants = entityManager.createQuery("SELECT r " +
                        "FROM Restaurant r " +
                        "INNER JOIN r.address a " +
                        "WHERE a.city = :city", Restaurant.class)
                .setParameter("city", city)
                .getResultList();
        entityManager.close();
        return restaurants;
    }

    public static List<Restaurant> getRestaurantsContainName(String name) {
        EntityManager entityManager = getEntityManager();
        List<Restaurant> restaurants;
        restaurants = entityManager.createQuery("SELECT r FROM Restaurant r WHERE r.name LIKE :name", Restaurant.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
        entityManager.close();
        return restaurants;
    }
}
