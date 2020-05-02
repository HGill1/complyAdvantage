package com.complyAdvantage.apiautomationtest.models;

public class ResponseObject {

    private  String id;

    private  String message;

    private  boolean ok;

    public void setId(String id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public boolean isOk() {
        return ok;
    }
}
