<%@include file="header.jsp" %>

  <h1>Welcome to Mizdooni</h1>
  <form method="post" action="">
    <label>Username:</label>
    <input name="username" type="text" />
    <br>
    <label>Password:</label>
    <input name="password" type="password" />
    <br>
    <button type="submit" class="btn btn-dark">Login!</button>
  </form>

<%@include file="scripts.jsp" %>

  <script>
    // alert("Welcome to Mizdooni");
    // Swal.fire({
    //   title: 'Welcome to Mizdooni',
    //   text: 'Please login to continue',
    //   icon: 'info',
    //   confirmButtonText: 'Login'
    // })
  </script>

<%@include file="footer.jsp" %>
