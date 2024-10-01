package com.github.mohajel.IE.CA7.database;

import jakarta.persistence.EntityManager;

import java.util.ArrayList;

import com.github.mohajel.IE.CA7.models.Dining_Table;
import com.github.mohajel.IE.CA7.utils.HibernateDatabaseUtil;

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

    // Search

    public static ArrayList<Dining_Table> getTablesByRestaurantName(String restaurantName) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        ArrayList<Dining_Table> diningTables = new ArrayList<>();
        try {
            diningTables = (ArrayList<Dining_Table>) entityManager.createQuery("SELECT t FROM Dining_Table t WHERE t.restaurantName = :restaurantName", Dining_Table.class)
                    .setParameter("restaurantName", restaurantName)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Error in getTablesByRestaurantName: " + e.getMessage());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return diningTables;
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
