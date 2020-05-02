package com.complyAdvantage.apiautomationtest.models;

public class InvalidResponse {
    private  String message;

    private  boolean ok;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public boolean isOk() {
        return ok;
    }
}
