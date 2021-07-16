package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.serenitybdd.rest.SerenityRest;
import utils.Payloads;
import utils.ReusableSpecifications;

public class StepDefinition extends ReusableSpecifications {
	
	RequestSpecification rspec;
	ResponseSpecification respec;
	Response response;
	JsonPath json;
	String str;
	String expectedMessage;
	
	@Given("^I access Github API endpoint with valid date \"([^\"]*)\"$")
    public void accessAPIwithValidDate(String date) throws FileNotFoundException {
        rspec = SerenityRest.given().spec(genericRequestSpec()).queryParam("since", date);
    }

	
	@When("^I execute the GET HTTP method$")
    public void executeGetMethod() {
    	response = rspec.when().get("gists/public")
    			.then().spec(genericResponseSpec()).extract().response();
    	str = response.asString();
    }

	
	@Then("I should see the statuscode as {int}")
    public void verifyStatusCode(Integer code) {
        Assert.assertEquals(response.getStatusCode(),(int)code);
    }
	
	@Given("^I access Github API endpoint with invalid date \"([^\"]*)\"$")
    public void accessAPIwithInvalidDate(String date) throws FileNotFoundException {
        rspec = SerenityRest.given().spec(genericRequestSpec()).queryParam("since", date);
    }
	
	
	@And("^I should see the expected error message$")
    public void verifyErrorMessage() {
		json = new JsonPath(str);
		expectedMessage = json.get("message");
    	Assert.assertEquals(expectedMessage,"Invalid since parameter: '@@@'. Must be an ISO 8601 timestamp.");
    	
    }
	
	@Given("^I access Github PATCH API endpoint$")
    public void accessPatchAPI() throws IOException{
        rspec = SerenityRest.given().spec(genericRequestSpec()).body(Payloads.updateGist());
    }
	
	@Given("^I access Github DELETE API endpoint$")
    public void accessDeleteAPI() throws IOException{
        rspec = SerenityRest.given().spec(genericRequestSpec());
    }
	
	@When("^I execute the PATCH HTTP method$")
    public void executePatchMethod() {
    	response = rspec.when().patch("gists/123")
    			.then().spec(genericResponseSpec()).extract().response();
    	str = response.asString();
    }
	
	@When("^I execute the DELETE HTTP method$")
    public void executeDeleteMethod() {
    	response = rspec.when().delete("gists/123")
    			.then().spec(genericResponseSpec()).extract().response();
    	str = response.asString();
    }
	
	@And("^I should see the error message as 'Not Found'$")
    public void verifyFailMessage() {
		json = new JsonPath(str);
		expectedMessage = json.get("message");
    	Assert.assertEquals(expectedMessage,"Not Found");
    	
    }

}

