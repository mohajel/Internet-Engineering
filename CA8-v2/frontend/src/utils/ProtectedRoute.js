import React from 'react';
import { useEffect, useState } from 'react';
import { getReq } from './api';
import MessagePage from '../views/MessagePage';
import { useActionData } from 'react-router-dom';
import { useLayoutEffect } from 'react';


export const AuthenticationLayer = Object.freeze({ 
    LOGOUT: 0, 
    MANAGER_LOGIN: 1, 
    USER_LOGIN: 2, 
    EVERYONE: 3,
    NO_ONE: 4,
    LOGGED_IN: 5
}); 

const ProtectedRoute = ({ accessType, children }) => {

    const [info, setInfo] = useState([]);
    const [loading, setLoading] = useState(true);
    const [userStatus, setUserStatus] = useState(AuthenticationLayer.NO_ONE);
  
    useLayoutEffect(() => {
      getReq("/status")
  
        .then(response => {
          console.log(response);
          setInfo(response);
          setLoading(false);
  
          if (response.status == "loggedOut") {
            setUserStatus(AuthenticationLayer.LOGOUT);
          } else if (response.role == "MANAGER") {
            setUserStatus(AuthenticationLayer.MANAGER_LOGIN);
          } else if (response.role == "CLIENT") {
            setUserStatus(AuthenticationLayer.USER_LOGIN);
          }
        });
  
    }, []);

    // return ( JSON.stringify(info) + " " + userStatus + " " + accessType );

    if (loading) {
        return <div>Loading...</div>;
    } else if (accessType == AuthenticationLayer.LOGOUT) { //OK
        return children;

        // if (userStatus == AuthenticationLayer.LOGOUT) {
        //     return children;
        // } else {
        //     return <MessagePage type="info" message="--- You are already logged in" redirectURL="/" />;
        // }
    } else if (accessType == AuthenticationLayer.MANAGER_LOGIN) {
        if (userStatus == AuthenticationLayer.MANAGER_LOGIN) { // OK
            return children;
        } else {
            return <MessagePage type="error" message="--- Please Login as Manager" redirectURL="/" />;
        }

    } else if (accessType == AuthenticationLayer.USER_LOGIN) { //OK
        if (userStatus == AuthenticationLayer.USER_LOGIN) {
            return children;
        } else {
            return <MessagePage type="error" message="--- Please Login as User" redirectURL="/" />;
        }

    } else if (accessType == AuthenticationLayer.EVERYONE) { //OK
        return children;

    } else if (accessType == AuthenticationLayer.NO_ONE) { //OK
        return <MessagePage type="error" message="--- You are not authorized" redirectURL="/" />;

    } else if (accessType == AuthenticationLayer.LOGGED_IN) { //OK
        if (userStatus == AuthenticationLayer.LOGOUT) {
            return <MessagePage type="info" message="--- You are not logged in" redirectURL="/login" />;
        } else {
            return children;
        }
    } else {
        return (<div>Access Denied</div>)
    }
};

export default ProtectedRoute;