package com.github.mohajel.IE.CA8.database;

import jakarta.persistence.EntityManager;

import java.util.List;

import com.github.mohajel.IE.CA8.models.MizdooniDate;
import com.github.mohajel.IE.CA8.models.Reserve;
import com.github.mohajel.IE.CA8.utils.HibernateDatabaseUtil;

public class ReserveDAO {
    private static EntityManager getEntityManager() {
        return HibernateDatabaseUtil.getEntityManager();
    }

    public static void addReserve(Reserve reserve) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(reserve);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Search

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

    public static Reserve getReserveByReservationIdAndUserName(int reservationId, String userName) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Reserve reserve;
        try {
            reserve = entityManager.createQuery("SELECT r " +
                            "FROM Reserve r " +
                            "WHERE r.reservationId = :reservationId " +
                            "AND r.userName = :userName",
                            Reserve.class)
                    .setParameter("reservationId", reservationId)
                    .setParameter("userName", userName)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("No reservation found with reservationId: " + reservationId + " and userName: " + userName);
            reserve = null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return reserve;
    }

    public static List<Reserve> getReservationsByUserName(String userName) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        List<Reserve> reserves = entityManager.createQuery("SELECT r " +
                        "FROM Reserve r " +
                        "WHERE r.userName = :userName",
                        Reserve.class)
                .setParameter("userName", userName)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return reserves;
    }

    public static List<Reserve> getReservationsByRestaurantName(String restaurantName) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        List<Reserve> reserves = entityManager.createQuery("SELECT r " +
                        "FROM Reserve r " +
                        "WHERE r.restaurantName = :restaurantName",
                        Reserve.class)
                .setParameter("restaurantName", restaurantName)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return reserves; }

    public static void cancelReservation(String userName, int reservationId) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Reserve reserve = getReserveByReservationIdAndUserName(reservationId, userName);
        if (reserve != null) {
            reserve.isCancelled = true;
            entityManager.merge(reserve);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
