import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
const Input = (parametre) => {
  return (
    <div className={parametre.className}>
      <input
        type={parametre.type}
        placeholder={parametre.place_holder}
        onChange={parametre.event}
        value={parametre.value}
      />
      {parametre.display_icon && (
        <span className={parametre.class}>
          {" "}
          <FontAwesomeIcon
            icon={parametre.icon}
            color={parametre.color_icon}
            size={parametre.size_icon}
          />
        </span>
      )}
    </div>
  );
};

export default Input;
