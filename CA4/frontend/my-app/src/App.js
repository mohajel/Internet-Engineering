import React, { Suspense } from 'react';

import {BrowserRouter, Routes, Route,} from "react-router-dom";

import './App.css';
// import HomePage from './views/HomePage';

const HomePage = React.lazy(() => import('./views/HomePage'));
const ErrorPage = React.lazy(() => import('./views/ErrorPage'));
const LoginPage = React.lazy(() => import('./views/LoginPage'));




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
        <Route path="/error" element={<ErrorPage />} />
        <Route path="/login" element={<LoginPage />} />

      </Routes>
    </BrowserRouter>
    </Suspense>
  );
}

// function HomePage() {
//   return <h2>Home</h2>;
// }

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