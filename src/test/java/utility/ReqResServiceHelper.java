package utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqResServiceHelper {

    ObjectMapper objectMapper;

    public ReqResServiceHelper() {
        this.objectMapper = new ObjectMapper();
    }

    public Response createUser(String createUserAPIRequest) throws JsonProcessingException {

//        System.out.println("createUserAPIRequest: " + createUserAPIRequest);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(createUserAPIRequest)
                .post("/users");

//        System.out.println("apiresponse: " + response.getBody().prettyPrint());
//        System.out.println("apiresponsestatus line: " + response.getStatusLine());

        return response;
    }

}
