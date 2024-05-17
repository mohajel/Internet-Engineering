package com.github.mohajel.IE.CA5.database;

import com.github.mohajel.IE.CA5.utils.HibernateDatabaseUtil;
import jakarta.persistence.EntityManager;

public class TableDAO {
    EntityManager entityManager = HibernateDatabaseUtil.getEntityManager();

    public void getTableByIdAndRestaurantName(int tableId, String restaurantName) {
        entityManager.createQuery("SELECT t FROM Table t WHERE t.id = :tableId AND t.restaurantName = :restaurantName")
                .setParameter("tableId", tableId)
                .setParameter("restaurantName", restaurantName)
                .getSingleResult();
    }
}
