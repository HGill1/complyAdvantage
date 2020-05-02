package com.complyAdvantage.apiautomationtest.stepdefs;

import com.complyAdvantage.apiautomationtest.models.Politician;
import com.complyAdvantage.apiautomationtest.models.ResponseObject;
import com.complyAdvantage.apiautomationtest.services.SendPoliticianRequest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;

import java.util.Arrays;

public class GetPolitician extends BaseStepDef {

    private BaseStepDef baseStepDef;

    public GetPolitician(BaseStepDef baseStepDef) {
        this.baseStepDef = baseStepDef;
    }

    @When("I add a politician using {string}, {string}, {string}, {int}, {int}")
    public void iAddAPoliticianUsingRiskYob(String name, String country, String position, int risk, int yob) {

        Politician politician = new Politician();
        politician.setName(name);
        politician.setCountry(country);
        politician.setPosition(position);
        politician.setRisk(risk);
        politician.setYob(yob);

        baseStepDef.newPolitician = politician;

        SendPoliticianRequest politicianRequest = new SendPoliticianRequest(baseStepDef.response);
        baseStepDef.response = politicianRequest.postRequest(politician, endPoint);

    }

    @And("I send a get request to access the added politician")
    public void iSendAGetRequestToAccessTheAddedPolitician() {
        ResponseObject responseObject = baseStepDef.response.as(ResponseObject.class);
        SendPoliticianRequest politicianRequest = new SendPoliticianRequest(baseStepDef.response);
        baseStepDef.response = politicianRequest.getRequest( endPoint + "/" + responseObject.getId());
    }

    @Then("I see added politician detail")
    public void iSeeAddedPoliticianDetail() {
        Politician politician = baseStepDef.response.getBody().as(Politician.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(politician.getName()).isEqualTo(baseStepDef.newPolitician.getName());
            softly.assertThat(politician.getCountry()).isEqualTo(baseStepDef.newPolitician.getCountry());
            softly.assertThat(politician.getPosition()).isEqualTo(baseStepDef.newPolitician.getPosition());
            softly.assertThat(politician.getRisk()).isEqualTo(baseStepDef.newPolitician.getRisk());
            softly.assertThat(politician.getYob()).isEqualTo(baseStepDef.newPolitician.getYob());

        });
    }

    @And("I send a get request to access a politician detail using invalid id")
    public void iSendAGetRequestToAccessAPoliticianDetailUsingInvalidId() {
        SendPoliticianRequest politicianRequest = new SendPoliticianRequest(baseStepDef.response);
        baseStepDef.response = politicianRequest.getRequest( endPoint + "/invalidId" );
    }

    @Then("I receive {int} status code in response")
    public void iReceiveStatusCodeInResponse(int statusCode) {
        baseStepDef.response.then()
                .assertThat()
                .statusCode(statusCode)
                .log()
                .ifError(); // Response logs
    }
}
