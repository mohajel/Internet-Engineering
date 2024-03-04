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

<script>;
    const navbar =  document.getElementById('navbar_id');
    navbar.innerHTML = navbar.innerHTML +
        '<li class="nav-item">' +
        '<a class="nav-link" href="/restaurants">Restaurants</a>' +
        '</li>' +
        '<li class="nav-item">' +
        '<a class="nav-link" href="/reservations">Reservations</a>' +
        '</li>';
</script>

<%@include file="footer.jsp" %>