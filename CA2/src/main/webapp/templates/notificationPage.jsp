
<% JSONObject notification = (JSONObject) request.getAttribute("notification"); %>
<%@include file="header.jsp" %>

<h1>INFO</h1>

<%@include file="scripts.jsp" %>

<script>
    const notification = JSON.parse('<%= notification.toString() %>');

//     if (context.success == false) { //error
//         Swal.fire({
//         title: 'OOPS!',
//         text: context.data.error,
//         icon: "error",
//         confirmButtonText: 'OK'
//     })
//     } else if (context.message !== undefined) { //message exists
//         Swal.fire({
//                 title: context.icon,
//                 text: context.message,
//                 icon: context.icon,
//                 confirmButtonText: 'OK'
//     })
//     }
//   alert("Welcome to Mizdooni");
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
