package stepDefinition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import responsePojo.CreateUserResponse;
import utility.Commons;
import utility.ReqResServiceHelper;
import utility.RequestHelper;

import java.util.List;
import java.util.Map;

public class UserAPIStepDefinition {

    ReqResServiceHelper reqResServiceHelper;
    RequestHelper requestHelper;
    ObjectMapper objectMapper;

    Response createUserResponse;
    String createUserAPIRequest;

    public UserAPIStepDefinition() {
        reqResServiceHelper = new ReqResServiceHelper();
        requestHelper = new RequestHelper();
        objectMapper = new ObjectMapper();
    }

    @Given("I have the endpoint to create a user")
    public void i_have_the_endpoint_to_create_a_user() {

        RestAssured.baseURI = Commons.USER_API_URL;
    }

    @When("I hit a request with below details to create a user")
    public void i_hit_a_request_with_below_details_to_create_a_user(List<Map<String, String>> dataTable) throws JsonProcessingException {
//        System.out.println("okie");

        for (Map<String, String> map : dataTable) {
            String name = map.get("name");
            String job = map.get("job"); // null

            createUserAPIRequest = requestHelper.getCreateUserAPIRequest(map);
            createUserResponse = reqResServiceHelper.createUser(createUserAPIRequest);
        }
    }

    @Then("I verify the creation of user from the response")
    public void i_verify_the_creation_of_user_from_the_response() throws JsonProcessingException {
        CreateUserResponse createUserResponseObject = objectMapper.readValue(createUserResponse.getBody().asString(), CreateUserResponse.class);
        Assert.assertTrue(createUserAPIRequest.contains(createUserResponseObject.getName()));
        Assert.assertTrue(createUserAPIRequest.contains(createUserResponseObject.getJob()));
    }


    @Then("I verify the status code is equal to {int}")
    public void i_verify_the_status_code_is_equal_to(Integer int1) {

        Assert.assertEquals(201, createUserResponse.getStatusCode());
    }

}
