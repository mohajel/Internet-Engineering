
<%@include file="header.jsp" %>

<h1>HOME</h1>
<% String message = (String) request.getAttribute("message"); %>
<p>Message from the servlet: <%= message %></p>
<p>Message from the servlet: ${message}</p>

<%@include file="scripts.jsp" %>

<script>
  alert("Welcome to Mizdooni");
</script>

<%@include file="footer.jsp" %>
