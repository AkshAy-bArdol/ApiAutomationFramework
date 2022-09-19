package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoclasses.AddPlace;
import pojoclasses.Location;
import resources.APIResources;
import resources.TestBuildData;
import resources.Utils;



public class StepDefinations extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestBuildData data = new TestBuildData();
	static String place_Id;

	
	@Given("Add Place Payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException{
		
		
		 res=given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));

	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		APIResources resourceAPI=APIResources .valueOf(resource);
		System.out.println(resourceAPI.getResource());
		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		response=res.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
		{
			response=res.when().get(resourceAPI.getResource());	
		}
	}
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
		
		
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String Expectedvalue) {
	   
	   assertEquals(getJsonPath(response,keyvalue),Expectedvalue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException{
		 place_Id=getJsonPath(response,"place_id");
		 res=given().spec(requestSpecification()).queryParam("place_id",place_Id);
		 user_calls_with_http_request(resource,"GET");
		 String actualname=getJsonPath(response,"name");
		 assertEquals(actualname,expectedName);

	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_Id));

	}



}
