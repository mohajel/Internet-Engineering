import React, { Suspense } from 'react';

import { BrowserRouter, Routes, Route, } from "react-router-dom";
import { AuthenticationLayer } from './utils/ProtectedRoute';


import './App.css';
// import HomePage from './views/HomePage';

const HomePage = React.lazy(() => import('./views/HomePage'));
const MessagePage = React.lazy(() => import('./views/MessagePage'));
const LoginPage = React.lazy(() => import('./views/LoginPage'));
const TestPost = React.lazy(() => import('./views/TestPost'));
const LogoutPage = React.lazy(() => import('./views/Logout'));
const SearchPage = React.lazy(() => import('./views/SearchRestaurantPage'));
const ManagerRestaurantPage = React.lazy(() => import('./views/ManagerRestaurantPage'));
const RestaurantPage = React.lazy(() => import('./views/RestaurantPage'));
const ProtectedRoute = React.lazy(() => import('./utils/ProtectedRoute'));
const ManagerManagePage = React.lazy(() => import('./views/ManagerManagePage'));

function App() {
  // get state from the server
  // const [userStatus, setUserStatus] = React.useState(null);

  // React.useEffect(() => {
  //   fetch("/api/user")
  //     .then((res) => res.json())
  //     .then((data) => setUserStatus(data.isLoggedIn));
  // }
  // , []);

  // if (userStatus === null) {
  //   return <div>Loading...</div>;
  // }





  return (
    <Suspense fallback={<div>Loading...</div>}>
      <BrowserRouter>
        <Routes>
          <Route path="/"
            element={
              <ProtectedRoute accessType={AuthenticationLayer.EVERYONE}>
                <HomePage />
              </ProtectedRoute>
            }
          />

          <Route path="/login"
            element={
              <ProtectedRoute accessType={AuthenticationLayer.LOGOUT}>
                <LoginPage />
              </ProtectedRoute>
            }
          />

          {/* <Route path="/test" element={<TestPost />} /> */}

          <Route path="/logout"
            element={
              <ProtectedRoute accessType={AuthenticationLayer.EVERYONE}>
                <LogoutPage />
              </ProtectedRoute>
            }
          />


          <Route path="/search"
            element={
              <ProtectedRoute accessType={AuthenticationLayer.USER_LOGIN}>
                <SearchPage />
              </ProtectedRoute>
            }
          />

          {/* <Route path="/r" element={<ManagerRestaurantPage />} /> */}

          <Route path="/restaurant/:name"
            element={
              <ProtectedRoute accessType={AuthenticationLayer.USER_LOGIN}>
                <RestaurantPage />
              </ProtectedRoute>
            }
          />

          <Route path="/manager/:name"
            element={
              <ProtectedRoute accessType={AuthenticationLayer.EVERYONE}>
                <ManagerManagePage />
              </ProtectedRoute>
            }
          />


        </Routes>
      </BrowserRouter>
    </Suspense>
  );
}


export default App;








// import React from "react";
// import logo from "./logo.svg";
// import "./App.css";

// function App() {
//   const [data, setData] = React.useState(null);

//   React.useEffect(() => {
//     fetch("/api")
//       .then((res) => res.json())
//       // .then((data) => setData(data.message));
//       .then((data) => setData(data.title));
//   }, []);

//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>{!data ? "Loadinggg..." : data}</p>
//       </header>
//     </div>
//   );
// }

// export default App;