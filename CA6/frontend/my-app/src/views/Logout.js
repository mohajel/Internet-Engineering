import React, { useLayoutEffect } from 'react';
import { useEffect, useState } from 'react';
import Cookies from 'universal-cookie';

import { postReq, getReq } from '../utils/api';

import MessagePage from './MessagePage';


function Logout() {

    const cookie = new Cookies();
    cookie.remove("JWT", { path: '/'});

    const [logoutData, setLogoutData] = useState(null);
    const [showError, setShowError] = useState(true);
    
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