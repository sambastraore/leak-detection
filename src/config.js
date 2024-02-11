// // Import the functions you need from the SDKs you need
// import { initializeApp } from "firebase/app";
// import { getAuth } from "firebase/auth";
// // TODO: Add SDKs for Firebase products that you want to use
// // https://firebase.google.com/docs/web/setup#available-libraries

// // Your web app's Firebase configuration
// const firebaseConfig = {
//   apiKey: "AIzaSyCCQyIJhvYH_JTO-h8fdBC1BnfZXJ9GX9k",
//   authDomain: "gnaa-76c6b.firebaseapp.com",
//   databaseURL:
//     "https://gnaa-76c6b-default-rtdb.europe-west1.firebasedatabase.app",
//   projectId: "gnaa-76c6b",
//   storageBucket: "gnaa-76c6b.appspot.com",
//   messagingSenderId: "16058060580",
//   appId: "1:16058060580:web:7c074559a974c61798edb1",
// };

// // Initialize Firebase
// const app = initializeApp(firebaseConfig);
// export const auth = getAuth(app);

//export default auth;

//app leak
// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { firestore } from "firebase/firestore";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyBQTCpOL2FY5phC0OPcOnrx1wu1-3zcXHE",
  authDomain: "water-leak-db.firebaseapp.com",
  projectId: "water-leak-db",
  storageBucket: "water-leak-db.appspot.com",
  messagingSenderId: "900434346463",
  appId: "1:900434346463:web:4a18b2e8338e7e7d1f446a",
};

const db = firestore();

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const matricule = "1234";

export { db, auth, matricule };
