package sn.ept.leak.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Segment {

    public String documentId;
    public boolean fuite;
    public Date verification;
    public double perteDeChargeNormale;
    public String id_1;
    public String id_2;

}


