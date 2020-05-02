package com.complyAdvantage.apiautomationtest.stepdefs;

import com.complyAdvantage.apiautomationtest.util.PropertiesUtil;
import io.cucumber.java.en.Given;

public class CommonSteps extends BaseStepDef {

    @Given("I have a valid endpoint for adding politician")
    @Given("I have a valid endpoint for displaying politician details")
    @Given("I have a valid endpoint for fetching a politician")
    public void iHaveAValidEndpointForAddingPolitician() {
        final PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();
        endPoint = propertiesUtil.getApiURI("endPoint");
    }
}
