
<%@include file="header.jsp" %>
    

<h1>Welcome {user_name} <a href="/logout" style="color: red">Log Out</a></h1>

<h2>Your Restaurant Information:</h2>
<div id="show_info"></div>

<!-- <ul>
    <li id="id">Id: 1</li>
    <li id="name">Name: Fast Food</li>
    <li id="type">Type: Italian</li>
    <li id="time">Time: 08:00 - 23:00</li>
    <li id="description">Description: "Best food you can eat"</li>
    <li id="address">Address: North Kargar, Tehran, Iran</li>
    <li id="tables">Tables:</li>
    <ul>
        <li>table1</li>
        <li>table2</li>
        <li>table3</li>
        <li>table4</li>
        <li>table5</li>
    </ul>
</ul> -->

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

   // Function to convert JSON data to HTML table
   function convert() {
      
      // Sample JSON data
    //   let jsonData = [
    //      {
    //         name: "Saurabh",
    //         age: "20",
    //         city: "Prayagraj"
    //      },
    //      {
    //         name: "Vipin",
    //         age: 23,
    //         city: "Lucknow",
    //      },
    //      {
    //         name: "Saksham",
    //         age: 21,
    //         city: "Noida"
    //      }
    //   ];

      let jsonData = context.data.restaurant;
      
      // Get the container element where the table will be inserted
      let container = document.getElementById("container");
      
      // Create the table element
      let table = document.createElement("table");
      
      // Get the keys (column names) of the first object in the JSON data
      let cols = Object.keys(jsonData[0]);
      
      // Create the header element
      let thead = document.createElement("thead");
      let tr = document.createElement("tr");
      
      // Loop through the column names and create header cells
      cols.forEach((item) => {
         let th = document.createElement("th");
         th.innerText = item; // Set the column name as the text of the header cell
         tr.appendChild(th); // Append the header cell to the header row
      });
      thead.appendChild(tr); // Append the header row to the header
      table.append(tr) // Append the header to the table
      
      // Loop through the JSON data and create table rows
      jsonData.forEach((item) => {
         let tr = document.createElement("tr");
         
         // Get the values of the current object in the JSON data
         let vals = Object.values(item);
         
         // Loop through the values and create table cells
         vals.forEach((elem) => {
            let td = document.createElement("td");
            td.innerText = elem; // Set the value as the text of the table cell
            tr.appendChild(td); // Append the table cell to the table row
         });
         table.appendChild(tr); // Append the table row to the table
      });
      container.appendChild(table) // Append the table to the container element
   }
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