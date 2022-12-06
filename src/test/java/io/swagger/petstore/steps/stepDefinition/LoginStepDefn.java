package io.swagger.petstore.steps.stepDefinition;


import org.junit.Assert;
import org.junit.runner.RunWith;

import io.swagger.petstore.pojo.users.UserAPIResponse;
import io.swagger.petstore.steps.generic.LoginAPISteps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class LoginStepDefn{
    private Response res = null; //Response
    private RequestSpecification requestSpec = null;
    @Before
    public void setup()
    {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @After
    public void tearDown()
    {
        RestAssured.reset();
    }

    @Steps
    LoginAPISteps loginAPI;

    @Given("^I provide login credentials \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_provide_login_credentials_and(String username, String password){
        //GET URL Path
        String urlPath = "/user/login?username=" + username + "&password=" + password;
        //Add user name and password into session variable
        Serenity.setSessionVariable("username").to(username);
        Serenity.setSessionVariable("password").to(password);
        //This method call APIRequestBuilder and
        this.requestSpec = loginAPI.givenUserDetails(urlPath, username,password);
    }


    @When("^I send request to login$")
    public void i_send_request_to_login() {
        //Call Post method
        this.res = loginAPI.postLoginRequest(this.requestSpec);
    }

    @Then("^login failed$")
    public void login_failed() {
        //Fetch user name and password from session variable
        String username = Serenity.sessionVariableCalled("username").toString();
        String password = Serenity.sessionVariableCalled("password").toString();
        //Verify login failure
        loginAPI.verifyLoginFailure(this.res, username,password);
    }

    @Then("^login is successful$")
    public void login_is_successful() {
        //Fetch user name and password from session variable
        String username = Serenity.sessionVariableCalled("username").toString();
        String password = Serenity.sessionVariableCalled("password").toString();
        //Verify login success
        loginAPI.verifyLoginSuccess(res, username,password);
    }

    @Then("^logout is successful$")
    public void logout_is_successful() {
        //GET URL Path
        String urlPath = "/user/logout";
        //Verify logout successful
        UserAPIResponse expectedResponse= loginAPI.verifyLogoutSuccess(urlPath);
        Assert.assertEquals("Status Check Passed!", "200", expectedResponse.getCode().toString());
        Assert.assertNotNull("type field in response is not empty", expectedResponse.getType());
        Assert.assertEquals("Message return id", "ok", expectedResponse.getMessage());
    }



}

