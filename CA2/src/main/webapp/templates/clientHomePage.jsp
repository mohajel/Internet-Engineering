<%@include file="header.jsp" %>

<%@include file="scripts.jsp" %>

<script>;
    const navbar =  document.getElementById('navbar_id');
    navbar.innerHTML = navbar.innerHTML +
        '<li class="nav-item">' +
        '<a class="nav-link" href="templates/restaurantsPage.jsp">Restaurants</a>' +
        '</li>' +
        '<li class="nav-item">' +
        '<a class="nav-link" href="/reservations">Reservations</a>' +
        '</li>';
</script>

<%@include file="footer.jsp" %>