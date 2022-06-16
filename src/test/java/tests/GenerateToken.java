package tests;

import static io.restassured.RestAssured.given;
import static spec.Specs.request;
import static spec.Specs.response;

public class GenerateToken {
    public String haveToken(){
        String token = given()
                .spec(request)
                .body("{\"device\":\"DESKTOP\",\"language\":\"en-GB\",\"platform\":\"Win32\",\"screenHeight\":947"
                        + ",\"screenWidth\":1175,\"surveyResultIdentity\":\"42b40120-e9c4-48f8-b8e2-75ec33f35199\","
                        + "\"userAgent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like"
                        + "Gecko) Chrome/102.0.0.0 Safari/537.36\",\"queryParameters\":{}}")
                .when()
                .post("/MGfE8NBevifZsH6Ji/open")
                .then()
                .spec(response)
                .extract().path("payload.surveyResultIdentity");
        return token;
    }
}
