package com.github.mohajel.IE.CA5.database;

import com.github.mohajel.IE.CA5.models.Review;
import com.github.mohajel.IE.CA5.utils.HibernateDatabaseUtil;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class ReviewDAO {
    private static EntityManager getEntityManager() {
        return HibernateDatabaseUtil.getEntityManager();
    }

    public static void addReview(Review review) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(review);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Search
    public static ArrayList<Review> getReviewsByRestaurantName(String restaurantName) {
        EntityManager entityManager = getEntityManager();
        ArrayList<Review> reviews = (ArrayList<Review>) entityManager.createQuery("SELECT r FROM Review r WHERE r.restaurantName = :restaurantName", Review.class)
                .setParameter("restaurantName", restaurantName)
                .getResultList();
        entityManager.close();
        return reviews;
    }

    public static Review getReviewByUserNameAndRestaurantName(String userName, String restaurantName) {
        EntityManager entityManager = getEntityManager();
        Review review;
        try {
            review = entityManager.createQuery("SELECT r FROM Review r WHERE r.userName = :userName AND r.restaurantName = :restaurantName", Review.class)
                    .setParameter("userName", userName)
                    .setParameter("restaurantName", restaurantName)
                    .getSingleResult();
        } catch (Exception e) {
            review = null;
        }
        entityManager.close();
        return review;
    }

    // Update
    public static void updateReview(Review review) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(review);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
