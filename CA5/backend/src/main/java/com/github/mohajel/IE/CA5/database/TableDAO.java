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

    public static Dining_Table getTableById(int tableId) {
        EntityManager entityManager = getEntityManager();
        Dining_Table diningTable = entityManager.find(Dining_Table.class, tableId);
        entityManager.close();
        return diningTable;
    }
}
