package com.github.mohajel.IE.CA8;

import static org.junit.Assert.assertEquals;
import org.json.JSONObject;
import org.junit.Test;

import com.github.mohajel.IE.CA8.utils.Utils;

public class UtilsTest {

    @Test
    public void pharseLineTestCommand() {
        String line = "addUser {'role': 'client', 'username': 'user1', 'password': '1234', 'email':'user1@gmail.com', 'address': {'country': 'Iran', 'city': 'Tehran'}}";
        JSONObject result = Utils.pharseLine(line);
        String command = result.getString("command");
        assertEquals(command, "addUser");
    }

    @Test
    public void pharseLineTestDataOneLayer() {
        String line = "addUser {'role': 'client', 'username': 'user1', 'password': '1234', 'email':'user1@gmail.com', 'address': {'country': 'Iran', 'city': 'Tehran'}}";
        JSONObject result = Utils.pharseLine(line);
        String countryAddress = result.getJSONObject("data").getJSONObject("address").getString("country");
        assertEquals(countryAddress, "Iran");
    }

    @Test
    public void pharseLineTestLineGetting() {
        String line = "{'role': 'client', 'username': 'user1', 'password': '1234', 'email':'user1@gmail.com', 'address': {'country': 'Iran', 'city': 'Tehran'}}";
        JSONObject obj = new JSONObject(line);
        String role = obj.getString("role");
        assertEquals(role, "client");
    }

    @Test
    public void pharseLineTestDataTwoLayer() {
        String line = "addUser {'role': 'client', 'username': 'user1', 'password': '1234', 'email':'user1@gmail.com', 'address': {'country': 'Iran', 'city': 'Tehran'}}";
        JSONObject result = Utils.pharseLine(line);
        String countryAddress = result.getJSONObject("data").getJSONObject("address").getString("country");
        assertEquals(countryAddress, "Iran");
    }
}
