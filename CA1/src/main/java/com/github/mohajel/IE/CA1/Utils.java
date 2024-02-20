package com.github.mohajel.IE.CA1;

import org.json.JSONObject;

public class Utils {

    public JSONObject pharseLine(String line) {
        int separatorIndex = line.indexOf("{");
        String command = line.substring(0, separatorIndex).trim();
        String dataStringForm = line.substring(separatorIndex);

        JSONObject input = new JSONObject();
        input.put("command", command);
        input.put("data", new JSONObject(dataStringForm));

        System.out.println("command:" + input.getString("command"));
        System.out.println("json:" + input.getJSONObject("data").toString());

        return input;
    }
}
