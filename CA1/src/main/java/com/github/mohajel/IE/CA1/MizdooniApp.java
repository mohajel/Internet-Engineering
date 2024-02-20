package com.github.mohajel.IE.CA1;

import org.json.*;;

class MizdooniApp {
    public String function1(JSONObject json) {
        // Implement your logic for function1
        // Access the JSON values using the org.json library
        String value1 = json.getString("value1");
        int value2 = json.getInt("value2");
        // ...

        // Process the values and return the output
        String output = "Function1 output: " + value1 + ", " + value2;
        return output;
    }

    public String function2(JSONObject json) {
        // Implement your logic for function2
        // Access the JSON values using the org.json library
        String value3 = json.getString("value3");
        boolean value4 = json.getBoolean("value4");
        // ...

        // Process the values and return the output
        String output = "Function2 output: " + value3 + ", " + value4;
        return output;
    }
}
