// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyCCQyIJhvYH_JTO-h8fdBC1BnfZXJ9GX9k",
  authDomain: "gnaa-76c6b.firebaseapp.com",
  databaseURL:
    "https://gnaa-76c6b-default-rtdb.europe-west1.firebasedatabase.app",
  projectId: "gnaa-76c6b",
  storageBucket: "gnaa-76c6b.appspot.com",
  messagingSenderId: "16058060580",
  appId: "1:16058060580:web:7c074559a974c61798edb1",
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);

//export default auth;
