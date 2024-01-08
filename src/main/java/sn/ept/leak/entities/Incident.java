package sn.ept.leak.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class Incident {

    public String documentId;
    public String id_1;
    public String id_2;
    public double pression_1;
    public double pression_2;
    public String gravite;
    public Date date;

}


