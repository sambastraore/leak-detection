package sn.ept.leak.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Capteur {
    public String documentId;
    public boolean normal;
    public double pression;

}
