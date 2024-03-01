<!DOCTYPE html>
<html>
<head>
    <title>My JSP File</title>
</head>
<body>
    <h1>HOME</h1>
    
    <%-- Using JSP scriptlets --%>
    <% String message = (String) request.getAttribute("message"); %>
    <p>Message from the servlet: <%= message %></p>
    
    <%-- Using JSP EL --%>
    <p>Message from the servlet: ${message}</p>
</body>
</html>
