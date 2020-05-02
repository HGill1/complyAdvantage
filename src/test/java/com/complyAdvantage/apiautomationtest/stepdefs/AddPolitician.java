package com.complyAdvantage.apiautomationtest.stepdefs;

import com.complyAdvantage.apiautomationtest.models.InvalidResponse;
import com.complyAdvantage.apiautomationtest.models.Politician;
import com.complyAdvantage.apiautomationtest.models.ResponseObject;
import com.complyAdvantage.apiautomationtest.services.SendPoliticianRequest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;

public class AddPolitician extends BaseStepDef {

    private BaseStepDef baseStepDef;

    public AddPolitician(BaseStepDef baseStepDef) {
        this.baseStepDef = baseStepDef;
    }

    @When("I send {string}, {string}, {string}, {int}, {int} as post request")
    public void iSendAsPostRequest(String name, String country, String position, int risk, int yob) throws Throwable {
        Politician politician = new Politician();
        politician.setName(name);
        politician.setCountry(country);
        politician.setPosition(position);
        politician.setRisk(risk);
        politician.setYob(yob);

        SendPoliticianRequest politicianRequest = new SendPoliticianRequest(baseStepDef.response);
        baseStepDef.response = politicianRequest.postRequest(politician,  endPoint);
    }


    @Then("^I see a successful message of politician added$")
    public void iSeeASuccessfulMessageOfPoliticianAdded() {
        validateResponse();
    }

    @When("I send {string} {string}, {string}, {int}, {int} as post request with missing name field")
    public void iSendAsPostRequestWithMissingNameField(String name, String country, String position, int risk, int yob) throws Throwable {
        Politician politician = new Politician();
        if (!name.isEmpty())
            politician.setName(name);

        if (!position.isEmpty())
            politician.setPosition(position);

        politician.setCountry(country);
        politician.setRisk(risk);
        politician.setYob(yob);

        SendPoliticianRequest politicianRequest = new SendPoliticianRequest(baseStepDef.response);
        baseStepDef.response = politicianRequest.postRequest(politician,  endPoint);

    }

    @Then("^I see a invalid request error message$")
    public void iSeeAInvalidRequestErrorMessage() {
        verifyInvalidResponse();
    }

    public void validateResponse() {
        baseStepDef.response.then()
                .assertThat()
                .statusCode(201)
                .log()
                .ifError(); // Response logs

        ResponseObject responseObject = baseStepDef.response.as(ResponseObject.class);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(responseObject.getId()).isNotEmpty();
            softly.assertThat(responseObject.getMessage().equals("Entity created successfully!"));
            softly.assertThat(responseObject.isOk()).isTrue();

        });
    }

    public void verifyInvalidResponse() {
        baseStepDef.response.then()
                .assertThat()
                .statusCode(400)
                .log()
                .ifError(); // Response logs
        InvalidResponse invalidResponse = baseStepDef.response.as(InvalidResponse.class);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(invalidResponse.getMessage().equals("Invalid request - missing parameters"));
            softly.assertThat(invalidResponse.isOk()).isFalse();

        });
    }


}
