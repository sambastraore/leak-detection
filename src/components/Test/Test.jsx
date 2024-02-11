import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import Input from "../input/Input";

const Test = (parametre) => {
  return (
    <div className="test">
      <span>
        <FontAwesomeIcon icon={parametre.icon} color={parametre.icon_color} />
      </span>
      <div>
        <Input
          display_icon={false}
          place_holder={parametre.texte_1}
          type={parametre.type_1}
        />
        <Input
          display_icon={false}
          place_holder={parametre.texte_2}
          type={parametre.type_2}
          event={parametre.event}
        />{" "}
        <Input
          display={parametre.display}
          display_icon={false}
          place_holder={parametre.texte_3}
          type={parametre.type_2}
        />
      </div>
    </div>
  );
};

export default Test;
