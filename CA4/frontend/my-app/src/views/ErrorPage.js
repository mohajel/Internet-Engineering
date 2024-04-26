import React from 'react';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';

import "../resources/styles/error_page.css"

import Header from './Header';


function ErrorPage(props) {
    const navigate = useNavigate();
    const timerPeriod = 5000;

    // var props = {
    //     type: 'error',
    //     message: 'An error occurred. Redirecting to home page...',
    //     redirectURL: '/'
    // };

    let timerInterval;
    Swal.fire({
        title: props.type,
        text: props.message,
        icon: props.type,
        timer: timerPeriod,
        timerProgressBar: true,
        didOpen: () => {
            Swal.showLoading();
        },
        willClose: () => {
            clearInterval(timerInterval);
        }
    }).then((result) => {

        if (result.dismiss === Swal.DismissReason.timer) {
            console.log("redirecting to " + props.redirectURL);
            navigate(props.redirectURL);
            //   window.location.href = props.redirectURL;
        }
    });

    return (
        <div class="d-flex flex-column">
            <Header />  
            <main class="flex-grow-1 home-background w-100">
                <div class="container ">
                    <div class="row ">
                        <div class="col-12 text-center">
                            {/* <h1 class="display-1 mt-5">404</h1>
                            <h2 class="display-4">Page Not Found</h2>
                            <p class="lead">
                                We are sorry, the page you are looking for is not available.
                            </p>
                            <a href="index.html" class="btn btn-primary">Go to Home</a> */}
                        </div>
                    </div>
                </div>
            </main>

            <footer class="fixed-bottom text-center p-2 m-0 mt-5" id="footer">
                <p class="m-0">Copyright &copy; 2024 Mizdooni - All rights reserved.</p>
            </footer>
        </div>
    );
}

export default ErrorPage;