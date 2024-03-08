<%@ page import="org.json.JSONArray" %>
<%@include file="header.jsp" %>

<%@include file="scripts.jsp" %>

<%
    JSONArray reservations = context.getJSONObject("ReservationData").getJSONObject("data").getJSONArray("reservationHistory");
%>

<%

    StringBuilder reservationTableHTML = new StringBuilder();
    for (int i = 0; i < reservations.length(); i++) {
        JSONObject reservation = reservations.getJSONObject(i);
        reservationTableHTML.append("<tr>\n");
        reservationTableHTML.append("\t<td>").append(reservation.getInt("reservationNumber")).append("</td>\n");
        String link = "/restaurant?name=" + reservation.getString("restaurantName");
        reservationTableHTML.append("\t<td><a href=").append(link).append(">").append(reservation.getString("restaurantName")).append("</a></td>\n");
        reservationTableHTML.append("\t<td>").append(reservation.getInt("tableNumber")).append("</td>\n");
        reservationTableHTML.append("\t<td>").append(reservation.getString("datetime")).append("</td>\n");
        reservationTableHTML.append("\t<td>\n");
        reservationTableHTML.append("\t\t<form action=\"\" method=\"POST\">\n");
        reservationTableHTML.append("\t\t\t<button type=\"submit\" name=\"action\" value=\"").append(reservation.getInt("reservationNumber")).append("\">Cancel This</button>\n");
        reservationTableHTML.append("\t\t</form>\n");
        reservationTableHTML.append("\t</td>\n");
        reservationTableHTML.append("</tr>\n");
    }
%>

    <h1>Your Reservations:</h1>
    <br><br>
    <br><br>
    <table style="width:100%; text-align:center;" border="1">
        <tr>
            <th>Reservation Id</th> 
            <th>Restaurant Name</th>
            <th>Table Number</th> 
            <th>Date & Time</th>
            <th>Canceling</th>
        </tr>
        <%= reservationTableHTML %>
    </table>

<%@include file="footer.jsp" %>