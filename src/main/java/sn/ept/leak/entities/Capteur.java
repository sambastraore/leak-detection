package sn.ept.leak.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Capteur {
    public String documentId;
    public double pression;
    public double latitude;
    public double longitude;
    public String region;

}
