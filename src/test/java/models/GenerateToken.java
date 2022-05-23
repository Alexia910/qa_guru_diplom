package models;

import static io.restassured.RestAssured.given;
import static spec.Specs.request;
import static spec.Specs.response;

public class GenerateToken {
    public String haveToken(){
        String token = given()
                .spec(request)
                .body("{\"device\":\"DESKTOP\",\"language\":\"en-GB\",\"platform\":\"Win32\",\"screenHeight\":947,"
                        + "\"screenWidth\":1387,\"userAgent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit"
                        + "/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36\",\"queryParameters\":{}}")
                .when()
                .post("/MGfE8NBevifZsH6Ji/handshake")
                .then()
                .spec(response)
                .extract().path("payload.surveyResultIdentity");
        return token;
    }
}
