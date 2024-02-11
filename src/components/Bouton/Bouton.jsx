import React from "react";

const Bouton = (parametre) => {
  return (
    <div>
      <button
        className={
          parametre.class
        } /*style={{ width: parametre.width, padding: parametre.padding }}*/
      >
        {parametre.texte}
      </button>
    </div>
  );
};

export default Bouton;
