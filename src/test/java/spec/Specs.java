package spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static listeners.CustomAllureListener.withCustomTemplates;

public class Specs {

    public static RequestSpecification request = with()
            .baseUri("https://new.oprosso.net")
            .basePath("/api/public/panel")
            .filter(withCustomTemplates())
            .contentType("application/json; charset=UTF-8");

    public static ResponseSpecification response = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}