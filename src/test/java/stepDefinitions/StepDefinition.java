package stepDefinitions;

import static org.hamcrest.Matchers.lessThan;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.serenitybdd.rest.SerenityRest;
import utils.ReusableSpecifications;

public class StepDefinition extends ReusableSpecifications {
	
	RequestSpecification rspec;
	ResponseSpecification respec;
	Response response;
	
	@Given("^I access Github API endpoint with valid date \"([^\"]*)\"$")
    public void accessAPI(String date) throws FileNotFoundException {
        rspec = SerenityRest.given().spec(genericRequestSpec()).queryParam("since", date);
    }

	
	@When("^I execute the GET HTTP method$")
    public void executeMethod() {
    	response = rspec.when().get("gists/public")
    			.then().spec(genericResponseSpec()).extract().response();
    }

	
	@Then("^I should see the statuscode as '200'$")
    public void verifyStatusCode() {
    	Assert.assertEquals(response.getStatusCode(),200);
    }

}

