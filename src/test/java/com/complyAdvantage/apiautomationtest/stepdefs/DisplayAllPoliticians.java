package com.complyAdvantage.apiautomationtest.stepdefs;

import com.complyAdvantage.apiautomationtest.models.Politician;
import com.complyAdvantage.apiautomationtest.services.SendPoliticianRequest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;

public class DisplayAllPoliticians extends BaseStepDef {

    private BaseStepDef baseStepDef;

    public DisplayAllPoliticians(BaseStepDef baseStepDef) {
        this.baseStepDef = baseStepDef;
    }

    @When("^I send a get request to access politicians$")
    public void iSendAGetRequestToAccessPoliticians() {
        SendPoliticianRequest politicianRequest = new SendPoliticianRequest(baseStepDef.response);
        baseStepDef.response = politicianRequest.getRequest(endPoint);
    }

    @Then("^I verify politician details are displayed$")
    public void iVerifyPoliticianDetailsAreDisplayed() {
       baseStepDef.response.then()
               .log()
               .ifError()
               .assertThat()
               .statusCode(200);
    }

    @And("I verify that list displayed the latest {int} politician added")
    public void iVerifyThatListDisplayedTheLatestPoliticianAdded(int count) {
        baseStepDef.listOfPolitician = Arrays.asList(baseStepDef.response.getBody().as(Politician[].class));
        assertThat(baseStepDef.listOfPolitician).size().isEqualTo(10);
    }

    @And("I verify politician are displayed in order of date added")
    public void iVerifyPoliticianAreDisplayedInOrderOfDateAdded() {
        List<Politician> actualList = new ArrayList<>(baseStepDef.listOfPolitician);
        assertThat(baseStepDef.listOfPolitician.stream().sorted((o1, o2) -> LocalDateTime.parse(o2.getCreatedAt()).compareTo(LocalDateTime.parse(o1.getCreatedAt()))).collect(Collectors.toList()).equals(actualList)).isTrue();
    }
}
