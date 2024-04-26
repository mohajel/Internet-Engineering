import React from 'react';
import "../resources/styles/login_page.css"
import { useNavigate } from 'react-router-dom';

import Header from './Header';


function LoginPage() {
    const  navigate  = useNavigate();

    return (
        <button onClick={() => navigate("/")}>Go to Home</button>
    );

    // return (
    //     <div class="d-flex flex-column">
    //         <Header />
    //         <main class="flex-grow-1">
            
    //             {/* create a button that goes to HomePage */}

    //             <div class="container-s w-100 text-center">
    //                 <div class="home-background w-100">
    //                     <div class="container">
    //                         <div class="input-group mb-3">
    //                             <img class="big-logo" src={require("../resources/images/logo.png")} />
    //                         </div>
    //                         <div class="input-group mb-1 text-center">
    //                             <input type="text" class="search-input restaurant-search rounded-4" placeholder=" Username " aria-label="restaurant search" aria-describedby="basic-addon2" />
    //                             <input type="text" class="search-input restaurant-search rounded-4" placeholder=" Password " aria-label="restaurant search" aria-describedby="basic-addon2" />
    //                             <button class="btn search-input btn-outline-secondary rounded-4 search-button" type="button">
    //                                 Login
    //                             </button>
    //                         </div>
    //                     </div>
    //                 </div>
    //             </div>
    //         </main>

    //         <footer class="text-center p-2 m-0 mt-5" id="footer">
    //             <p class="m-0">Copyright &copy; 2024 Mizdooni - All rights reserved.</p>
    //         </footer>
    //     </div>

    // );
}

export default LoginPage;