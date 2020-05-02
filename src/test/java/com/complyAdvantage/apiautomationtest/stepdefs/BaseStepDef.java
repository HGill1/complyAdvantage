package com.complyAdvantage.apiautomationtest.stepdefs;

import com.complyAdvantage.apiautomationtest.models.Politician;
import io.restassured.response.Response;

import java.util.List;

public class BaseStepDef {
    static protected String endPoint;
    protected Response response;
    protected List<Politician> listOfPolitician;
    protected Politician newPolitician;
}
