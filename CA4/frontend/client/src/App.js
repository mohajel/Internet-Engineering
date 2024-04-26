
import React from "react";
import logo from "./logo.svg";
import "./App.css";

function App() {
  const [data, setData] = React.useState(null);

  React.useEffect(() => {
    fetch("/userStatus")
      .then((res) => res.json())
      // .then((data) => setData(data.message));
      .then((data) => setData(data.title));
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>{!data ? "Loadinggg..." : data}</p>
      </header>
    </div>
  );
}

export default App;