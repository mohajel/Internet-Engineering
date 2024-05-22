import React, { useLayoutEffect } from 'react';
import { useEffect, useState } from 'react';
import Cookies from 'universal-cookie';

import { postReq, getReq } from '../utils/api';

import MessagePage from './MessagePage';


function Logout() {

    const cookie = new Cookies();
    const [logoutData, setLogoutData] = useState(null);
    const [showError, setShowError] = useState(false);

    const sendLogoutReq = async (event) => {
        postReq('/logout', {})
            .then(response => {
                setLogoutData(response);
                if (response.success == true) {
                    cookie.remove("JWT")
                }
                setShowError(true)
            });
    };

    useEffect(() => {
        sendLogoutReq();
    }, []);

    return (
        <div>
            {
                showError ?
                    (<MessagePage type="info" message="Logging out" redirectURL="/" />)
                    :
                    <div>Loading...</div>
            }
        </div>
    );
}

export default Logout;