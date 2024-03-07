
<%@include file="header.jsp" %>
    

<h1>Welcome {user_name} <a href="/logout" style="color: red">Log Out</a></h1>

<h2>Your Restaurant Information:</h2>
<div id="show_info"></div>

<table class="table table-striped table-dark" id="restaurant_info_table_id">
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


<table border="1" cellpadding="10">
    <tr>
        <td>

        <h3>Add Table:</h3>
        <form method="post" action="">
            <label>Table Number:</label>
            <input name="table_number" type="number" min="0"/>
            <br>
            <label>Seats Number:</label>
            <input name="seats_number" type="number" min="1"/>
            <br>
            <button type="submit">Add</button>
        </form>

        </td>
    </tr>
</table>


<!-- <button id="btn" onclick="convert( )"> Click Here </button> <br>
<h3> Resulting Table: </h3>
<div id="container"></div> -->

<%@include file="scripts.jsp" %>

<script>
    let c = document.getElementById("show_info");
    c.innerHTML = JSON.stringify(context.data.restaurant);
    c.innerHTML = c.innerHTML + "Tables:" + JSON.stringify(context.data.tables);
</script>

<script>
    function addColumnsToRestaurantTable()
    {
        // Get the table element
        var table = document.getElementById("restaurant_info_table_id");
        var jsonObject = context.data.restaurant;

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
        addColumnsToRestaurantTable();
    }
</script>

<script>
 
</script>

<script>
    // alert("Welcome to Mizdooni");
    // Swal.fire({
    //   title: 'Welcome to Mizdooni',
    //   text: 'Please login to continue',
    //   icon: 'info',
    //   confirmButtonText: 'Login'
    // })
  </script>

<%@include file="footer.jsp" %>