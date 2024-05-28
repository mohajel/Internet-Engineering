package com.github.mohajel.IE.CA7.database;

import jakarta.persistence.EntityManager;
import org.json.JSONObject;

import com.github.mohajel.IE.CA7.models.Review;
import com.github.mohajel.IE.CA7.utils.HibernateDatabaseUtil;

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

    // Data
    public static JSONObject getAVGRateRestaurantByName(String restaurantName) {
        EntityManager entityManager = getEntityManager();
        JSONObject jsonObject = new JSONObject();
        try {
            String query = "SELECT AVG(r.foodRate), AVG(r.serviceRate), AVG(r.ambianceRate), AVG(r.overallRate), COUNT(r) " +
                    "FROM Review r " +
                    "WHERE r.restaurantName = :restaurantName";

            Object[] result = (Object[]) entityManager.createQuery(query)
                    .setParameter("restaurantName", restaurantName)
                    .getSingleResult();

            if (result[4].equals(0)) {
                jsonObject.put("foodRate", 0);
                jsonObject.put("serviceRate", 0);
                jsonObject.put("ambianceRate", 0);
                jsonObject.put("overallRate", 0);
                jsonObject.put("reviewsCount", 0);
            } else {
                jsonObject.put("foodRate", result[0]);
                jsonObject.put("serviceRate", result[1]);
                jsonObject.put("ambianceRate", result[2]);
                jsonObject.put("overallRate", result[3]);
                jsonObject.put("reviewsCount", result[4]);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        entityManager.close();
        return jsonObject;
    }
}
