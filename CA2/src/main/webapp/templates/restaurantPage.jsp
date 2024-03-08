<%@ page import="org.json.JSONArray" %>
<%@ page import="com.github.mohajel.IE.CA2.models.Review" %>
<%@include file="header.jsp" %>

<%
  JSONObject restaurant = context.getJSONObject("restaurantData").getJSONObject("restaurant");
  JSONObject rate = context.getJSONObject("restaurantData").getJSONObject("rate");
  JSONArray reviews = context.getJSONObject("restaurantData").getJSONArray("reviews");
%>

<%
  StringBuilder reviewsTableHTML = new StringBuilder();
  for (int i = 0; i < reviews.length(); i++) {
    JSONObject review = reviews.getJSONObject(i);
    reviewsTableHTML.append("<tr>\n");
    reviewsTableHTML.append("\t<td>\n").append(review.getString("username")).append("</td>\n");
    reviewsTableHTML.append("\t<td>\n").append(review.getString("comment")).append("</td>\n");
    reviewsTableHTML.append("\t<td>\n").append(review.getString("reviewDate")).append("</td>\n");
    reviewsTableHTML.append("\t<td>\n").append(review.getDouble("foodRate")).append("</td>\n");
    reviewsTableHTML.append("\t<td>\n").append(review.getDouble("serviceRate")).append("</td>\n");
    reviewsTableHTML.append("\t<td>\n").append(review.getDouble("ambianceRate")).append("</td>\n");
    reviewsTableHTML.append("\t<td>\n").append(review.getDouble("overallRate")).append("</td>\n");
    reviewsTableHTML.append("</tr>\n");
  }
%>

  <p id="username">username: ali <a href="/">Home</a> <a href="/logout" style="color: red">Log Out</a></p>
  <br>
  <h2>Restaurant Info:</h2>
  <ul>
    <li id="id">Id: 1</li>
      <li id="name">Name: <%= restaurant.getString("name") %></li>
      <li id="type">Type: <%= restaurant.getString("type") %></li>
      <li id="time">Time: <%= restaurant.getString("startTime") %> - <%= restaurant.getString("endTime") %></li>
      <li id="rate">Scores:</li>
      <ul>
        <li>Food: <%= rate.getDouble("foodRate") %></li>
        <li>Service: <%= rate.getDouble("serviceRate") %></li>
        <li>Ambiance: <%= rate.getDouble("ambianceRate") %></li>
        <li>Overall: <%= rate.getDouble("overallRate") %></li>
      </ul>
      <li id="address">Address: <%= restaurant.getJSONObject("address").getString("street") %>, <%= restaurant.getJSONObject("address").getString("city") %>, <%= restaurant.getJSONObject("address").getString("country") %></li>
      <li id="description">Description: <%= restaurant.getString("description") %></li>
  </ul>


  <table border="1" cellpadding="10">
    <tr>
        <td>
            <label>Reserve Table:</label>
            <form action="" method="post">
              <label>Table:</label>
              <select id="table_number" name="table_number">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
              </select>
              <label>Date & Time:</label>
              <!-- i want date output to be in this format 2024-02-27 09:00 and only round hours -->
              
              <input type="datetime-local" id="date_time" name="date_time" required>
              <br>
              <button type="submit" name="action" value="reserve">Reserve</button>
            </form>
        </td>
    </tr>
</table>

  <table border="1" cellpadding="10">
    <tr>
        <td>
            <label>Feedback:</label>
            <form action="" method="post">                
              <label>Food Rate:</label>
              <input type="number" id="food_rate" name="food_rate" step="0.1" min="0" max="5" required>
              <label>Service Rate:</label>
              <input type="number" id="service_rate" name="service_rate" step="0.1" min="0" max="5" required>
              <label>Ambiance Rate:</label>
              <input type="number" id="ambiance_rate" name="ambiance_rate" step="0.1" min="0" max="5" required>
              <label>Overall Rate:</label>
              <input type="number" id="overall_rate" name="overall_rate" step="0.1" min="0" max="5" required>
              <br>
              <label>Comment:</label>
              <textarea name="comment"  id="" cols="30" rows="5" placeholder="Enter your comment" required></textarea>
              <!-- <input type="textarea" name="comment" value="" /> -->
              <br>
              <button type="submit" name="action" value="feedback">Submit</button>
            </form>
        </td>
    </tr>
</table>

  


  <br>
  
  <br/>
  <table style="width: 100%; text-align: center;" border="1">
    <caption><h2>Feedbacks</h2></caption>
    <tr>
      <th>Username</th>
      <th>Comment</th>
      <th>Date</th>
      <th>Food Rate</th>
      <th>Service Rate</th>
      <th>Ambiance Rate</th>
      <th>Overall Rate</th>
    </tr>
    <%= reviewsTableHTML%>
  </table>
  <br><br>
    

  <%@include file="scripts.jsp" %>

  <script>
  </script>
  
  <%@include file="footer.jsp" %>
    