import React from "react";
import Test from "../Test/Test";
import Bouton from "../Bouton/Bouton";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import { faLock } from "@fortawesome/free-solid-svg-icons";
import { faAddressBook } from "@fortawesome/free-solid-svg-icons";
import { createUserWithEmailAndPassword } from "firebase/auth";
import { useState } from "react";
import { auth } from "../../config";

const Regis = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const register_user = (e) => {
    e.preventDefault();
    createUserWithEmailAndPassword(auth, email, password)
      .then((userInformations) => console.log(userInformations))
      .catch((error) => console.log(error));
  };

  function emailChange(event) {
    setEmail(event.target.value);
  }

  function passwordChange(event) {
    setPassword(event.target.value);
  }

  return (
    <div>
      {/* <FontAwesomeIcon icon={faEnvelope} /> */}

      <div className="corps" style={{ height: 700, width: 600 }}>
        <form
          action=""
          className="formulaire"
          style={{ height: 700, width: 600 }}
          onSubmit={register_user}
        >
          <p>{email}</p>
          <p>{password}</p>
          <header>
            <h1>INSCRIPTION</h1>
            <div className="the-hr">
              <hr />
            </div>
          </header>

          <div className="body-formulaire">
            <Test
              icon={faUser}
              icon_color={"#006392"}
              texte_1={"Votre prénom"}
              texte_2={"Votre nom"}
              type_1={"text"}
              type_2={"text"}
            />
            <Test
              icon={faAddressBook}
              icon_color={"#006392"}
              texte_1={"Numéro"}
              texte_2={"Email"}
              type_1={"number"}
              type_2={"email"}
              event={emailChange}
            />
            <Test
              icon={faLock}
              icon_color={"#006392"}
              texte_1={"Mot de passe"}
              texte_2={"Confirmer mot de passe"}
              type_1={"password"}
              type_2={"password"}
              event={passwordChange}
            />

            <div className="buttonn">
              <Bouton class={"boutton"} texte={"S'inscrire"} />
            </div>
          </div>

          <div className="tail-formulaire">
            <span className="not-again-account">
              Vous avez deja un compte ?
            </span>
            <span className="">
              <a href=""> Login</a>{" "}
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

export default Regis;
