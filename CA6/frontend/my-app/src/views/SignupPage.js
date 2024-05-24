import React from 'react';
import { useEffect, useState } from 'react';
import { postReq, getReq } from '../utils/api';


import "../resources/styles/login_page.css"


import Header from './Header';
import MessagePage from './MessagePage';


function SignupPage() {

    const [signupData, setSignupData] = useState(null);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [role, setRole] = useState('');
    const [showMessage, setShowMessage] = useState(false);

    const githubClientId = "92b571684150ec1038aa"
    const githubURL = "https://github.com/login/oauth/authorize?client_id=" + githubClientId

    const handleClick = async (event) => {
        event.preventDefault();
        postReq('/users/signup', { username: username, password: password, email: email, role: role })
            .then(response => {
                setSignupData(response);
                setShowMessage(true)
            });
    }

    return (
        <div>
            {showMessage ? (<MessagePage type={signupData.success == true ? "success" : "error"}
                message={signupData.success == true ? "Signup Sucessful" : signupData.data.error} redirectURL={signupData.success == true ? "/" : "/signup"} />)
                : (
                    <div class="d-flex flex-column">
                        <Header buttonText="Login" navigateURL="/login" />
                        <main class="flex-grow-1">

                            <div class="container-s w-100 text-center">
                                <div class="home-background w-100">
                                    <div class="container">
                                        <div class="input-group mb-3">
                                            <img class="big-logo" src={require("../resources/images/logo.png")} alt="logo" />
                                        </div>

                                        <form onSubmit={handleClick} class="input-group mb-1 text-center" autocomplete="on">

                                            <input type="text" value={username} onChange={(event) => setUsername(event.target.value)} class="search-input restaurant-search rounded-4 mb-3" placeholder=" Username " aria-label="restaurant search" aria-describedby="basic-addon2" />
                                            <input type="text" value={email} onChange={(event) => setEmail(event.target.value)} class="search-input restaurant-search rounded-4 mb-3" placeholder=" Email " aria-label="restaurant search" aria-describedby="basic-addon2" />
                                            <input type="text" value={role} onChange={(event) => setRole(event.target.value)} class="search-input restaurant-search rounded-4 mb-3" placeholder=" Role " aria-label="restaurant search" aria-describedby="basic-addon2" />
                                            <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} class="search-input restaurant-search rounded-4 mb-3" placeholder=" Password " aria-label="restaurant search" aria-describedby="basic-addon2" />
                                            <button class="btn search-input btn-outline-secondary rounded-4 search-button" type="submit">
                                                Signup
                                            </button>
                                        </form>

                                        <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />

                                        <a href={githubURL} class="btn search-input btn-outline-secondary rounded-4 search-button ">
                                                Signin Using Github
                                        </a>

                                    </div>
                                </div>
                            </div>
                        </main>

                        <footer class="text-center p-2 m-0 mt-5" id="footer">
                            <p class="m-0">Copyright &copy; 2024 Mizdooni - All rights reserved.</p>
                        </footer>
                    </div>
                )}
        </div>
    );
}

export default SignupPage;