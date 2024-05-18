package com.github.mohajel.IE.CA5.database;

import com.github.mohajel.IE.CA5.models.MizdooniDate;
import com.github.mohajel.IE.CA5.models.Reserve;
import com.github.mohajel.IE.CA5.utils.HibernateDatabaseUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ReserveDAO {
    private static EntityManager getEntityManager() {
        return HibernateDatabaseUtil.getEntityManager();
    }

    public static boolean isTableReserved(int tableId, String restaurantName, MizdooniDate reserveDate) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        List<Reserve> reserves = entityManager.createQuery("SELECT r " +
                                "FROM Reserve r " +
                                "WHERE r.tableId = :tableId " +
                                "AND r.restaurantName = :restaurantName " +
                                "AND r.isCancelled = false",
                                Reserve.class)
                .setParameter("tableId", tableId)
                .setParameter("restaurantName", restaurantName)
                .getResultList();

        boolean isReserved = reserves.stream().anyMatch(reserve -> reserve.reserveDate.equals(reserveDate));
        entityManager.getTransaction().commit();
        entityManager.close();
        return isReserved;
    }
}
