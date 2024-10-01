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
              <ProtectedRoute accessType={AuthenticationLayer.EVERYONE}>
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
              <ProtectedRoute accessType={AuthenticationLayer.EVERYONE}>
                <SearchPage />
              </ProtectedRoute>
            }
          />

          {/* <Route path="/r" element={<ManagerRestaurantPage />} /> */}

          <Route path="/restaurant/:name"
            element={
              <ProtectedRoute accessType={AuthenticationLayer.EVERYONE}>
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