package ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception;

import java.util.Date;

public class CustomRestError {

    private Date date;
    private String message;
    private String reqDes;

    CustomRestError(Date d, String message, String requestDescription) {
        this.date = d;
        this.message = message;
        this.reqDes = requestDescription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReqDes() {
        return reqDes;
    }

    public void setReqDes(String reqDes) {
        this.reqDes = reqDes;
    }
}
