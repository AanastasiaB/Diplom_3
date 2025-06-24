package site.stellarburgers.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import site.stellarburgers.model.User;

public class UserClient {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private static final String REGISTER_URL = BASE_URL + "/api/auth/register";
    private static final String DELETE_URL = BASE_URL + "/api/auth/user";
    private static final String LOGIN_URL = BASE_URL + "/api/auth/login";

    public Response create(User user) {
        return RestAssured.given()
                .header("Content-type", "application/json")
                .body(user)
                .post(REGISTER_URL);
    }

    public Response delete(String accessToken) {
        return RestAssured.given()
                .header("Authorization", accessToken)
                .delete(DELETE_URL);
    }

    public Response login(User user) {
        return RestAssured.given()
                .header("Content-type", "application/json")
                .body(user)
                .post(LOGIN_URL);
    }
}