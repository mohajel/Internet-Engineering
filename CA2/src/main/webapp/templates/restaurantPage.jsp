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

  <br>

  <div class="row">
    <div class="col">
      <h2>Restaurant Info:</h2>
      <table class="table table-striped .table-hover table-dark" style="opacity: 0.9;">
        <tr>
          <td><b>Id:</b></td>
          <td>1</td>
        </tr>
        <tr>
          <td><b>Name:</b></td>
          <td><%= restaurant.getString("name") %></td>
        </tr>
        <tr>
          <td><b>Type:</b></td>
          <td><%= restaurant.getString("type") %></td>
        </tr>
        <tr>
          <td><b>Time:</b></td>
          <td><%= restaurant.getString("startTime") %> - <%= restaurant.getString("endTime") %></td>
        </tr>
        <tr>
          <td><b>Scores:</b></td>
          <td></td>
        </tr>
        <tr>
          <td></td>
          <td>
            <table>
              <tr>
                <td>Food:</td>
                <td><%= rate.getDouble("foodRate") %></td>
              </tr>
              <tr>
                <td>Service:</td>
                <td><%= rate.getDouble("serviceRate") %></td>
              </tr>
              <tr>
                <td>Ambiance:</td>
                <td><%= rate.getDouble("ambianceRate") %></td>
              </tr>
              <tr>
                <td>Overall:</td>
                <td><%= rate.getDouble("overallRate") %></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td><b>Address:</b></td>
          <td><%= restaurant.getJSONObject("address").getString("street") %>, <%= restaurant.getJSONObject("address").getString("city") %>, <%= restaurant.getJSONObject("address").getString("country") %></td>
        </tr>
        <tr>
          <td><b>Description:</b></td>
          <td><%= restaurant.getString("description") %></td>
        </tr>
      </table>
    
    </div>
    <div class="col">
      <h2>Reserve Table:</h2>
      <form action="" method="post">
        <label>Table:</label>
        <select id="table_number" name="table_number">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
        </select>
        <label>Date & Time:</label>
        
        <input type="datetime-local" id="date_time" name="date_time">
        <br>
        <button type="submit" name="action" value="reserve">Reserve</button>
      </form>

      <br> <br> <br>
      <h2>Feedback:</h2>
      
      <form action="" method="post">                
        <label>Food Rate:</label>
        <input type="number" id="food_rate" name="food_rate" step="0.1" min="0" max="5">
        <label>Service Rate:</label>
        <input type="number" id="service_rate" name="service_rate" step="0.1" min="0" max="5">
        <label>Ambiance Rate:</label>
        <input type="number" id="ambiance_rate" name="ambiance_rate" step="0.1" min="0" max="5">
        <label>Overall Rate:</label>
        <input type="number" id="overall_rate" name="overall_rate" step="0.1" min="0" max="5">
        <br>
        <label>Comment:</label>
        <textarea name="comment"  id="" cols="30" rows="5" placeholder="Enter your comment"></textarea>
        <!-- <input type="textarea" name="comment" value="" /> -->
        <br>
        <button type="submit" name="action" value="feedback">Submit</button>
      </form>

    </div>
  </div>



  <br>
  
  <br/>
  <table class="table table-striped .table-hover table-dark" style="opacity: 0.9;">
    <h2>Feedbacks</h2>
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
    const restaurant = JSON.parse('<%= restaurant.toString() %>'); 
  </script>
  
  <%@include file="footer.jsp" %>
    