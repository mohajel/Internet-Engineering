import React from 'react';
import { useEffect, useState } from 'react';
import { postReq, getReq } from '../utils/api';
// import Cookies from 'universal-cookie';


import "../resources/styles/login_page.css"
import { useNavigate, useLocation } from 'react-router-dom';

import Header from './Header';
import MessagePage from './MessagePage';


function AuthPage() {



    // const cookie = new Cookies();
    const location = useLocation();

    const [loginData, setLoginData] = useState(null);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [showError, setShowError] = useState(false);


    const searchParams = new URLSearchParams(location.search);
    const code = searchParams.get('code');

    const [info, setInfo] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getReq("/auth/github/" + code)
            .then(response => {
                console.log(response);
                setInfo(response);
                setLoading(false);
            });
    }, []);

    return (
        <>
            {
                loading ?
                    <div>Loading...</div>
                    :
                    <h1>
                        salam {info.code}
                    </h1>
            }
        </>
    );

}

export default AuthPage;