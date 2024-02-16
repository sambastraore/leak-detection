const letterA = document.querySelector(".lettre-A");
const otherLetter = document.querySelectorAll(".lettre");
const pressionA = document.querySelector(".pression-A");
const otherPression = document.querySelectorAll(".pression");
const linecus = document.querySelectorAll(".line-cus");
const linecusP = document.querySelectorAll(".line-cus-p");
const tuyau2 = document.querySelector(".tuyau-2");
const flecheUp = document.querySelectorAll(".fa-arrow-up-long");
const support1 = document.querySelector(".support-1");
const support2 = document.querySelector(".support-2");

const eps3 = document.querySelector(".eps-3");
const eps4 = document.querySelector(".eps-4");

const perte = document.querySelector(".perte-de-charge");
const line = document.querySelector(".line");

const fuitee = document.querySelector(".fuite");
function animeA() {
  letterA.style.display = "block";
  letterA.classList.add("lettre-animation");
}

function animeOthers() {
  otherLetter.forEach((elt) => {
    elt.style.display = "block";
    elt.classList.add("lettre-animation");
  });
}

function animePressionA() {
  pressionA.style.display = "block";
  pressionA.classList.add("pression-animation");
}

function animePressionOthers() {
  otherPression.forEach((elt) => {
    elt.style.display = "block";
    elt.classList.add("pression-animation");
  });
}

function animePerteDeCharge() {
  perte.style.display = "block";
  perte.classList.add("perte-de-charge-animation");
}

function ligneEau() {
  line.style.display = "block";
  line.classList.add("line-animation");
}

function lineCus() {
  linecus.forEach((elt) => {
    elt.style.display = "block";
    elt.classList.add("lettre-animation");
  });
}

function lineCusP() {
  linecusP.forEach((elt) => {
    elt.style.display = "block";
    elt.classList.add("line-cus-p-animation");
  });
}
function singuliere() {
  flecheUp.forEach((elt) => {
    console.log(elt);
    elt.style.display = "block";
  });
  tuyau2.style.display = "block";
}

function eps() {
  //   eps3.style.display = "block";
  //   eps3.style.width = "50px";
  //   eps3.style.height = "50px";
  //   eps3.style.borderRadius = "50%";
  //   eps3.backgroundColor = "green";
  eps3.innerHTML = "ε" + `<sub>1</sub>`;
  eps3.style.color = "red";
  eps4.innerHTML = "ε" + `<sub>2</sub>`;
  eps4.style.color = "blue";
  support1.style.display = "block";
}

function fuite() {
  fuitee.style.display = "block";
  support2.style.display = "block";
}
