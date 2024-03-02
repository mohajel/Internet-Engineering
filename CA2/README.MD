
# Json Format


## NO_ERROR:

        output.put("success", true);
        output.put("title", "Login");
        output.put("data", /any data big or small/);
        output.put("message", "login");
        output.put("icon", "warning/success");

## ERROR

    output.put("success", false);
    output.put("title", "Login");
    output.put("data", {"error" : "ERROR_MSG"});
    output.put("message", "login"); //not necessary
