package io.swagger.petstore.steps.stepDefinition;


import java.util.List;
import java.util.Map;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.runner.RunWith;

import io.swagger.petstore.pojo.store.StoreInfo;
import io.swagger.petstore.steps.generic.StoreAPISteps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class StoreStepDefn {

    private Response res = null; // Response
    private SoftAssertions softAssertion = null;

    @Before
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        this.softAssertion = new SoftAssertions();
    }

    @After
    public void tearDown() {
        this.softAssertion.assertAll();
        RestAssured.reset();
    }


    private StoreInfo storeInfo = null;
    private static String storeUrl = null;
    @Steps
    StoreAPISteps storeAPISteps;

    @Given("^I would like to place an order for a  pet$")
    public void i_would_like_to_place_an_order_for_a_pet(List<Map<String, String>> listOfData) {
        Map<String,String> map = listOfData.get(0);
        StoreStepDefn.storeUrl = map.get("url");
        //load list string value into class
        this.storeInfo = storeAPISteps.createStoreClass(map);
        this.res=storeAPISteps.createOrderRequest(StoreStepDefn.storeUrl, this.storeInfo);
        StoreInfo actualResponse = storeAPISteps.validatePostStatusAndReturnResponse(this.res);
        storeAPISteps.compareStoreInfo(this.softAssertion, this.storeInfo, actualResponse);

    }

    @When("^My order is placed, I would like to get the order information by \"([^\"]*)\"$")
    public void my_order_is_placed_I_would_like_to_get_the_order_information_by(String orderId) {
        StoreInfo actualResponse = storeAPISteps.fetchOrderInfoById(StoreStepDefn.storeUrl,orderId);
        storeAPISteps.compareStoreInfo(this.softAssertion, this.storeInfo, actualResponse);

    }

    @Then("^I would like to delete my order by \"([^\"]*)\" if I am not not happy with it$")
    public void i_would_like_to_delete_my_order_by_if_I_am_not_not_happy_with_it(String orderId) {
        storeAPISteps.deleteOrderInfoById(StoreStepDefn.storeUrl,orderId);
    }

    @Given("^I hit the \"([^\"]*)\"$")
    public void i_hit_the(String inventoryURL) throws Exception {
        this.res = storeAPISteps.fetchInventoryData(inventoryURL);
    }


    @When("^Request should submit and Positive API response should received \"([^\"]*)\"$")
    public void request_should_submit_and_Positive_API_response_should_received(String response) throws Exception {
        Assert.assertEquals("Status Check Passed!", response, this.res.getStatusCode()+"");
    }

    @Then("^Inventory data should display$")
    public void inventory_data_should_display() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        String response = this.res.body().asString();
        System.out.println("Inventory data " + response);
        Assert.assertNotNull("Inventory data should present ", response);
    }
}

