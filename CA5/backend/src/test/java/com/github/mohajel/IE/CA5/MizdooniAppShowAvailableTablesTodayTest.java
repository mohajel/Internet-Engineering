package com.github.mohajel.IE.CA5;

import com.github.mohajel.IE.CA5.testUtils.TestReservationFactory;
import com.github.mohajel.IE.CA5.testUtils.TestRestaurantFactory;
import com.github.mohajel.IE.CA5.testUtils.TestTableFactory;
import com.github.mohajel.IE.CA5.testUtils.TestUserFactory;

import org.json.JSONObject;
import org.junit.Before;


public class MizdooniAppShowAvailableTablesTodayTest {
    private MizdooniApp app;

    final private String currentDateTime = "2024-08-15 13:00";
    final private String restaurantStartTime = "10:00";
    final private String restaurantEndTime = "17:00";

    @Before
    public void setUp() {
        app = new MizdooniApp();
        JSONObject manager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
        app.addUser(manager);
        JSONObject restaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
        restaurant.put("startTime", restaurantStartTime);
        restaurant.put("endTime", restaurantEndTime);
        app.addRestaurant(restaurant);
        JSONObject validTable = TestTableFactory.createSimpleTable(1, 4, "restaurant1", "manager1");
        app.addTable(validTable);

        JSONObject validUser = TestUserFactory.createSimpleUser("user1", "user1@gmail.com");
        app.addUser(validUser);
        JSONObject validUser2 = TestUserFactory.createSimpleUser("user2", "user2@gmail.com");
        app.addUser(validUser2);

        JSONObject reservation = TestReservationFactory.createSimpleReservation("user1", "restaurant1", 1, currentDateTime);
        app.reserveTable(reservation);
    }
}
