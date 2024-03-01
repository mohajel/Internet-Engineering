<%@ page import="org.json.JSONObject" %>

<!DOCTYPE html>
<html>
    <head>
        
        <link rel=stylesheet href=https://cdn.jsdelivr.net/npm/pretty-print-json@2.1/dist/css/pretty-print-json.css>
        
        <title>JSONSOJSS_KIOSJ</title>
</head>
<body>
    <h1>JSON JSON JSON</h1>
    
    <% String message = (String) request.getAttribute("message"); %>
    <% JSONObject obj = (JSONObject) request.getAttribute("data"); %>


    <p>Message from the servlet: <%= message %></p>
    
    <%-- Using JSP EL --%>
    <p>Message from the servlet: ${message}</p>

    <h2>
        <%= obj.getString("name1") %>
    </h2>


    <h3>
        <%= obj.toString() %>
    </h3>

    <pre id=account class=json-container></pre>

    <script src=https://cdn.jsdelivr.net/npm/pretty-print-json@2.1/dist/pretty-print-json.min.js></script>
    
    <script>
        const data = { active: true, mode: '🚃', codes: [48348, 28923, 39080], city: 'London' };
        const elem = document.getElementById('account');
        elem.innerHTML = prettyPrintJson.toHtml(<%= obj.toString() %>);
    </script>


</body>
</html>
