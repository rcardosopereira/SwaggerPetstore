![REST Assured](rest-assured-logo-green.png)
# Appplicatin : PetStore
Pet store is Serenity BDD framework based REST API Automation project. Imagine you are a part of the team that performs quality assurance for a Pet store application, The frontend design is under development but API has already been published. QA collaborate with developer team to make the feature  more robust, by targeting to write tests for some workflows that they might break while developing business logic.
```
Goal is to cover: 
* To create a test automation framework skeleton
* To test the application using available endpoint
```
* Reference: Swagger URL# https://petstore.swagger.io/#/pet/updatePet


Testing REST services in Java
Technology used - Serenity, REST Assured, Cucumber and JAVA

# Prerequisite
* JAVA 8 or higher
* IntelliJ
* A Buid Tool - Used Maven

# Getting Started
* Clone this repository 

# How to install and run this project?
* Execute maven command - mvn clean verify

# Story Description
Below list of story coverage API capabilities of Login, PetProfile, Store and User. The story design such a way it cover all APIs present in the swagger.

* User - Operations about users
```
1_ As a End User, I can login and logout to the application using API
Description: Valid and Invalid Scenario covered
API Scenarion:
* GET /user/login - Logs user into the system
* GET /user/logout - Logs out current logged in user session
```
```
2_ As a Admin User, I can create new user, view or update existing user information and delete the user 
Description: Create new user, View user information, Update or Delete Existing user
API Scenarion:
* POST /user Create user
* GET /user/{username} Get user by username
* PUT /user{username} Updated user
* DELETE /user/{username} Delete User
* POST /user/createWithArray Creates list of users with given input array
* POST /user/createWithList Creates list of users with given input array
```

* Store - Access to Petstore order
```
3_ As a End User, I can control Pet Order Cart
Descriptoin : Place new order, Find or Delete purchase order by ID
* POST /store/order Place an order for a pet
* GET /store/order/{orderid} Find purchase order by ID
* DELETE /store/order/{orderId} Delete purcahse order by ID
```
```
4_ As a Store Owner, I would like to check my inventory
Descriptoin : Check Inventory
* GET /store/inventory Return pet inventories by status
```
* Pet - Everything about Pets
```
5_As a Store Owner, I would like add new pet profile, upload pet image and delete pet profile
Description : Create, Upload image, Search or Delete Pet profile 
* POST /put Add an new pet to the store
* GET /pet/{petId} Find pet by ID
* DELETE /pet/{petId} Delete a pet
* POST /put/{petId}/uploadImage upload an Image
```
```
6_ As a Store Owner, I would like add update pet profile, view pet info by status or create new pet profile with form data
Description : Create, Update a pet profile with body or form data and Find Pet By Status
* POST /put Add an ew pet to the store
* GET /pet/findByStatus Finds Pets by status
* PUT /pet Add a new pet to the store
* POST /pet/{petId} Update ta pet in the store with form data
```
# How to write new tests ?
* Step1. Create new feature file or Add Scenario (Outline, Given, When, Then) in a existing feature file with Valid Data
```
*************************   Sample Cucumber File  *************************

Scenario Outline: As a End User, Valid user can login and logout to the application using API
    Given I provide login credentials "<username>" and "<password>"
    When I send request to login
    Then login is successful
    Then logout is successful
  
  Examples: Valid
	|username|password|
	|test|test@123| 
```
* Step2. Add new methods with Given, When and Then Annotation in new/existing Step Defination class file.

```
*************************   Sample Class File  *************************

@RunWith(SerenityRunner.class)
public class LoginStepDefn{

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
	
	@Given("^I provide login credentials \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_provide_login_credentials_and(String username, String password){
		//Write your code
	}

	@When("^I send request to login$")
	public void i_send_request_to_login() {
	    // Write code here that turns the phrase above into concrete actions
		
	}

	@Then("^login is successful$")
	public void login_is_successful() {
		// Write code here that turns the phrase above into concrete actions
	}
	
	@Then("^logout is successful$")
	public void logout_is_successful() {
		// Write code here that turns the phrase above into concrete actions
	}
}
```
* Step3. Create a POJO class based on Request or Response JSON
```
* Create package like..  com.serene.tests.features.pojo.users
* Create class like.. UserInfo or UserResponse
For detail information, please refer usecase present at#  https://www.toolsqa.com/rest-assured/convert-json-to-java-object/
```
* Project Structure
```
src
  + main
  + test
    + java                                Test runners and supporting code
      + features                          Test Runner Class 
      + pojo                              POJO to map Request or Response
      + generic                           Helper class, write core logic which interact with step defination
      + stepDefinition                    Write fature files mapping methods ( Business Logic)
    + resources
      + features                          Feature files
          + Login
          + User
          + Pet
          + Store
		  
             record_a_new_trade.feature 
```










