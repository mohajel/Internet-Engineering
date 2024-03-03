<%@include file="header.jsp" %>


<h1>Welcome {user_name} <a href="/logout" style="color: red">Log Out</a></h1>
<ul type="square">
    <li>
        <a href="/restaurants">Restaurants</a>
    </li>
    <li>
        <a href="/reservations">Reservations</a>
    </li>
</ul>

<%@include file="scripts.jsp" %>

<script>

</script>

<%@include file="footer.jsp" %>