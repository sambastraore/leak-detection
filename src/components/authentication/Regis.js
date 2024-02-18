import React from "react";
import Test from "../Test/Test";
import Bouton from "../Bouton/Bouton";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import { faLock } from "@fortawesome/free-solid-svg-icons";
import { faAddressBook } from "@fortawesome/free-solid-svg-icons";
import { createUserWithEmailAndPassword } from "firebase/auth";
import { useState } from "react";
import { auth, db, matricule } from "../../config";
import { Link, useNavigate } from "react-router-dom";

const Regis = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [numero, setNumero] = useState("");
  const [matriculeUser, setMatriculeUser] = useState("");
  const navigate = useNavigate();

  const register_user = (e) => {
    e.preventDefault();

    // first we check if matricule user is equal to matricule
    if (matriculeUser !== matricule) return;
    createUserWithEmailAndPassword(auth, email, password)
      .then((userInformations) => {
        // when it'ss succes, we commit change  in the database firestore
        db.collection("users").add({
          prenom: firstName,
          nom: lastName,
          email: email,
          numero: numero,
        });
        console.log(userInformations);
        navigate("/login");
      })
      .catch((error) => console.log(error));
  };

  function firstNameChange(e) {
    setFirstName(e.target.value);
  }

  function lastNameChange(e) {
    setLastName(e.target.value);
  }
  function numeroChange(e) {
    setNumero(e.target.value);
  }

  function emailChange(event) {
    setEmail(event.target.value);
  }

  function passwordChange(event) {
    setPassword(event.target.value);
  }

  function matriculeChange(event) {
    setMatriculeUser(event.target.value);
  }

  return (
    <div>
      {/* <FontAwesomeIcon icon={faEnvelope} /> */}

      <div className="corps" style={{ height: 850, width: 600 }}>
        <form
          action=""
          className="formulaire"
          style={{ height: 850, width: 600 }}
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
              event1={firstNameChange}
              event={lastNameChange}
              display={"none"}
            />
            <Test
              icon={faAddressBook}
              icon_color={"#006392"}
              texte_1={"Numéro"}
              texte_2={"Email"}
              type_1={"text"}
              type_2={"email"}
              event1={numeroChange}
              event={emailChange}
              display={"none"}
            />
            <Test
              icon={faLock}
              icon_color={"#006392"}
              texte_1={"Mot de passe"}
              texte_2={"Confirmer mot de passe"}
              texte_3={"Matricule Admin"}
              type_1={"password"}
              type_2={"text"}
              event={passwordChange}
              event2={matriculeChange}
              display={"block"}
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
              {/* <a href=""> Login</a>{" "} */}
              <Link to={"/login"}>Login</Link>
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
