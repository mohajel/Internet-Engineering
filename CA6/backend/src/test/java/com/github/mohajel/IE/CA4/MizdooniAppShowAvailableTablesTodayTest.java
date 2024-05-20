package com.github.mohajel.IE.CA4;

import com.github.mohajel.IE.CA4.MizdooniApp;
import com.github.mohajel.IE.CA4.testUtils.TestReservationFactory;
import com.github.mohajel.IE.CA4.testUtils.TestRestaurantFactory;
import com.github.mohajel.IE.CA4.testUtils.TestTableFactory;
import com.github.mohajel.IE.CA4.testUtils.TestUserFactory;
import com.github.mohajel.IE.CA4.utils.Utils;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testShowAvailableTablesToday_1Table() {
        try (MockedStatic<Utils> utilities = Mockito.mockStatic(Utils.class)) {
            utilities.when(Utils::getCurrentTime).thenReturn(currentDateTime);
            JSONObject res = app.showAvailableTables(new JSONObject().put("restaurantName", "restaurant1"));
            assertTrue(res.getBoolean("success"));
            assertEquals(3, res.getJSONObject("data").getJSONArray("availableTables").getJSONObject(0).getJSONArray("availableTimes").length());
        }
    }
}
