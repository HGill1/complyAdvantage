package com.complyAdvantage.apiautomationtest.services;

import com.complyAdvantage.apiautomationtest.models.Politician;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SendPoliticianRequest {
    private Response response;

    public SendPoliticianRequest(Response response) {
        this.response = response;
    }

    public Response postRequest(Politician politician, String endpoint) {

        return response = given().contentType("application/json")
                .when()
                .log()
                .ifValidationFails()  //Request logs used for debugging
                .body(politician)
                .post(endpoint);

    }

    public Response getRequest(String endpoint) {

        return response = given().contentType("application/json")
                .when()
                .log().ifValidationFails() //Request logs used for debugging
                .get(endpoint);
    }
}
