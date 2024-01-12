import Login from "./components/authentication/Login";
import Regis from "./components/authentication/Regis";
import Input from "./components/input/Input";
import "./assets/style/style.css";
import Test from "./components/Test/Test";
// import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

// import "../../../node_modules/bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <div>
      {/* <Login />
      <Input
        className={"input-1"}
        place_holder={"test du component"}
        icon={faEnvelope}
        class={"icon-email"}
        color_icon={"#006392"}
        size={"5xs"}
      /> */}

      {/* <Login /> */}
      <Regis />
    </div>
  );
}

export default App;
