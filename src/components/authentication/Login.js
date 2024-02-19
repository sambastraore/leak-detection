import React from "react";

import { faEnvelope } from "@fortawesome/free-solid-svg-icons";
import { faLock } from "@fortawesome/free-solid-svg-icons";
import Input from "../input/Input";
import Bouton from "../Bouton/Bouton";
import { auth, db } from "../../config";
import { signInWithEmailAndPassword } from "firebase/auth";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import logo from "./logo.jpg";
import { collection, getDocs, query, where } from "firebase/firestore";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  function emailChange(event) {
    setEmail(event.target.value);
  }

  function passwordChange(event) {
    setPassword(event.target.value);
  }

  const login_user = (e) => {
    e.preventDefault();
    signInWithEmailAndPassword(auth, email, password)
      .then((userInformations) => {
        console.log(userInformations);
        // now we get the name of the user
        getUser(db, email);
        navigate("/dashboard");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  async function getUser(db, email) {
    const q = query(collection(db, "users"), where("email", "==", email));
    const querySnapshot = await getDocs(q);
    //return querySnapshot;
    querySnapshot.forEach((doc) => {
      // doc.data() is never undefined for query doc snapshots
      console.log(doc.id, " => ", doc.data());
      // prenom = doc.data().prenom;
      // console.log(doc.data().prenom);
      // return doc.data().prenom;

      localStorage.setItem("prenom", doc.data().prenom);
    });
  }

  return (
    <div>
      {/* <FontAwesomeIcon icon={faEnvelope} /> */}

      <div className="corps">
        <form action="" className="formulaire" onSubmit={login_user}>
          <header>
            <h1>LOG IN</h1>
            <div className="the-hr" style={{ width: "100px", height: "100px" }}>
              <img
                src={logo}
                alt="logo image"
                style={{ width: "100%", height: "100%" }}
              />
            </div>

            <div className="the-hr">
              <hr />
            </div>
          </header>

          <div className="body-formulaire">
            <Input
              type={"email"}
              className={"input-1"}
              place_holder={"Saisir votre e-mail"}
              icon={faEnvelope}
              class={"icon-email"}
              color_icon={"#006392"}
              size={"5xs"}
              event={emailChange}
              value={email}
            />

            <Input
              type={"password"}
              className={"input-1"}
              place_holder={"Saisir mot de passe"}
              icon={faLock}
              class={"icon-email"}
              color_icon={"#006392"}
              size={"5xs"}
              event={passwordChange}
              value={password}
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
              {/* <a href=""> S'inscrire</a>  */}
              <Link to={"/regis"}>S'inscrire</Link>
            </span>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
