<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/vanillajs-datepicker@1.1.2/dist/js/datepicker-full.min.js"></script>
<script src="https://unpkg.com/notie"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.js"></script>


<script>
    const context = JSON.parse('<%= context.toString() %>');
    console.log(context);

    if (context.title !== undefined)
        document.title = context.title;

    const name_container = document.getElementById("user_name_container_id");
    const logout_btn = document.getElementById("logout_btn_id");


    if (context.data == undefined || context.data.username == undefined)
    {
        name_container.innerHTML = "Mizdooni";
        logout_btn.style.display = "none";
    } else {
        name_container.innerHTML = context.data.username;
    }
</script>

