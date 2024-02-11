import { Route, Routes } from "react-router";
import Main from "./Main";
import Login from "./components/authentication/Login";
import Regis from "./components/authentication/Regis";
import "./assets/style/style.css";

// import { ColorModeContext, useMode } from "./theme";

function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<Login />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/regis" element={<Regis />}></Route>
        <Route path="/dashboard" element={<Main />}></Route>
      </Routes>
    </div>
  );
}

export default App;
