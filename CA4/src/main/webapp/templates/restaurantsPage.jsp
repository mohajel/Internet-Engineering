<%@include file="header.jsp" %>
<%@include file="scripts.jsp" %>

<br><br>
<form action="/restaurants" method="POST">
    <label>Search:</label>
    <input type="text" name="search" value="" required>
    <button type="submit" name="action" value="search_by_type">Search By Type</button>
    <button type="submit" name="action" value="search_by_name">Search By Name</button>
    <button type="submit" name="action" value="search_by_city">Search By City</button>
</form>
<form action="/restaurants" method="POST">
    <button type="submit" name="action" value="clear">Clear Search</button>
</form>
<br><br>
<!-- <form action="" method="POST">
    <label>Sort By:</label>
    <button type="submit" name="action" value="sort_by_rate">Overall Score</button>
</form> -->
<br><br>
<table id="table" class="table table-striped .table-hover table-dark" style="opacity: 0.9;">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>City</th>
        <th>Type</th>
        <th>Time</th>
        <th>Service Score</th>
        <th>Food Score</th>
        <th>Ambiance Score</th>
        <th>Overall Score</th>
    </tr>
</table>

<script>
    var insideTag = context.restaurants;
    var table = document.getElementById("table");
    table.innerHTML = table.innerHTML + insideTag;
</script>

<%@include file="footer.jsp" %>
  