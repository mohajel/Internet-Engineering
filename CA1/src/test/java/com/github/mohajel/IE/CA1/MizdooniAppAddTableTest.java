package com.github.mohajel.IE.CA1;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.github.mohajel.IE.CA1.testUtils.*;
import com.github.mohajel.IE.CA1.utils.MizdooniError;

public class MizdooniAppAddTableTest {

    private MizdooniApp app;

    @Before
    public void setUp() {
        app = new MizdooniApp();
        JSONObject manager = TestUserFactory.createSimpleManager("manager1", "manager@gmail.com");
        app.addUser(manager);
        JSONObject restaurant = TestRestaurantFactory.createSimpleRestaurant("resturant1", "manager1");
        app.addRestaurant(restaurant);

    }

    @Test
    public void testAddTableValid() {
        JSONObject validTable = TestTableFactory.createSimpleTable(1, 4, "resturant1", "manager1");
        JSONObject res = app.addTable(validTable);
        assertEquals(res.getBoolean("success"), true);
        System.out.println(res.toString());
    }

    @Test
    public void testAddTableUniqueId() {
        JSONObject validTable = TestTableFactory.createSimpleTable(1, 4, "resturant1", "manager1");
        app.addTable(validTable);
        JSONObject res = app.addTable(validTable);
        System.out.println(res.toString());
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.TABLE_ID_NOT_UNIQUE);
    }

    @Test
    public void testAddTableManagerIsNotManager() {
        JSONObject validClient = TestUserFactory.createSimpleUser("user1", "user1@gamil.com");
        app.addUser(validClient);
        JSONObject invalidTable = TestTableFactory.createSimpleTable(1, 4, "resturant1", "user1");
        JSONObject res = app.addTable(invalidTable);

        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.USER_IS_NOT_MANAGER);
    }

    @Test
    public void testAddTableResturantDoesNotExist() {
        JSONObject invalidTable = TestTableFactory.createSimpleTable(1, 4, "resturant2", "manager1");
        JSONObject res = app.addTable(invalidTable);

        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESTAURANT_DOES_NOT_EXIST);
    }

    @Test
    public void testAddTableSeatsNumberNotNatural() {
        JSONObject invalidTable = TestTableFactory.createSimpleTable(1, 2, "resturant1", "manager1");
        invalidTable.put("seatsNumber", 2.4);
        System.out.println(invalidTable.toString());

        JSONObject res = app.addTable(invalidTable);

        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.INVALID_JSON_SEATS_NUMBER_NOT_NATURAL);
    }
}
