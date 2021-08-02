package utility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class ReqResServiceHelper {

    public Response createUser(String createUserAPIRequest) {

//        System.out.println("createUserAPIRequest: " + createUserAPIRequest);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(createUserAPIRequest)
                .post("/users");

//        System.out.println("apiresponse: " + response.getBody().prettyPrint());
//        System.out.println("apiresponsestatus line: " + response.getStatusLine());

        return response;
    }

    public Response getUsers() {
        return getUsers(new HashMap<>());
    }

    public Response getUsers(Map<String, Integer> queryParam) {
        RequestSpecification request = RestAssured.given();

        for (Map.Entry<String, Integer> entry : queryParam.entrySet()) {
            request.queryParam(entry.getKey(), entry.getValue());
        }

        Response responseGetUser = request.get("/users");

        return responseGetUser;
    }

    public Response getUsersById(int id) {
        String path = String.format("/users/%d", id);
        Response getUserByIdResponse = RestAssured.given()
                .get(path);
    return getUserByIdResponse;
    }


}
