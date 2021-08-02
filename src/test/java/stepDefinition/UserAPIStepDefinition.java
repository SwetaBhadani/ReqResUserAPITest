package stepDefinition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import responsePojo.CreateUserResponse;
import responsePojo.GetUserByIDResponse;
import responsePojo.GetUsersResponse;
import utility.Commons;
import utility.ReqResServiceHelper;
import utility.RequestHelper;

import java.util.List;
import java.util.Map;

public class UserAPIStepDefinition {

    ReqResServiceHelper reqResServiceHelper;
    RequestHelper requestHelper;
    ObjectMapper objectMapper;

    String createUserAPIRequest;

    Response response;

    public UserAPIStepDefinition() {
        reqResServiceHelper = new ReqResServiceHelper();
        requestHelper = new RequestHelper();
        objectMapper = new ObjectMapper();
    }

    @Given("As a user, I have a valid endpoint")
    public void i_have_the_endpoint() {

        RestAssured.baseURI = Commons.USER_API_URL;
    }

    @When("I hit a request with below details to create a user")
    public void i_hit_a_request_with_below_details_to_create_a_user(List<Map<String, String>> dataTable) throws JsonProcessingException {

        for (Map<String, String> map : dataTable) {
            String name = map.get("name");
            String job = map.get("job"); // null

            createUserAPIRequest = requestHelper.getCreateUserAPIRequest(map);
            response = reqResServiceHelper.createUser(createUserAPIRequest);
        }
    }

    @Then("I verify the creation of user from the response")
    public void i_verify_the_creation_of_user_from_the_response() throws JsonProcessingException {
        CreateUserResponse createUserResponseObject = objectMapper.readValue(response.getBody().asString(), CreateUserResponse.class);
        Assert.assertTrue(createUserAPIRequest.contains(createUserResponseObject.getName()));
        Assert.assertTrue(createUserAPIRequest.contains(createUserResponseObject.getJob()));
    }


    @Then("I verify the status code is equal to {int}")
    public void i_verify_the_status_code_is_equal_to(int statusCode) {

        Assert.assertEquals(statusCode, response.getStatusCode());
    }


    @When("I hit the endpoint with invalid request body")
    public void i_hit_the_endpoint_with_invalid_request_body() throws JsonProcessingException {
        createUserAPIRequest = requestHelper.getCreateUserAPIInvalidRequest();
        response = reqResServiceHelper.createUser(createUserAPIRequest);
    }

    @Then("I get an error with {int} status code")
    public void i_get_an_error_with_status_code(int statusCode) {

        Assert.assertEquals(statusCode, response.getStatusCode());
    }


    @When("I hit a GET request")
    public void i_hit_a_get_request() {
        response = reqResServiceHelper.getUsers();
    }

    @Then("I validate number of users returned is equal to per page value")
    public void i_validate_number_of_users_returned_is_equal_to_per_page_value() throws JsonProcessingException {
        GetUsersResponse getUsersResponseJson = objectMapper.readValue(response.getBody().asString(), GetUsersResponse.class);

        int perPageCount = getUsersResponseJson.getPer_page();
        int userDataLength = getUsersResponseJson.getData().size();

        Assert.assertEquals(perPageCount, userDataLength);
    }


    @When("I hit a GET request with user id {int}")
    public void i_hit_a_get_request_with_user_id(Integer userId) {
        response = reqResServiceHelper.getUsersById(userId);
    }

    @Then("I validate the user details for user id {int} is same as below")
    public void i_validate_the_user_details_for_user_id_is_same_as_below(int userId, List<Map<String, String>> dataTable) throws JsonProcessingException {
        GetUserByIDResponse getUserByIDResponseJson = objectMapper.readValue(response.getBody().asString(), GetUserByIDResponse.class);

        Map<String, String> userData = dataTable.get(0);

        Assert.assertEquals(userId, getUserByIDResponseJson.getData().getId());
        Assert.assertEquals(userData.get("email"), getUserByIDResponseJson.getData().getEmail());
        Assert.assertEquals(userData.get("first_name"), getUserByIDResponseJson.getData().getFirst_name());
        Assert.assertEquals(userData.get("last_name"), getUserByIDResponseJson.getData().getLast_name());
    }
}
