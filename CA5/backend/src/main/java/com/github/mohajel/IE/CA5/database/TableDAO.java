package com.github.mohajel.IE.CA5.database;

import com.github.mohajel.IE.CA5.models.Table;
import com.github.mohajel.IE.CA5.utils.HibernateDatabaseUtil;
import jakarta.persistence.EntityManager;

public class TableDAO {
    private static EntityManager getEntityManager() {
        return HibernateDatabaseUtil.getEntityManager();
    }

    public static void addTable(Table table) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(table);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static Table getTableById(int tableId) {
        EntityManager entityManager = getEntityManager();
        Table table = entityManager.find(Table.class, tableId);
        entityManager.close();
        return table;
    }
}
