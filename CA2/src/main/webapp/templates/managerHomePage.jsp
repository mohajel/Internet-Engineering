
<%@include file="header.jsp" %>
    
<h2>Your Restaurant Information:</h2>
<div id="show_info"></div>

<table class="table table-striped .table-hover table-dark" style="opacity: 0.9;" id="restaurant_info_table_id">
    <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Address</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Type</th>
            <th>Manager</th>
        </tr>
    </thead>
    <tbody>
        <!-- Existing rows will be inserted here -->
    </tbody>
</table>

<br>  <br>  <br>  <br> 

<div class="row">
    <div class="col">
        <div class="form-group">
            <h3>Add Table:</h3>
            <form method="get" action="/addTable">
                <label>Table Number:</label>
                <input name="table_number" type="number" min="0"/>
                <br>
                <label>Seats Number:</label>
                <input name="seats_number" type="number" min="1"/>
                <br>
                <label>Restaurant Name</label>
                <input name="restaurant_name" required/>
                <br>
                <button type="submit">Add</button>
            </form>
        </div>
    </div>
    <div class="col">
        <table class="table table-striped .table-hover table-dark" style="opacity: 0.9;"  id="managers_table_info_table_id">
            <thead>
                <tr>
                    <th>Restaurant Name</th>
                    <th>Table Number</th>
                    <th>Seats Number</th>
                </tr>
            </thead>
            <tbody>
                <!-- Existing rows will be inserted here -->
            </tbody>
        </table>
    </div>
  </div>




<%@include file="scripts.jsp" %>

<script>
    let c = document.getElementById("show_info");
    c.innerHTML = JSON.stringify(context.data.restaurant);
    c.innerHTML = c.innerHTML + "Tables:" + JSON.stringify(context.data.tables);
</script>

<script>
    function addColumnsToRestaurantTable(jsonObject)
    {
        // Get the table element
        var table = document.getElementById("restaurant_info_table_id");
        // var jsonObject = context.data.restaurant;

        // Create a new row
        var newRow = table.insertRow();

        // Add cells to the row
        var nameCell = newRow.insertCell(0);
        nameCell.innerHTML = jsonObject.name;

        var descriptionCell = newRow.insertCell(1);
        descriptionCell.innerHTML = jsonObject.description;

        var addressCell = newRow.insertCell(2);
        addressCell.innerHTML = jsonObject.address.country + ", " + jsonObject.address.city + ", " + jsonObject.address.street;

        var startTimeCell = newRow.insertCell(3);
        startTimeCell.innerHTML = jsonObject.startTime;

        var endTimeCell = newRow.insertCell(4);
        endTimeCell.innerHTML = jsonObject.endTime;

        var typeCell = newRow.insertCell(5);
        typeCell.innerHTML = jsonObject.type;

        var managerCell = newRow.insertCell(6);
        managerCell.innerHTML = jsonObject.managerUsername;
    }
</script>

<script>
    var table = document.getElementById("restaurant_info_table_id");
    var size = Object.keys(context.data.restaurant).length;
    if (size === 0) {
        table.style.display = "none";
    } else{
        for (var i = 0; i < size; i++) {
            addColumnsToRestaurantTable(context.data.restaurant[i]);
        }
    }
</script>

<script>
    function addColumnsToManagersTable(restaurantTable, i)
    {
        

        // Get the table element
        var table = document.getElementById("managers_table_info_table_id");
        // var restaurantTable = context.data.restaurant;

        // Create a new row
        var newRow = table.insertRow();

        // Add cells to the row
        var restaurantNameCell = newRow.insertCell(0);
        restaurantNameCell.innerHTML = restaurantTable.restaurantName;

        var tableNumberCell = newRow.insertCell(1);
        tableNumberCell.innerHTML = restaurantTable.tableNumber;

        var seatsNumberCell = newRow.insertCell(2);
        seatsNumberCell.innerHTML = restaurantTable.seatsNumber;

    }
 
</script>

<script>
    var table = document.getElementById("managers_table_info_table_id");
    var size = Object.keys(context.data.tables).length;
    if (size === 0) {
        table.style.display = "none";
    } else{

        for(const x in context.data.tables)
        {
            console.log(context.data.tables[x]);
            addColumnsToManagersTable(context.data.tables[x], 0);  
        }
    }
</script>

<%@include file="footer.jsp" %>