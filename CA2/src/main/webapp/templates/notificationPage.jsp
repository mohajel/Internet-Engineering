
<% JSONObject notification = (JSONObject) request.getAttribute("notification"); %>
<%@include file="header.jsp" %>

<%@include file="scripts.jsp" %>

<script>
    const notification = JSON.parse('<%= notification.toString() %>');
</script>


<script>
    let timerInterval;
    Swal.fire({
        title: notification.type,
        text: notification.message,
        icon: notification.type,
        timer: 2000,
        timerProgressBar: true,
        didOpen: () => {
            Swal.showLoading();
        },
        willClose: () => {
            clearInterval(timerInterval);
        }
    }).then((result) => {

        if (result.dismiss === Swal.DismissReason.timer) {
        console.log("redirecting to " + notification.redirectURL);
        window.location.href = notification.redirectURL;
        }
    });
</script>

<%@include file="footer.jsp" %>
