package com.complyAdvantage.apiautomationtest.services;

import io.restassured.response.Response;

public class GetPolitician {

    private Response response;

    public GetPolitician(Response response) {
        this.response = response;
    }
}
