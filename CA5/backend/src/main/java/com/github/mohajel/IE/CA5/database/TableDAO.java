package com.github.mohajel.IE.CA5.database;

import com.github.mohajel.IE.CA5.models.Dining_Table;
import com.github.mohajel.IE.CA5.utils.HibernateDatabaseUtil;
import jakarta.persistence.EntityManager;

public class TableDAO {
    private static EntityManager getEntityManager() {
        return HibernateDatabaseUtil.getEntityManager();
    }

    public static void addTable(Dining_Table diningTable) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(diningTable);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static Dining_Table getTableByIdAndRestaurantName(int tableId, String restaurantName) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Dining_Table diningTable = null;
        try {
            diningTable = entityManager.createQuery("SELECT t FROM Dining_Table t WHERE t.id = :id AND t.restaurantName = :restaurantName", Dining_Table.class)
                    .setParameter("id", tableId)
                    .setParameter("restaurantName", restaurantName)
                    .getSingleResult();
        } catch (Exception e) {
            diningTable = null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return diningTable;

    }
}
