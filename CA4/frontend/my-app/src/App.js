import React, { Suspense } from 'react';

import { BrowserRouter, Routes, Route, } from "react-router-dom";

import './App.css';
// import HomePage from './views/HomePage';

const HomePage = React.lazy(() => import('./views/HomePage'));
const MessagePage = React.lazy(() => import('./views/MessagePage'));
const LoginPage = React.lazy(() => import('./views/LoginPage'));
const TestPost = React.lazy(() => import('./views/TestPost'));
const LogoutPage = React.lazy(() => import('./views/Logout'));
const SearchPage = React.lazy(() => import('./views/SearchRestaurantPage'));


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
          <Route path="/" element={<HomePage />} />
          <Route path="about/*" element={<AboutPage />} />
          {/* <Route path="/error" element={<MessagePage type='error' message='message' redirectURL='/login' />} /> */}
          <Route path="/login" element={<LoginPage />} />
          <Route path="/test" element={<TestPost />} />
          <Route path="/logout" element={<LogoutPage />} />
          <Route path="/search" element={<SearchPage />} />

        </Routes>
      </BrowserRouter>
    </Suspense>
  );
}

function AboutPage() {
  return <h2>About</h2>;
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