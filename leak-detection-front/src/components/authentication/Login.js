import React from "react";

import { faEnvelope } from "@fortawesome/free-solid-svg-icons";
import { faLock } from "@fortawesome/free-solid-svg-icons";
import Input from "../input/Input";
import Bouton from "../Bouton/Bouton";

const Login = () => {
  return (
    <div>
      {/* <FontAwesomeIcon icon={faEnvelope} /> */}

      <div className="corps">
        <form action="" className="formulaire">
          <header>
            <h1>LOG IN</h1>
            <div className="the-hr">
              <hr />
            </div>
          </header>

          <div className="body-formulaire">
            <Input
              type={"text"}
              className={"input-1"}
              place_holder={"Saisir votre e-mail"}
              icon={faEnvelope}
              class={"icon-email"}
              color_icon={"#006392"}
              size={"5xs"}
            />

            <Input
              type={"password"}
              className={"input-1"}
              place_holder={"Saisir mot de passe"}
              icon={faLock}
              class={"icon-email"}
              color_icon={"#006392"}
              size={"5xs"}
            />

            <div className="buttonn">
              <Bouton class={"boutton"} texte={"Se Connecter"} />
            </div>
          </div>

          <div className="tail-formulaire">
            <span className="not-again-account">
              Vous n'avez pas de compte ?
            </span>
            <span className="">
              <a href=""> S'inscrire</a>{" "}
            </span>
          </div>
        </form>
      </div>
      {/* 
            gardons une trace <div className="input-2">
              <input type="password" />
              <span className="icon-password"></span>
            </div> */}
    </div>
  );
};

export default Login;
