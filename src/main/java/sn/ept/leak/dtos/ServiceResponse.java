package sn.ept.leak.dtos;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Getter
@Setter
public class ServiceResponse {
    private String message;

    private String code;

    private String details;

    private JSONObject data;

    public ServiceResponse(String message, String code, String details, JSONObject data) {
        super();
        this.message = message;
        this.code = code;
        this.details = details;
        this.data = data;
    }

    public ServiceResponse(String message, String code, String details) {
        super();
        this.message = message;
        this.code = code;
        this.details = details;
    }

    public ServiceResponse() {
        super();
    }

    public ServiceResponse(String message, JSONObject data) {
        super();
        this.message = message;
        this.data = data;
    }



}
