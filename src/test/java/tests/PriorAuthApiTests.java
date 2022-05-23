package tests;

import models.GenerateToken;
import models.MainFormRequest;
import models.Root;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static spec.Specs.request;
import static spec.Specs.response;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriorAuthApiTests {

//С lombok
    @Tag("api")
    @Test
    @DisplayName("Отображение главной страницы оценки сайта")
    void feedbackPage() {
        MainFormRequest mainFormRequest = new MainFormRequest();
        mainFormRequest.setDevice("DESKTOP");
        mainFormRequest.setLanguage("en-GB");
        mainFormRequest.setPlatform("Win32");
        mainFormRequest.setScreenHeight(947);
        mainFormRequest.setScreenWidth(1387);
        mainFormRequest.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)"
                + "Chrome/101.0.4951.67 Safari/537.36");
        mainFormRequest.setQueryParameters(null);

        Root root = given()
                .spec(request)
                .body(mainFormRequest)
                .when()
                .post("/MGfE8NBevifZsH6Ji/handshake")
                .then()
                .spec(response)
                .log().body()
                .extract().as(Root.class);

        assertEquals("<block>Здравствуйте!</block><block> Спасибо, что посетили"
                + " сайт Приорбанка. Пожалуйста, уделите несколько минут и поделитесь впечатлениями о работе"
                + " сайта</block><block></block>", root.getPayload().getItem().getName().getRu());
        assertEquals("<block>Ваши ответы доступны только Приорбанку и"
                + " будут использованы для предоставления вам качественных услуг</block>",
                root.getPayload().getItem().getDescription().getRu());
    }

    @Tag("api")
    @Test
    @DisplayName("Запрос вопроса из неначатого опроса")
    void firstAnswerPage() {
        GenerateToken generateToken = new GenerateToken();
        String token = generateToken.haveToken();

        given()
                .spec(request)
                .body("[{\"comment\":null,\"options\":[],\"questionId\":40391,\"startedAt\":\"2022-05-17T18:10:37.179Z"
                        + "\",\"value\":7},{\"comment\":null,\"options\":[],\"questionId\":40392,\"startedAt\":\"2022"
                        + "-05-17T18:10:37.179Z\",\"value\":6},{\"comment\":null,\"options\":[],\"questionId\":40393,"
                        + "\"startedAt\":\"2022-05-17T18:10:37.179Z\",\"value\":5}]")
                .when()
                .post("/MGfE8NBevifZsH6Ji/" + token + "/answer")
                .then()
                .log().body()
                .body("errors.code[0]", is("ANSWER_POLL_NOT_STARTED"));
    }

    @Tag("api")
    @Test
    @DisplayName("Запрос вопроса из пройденного опроса")
    void secondAnswerPage() {
        given()
                .spec(request)
                .body("[{\"comment\":null,\"options\":[],\"questionId\":40389,\"startedAt\":\"2022-05-17T17:47:58.39"
                        + "7Z\",\"value\":6}]")
                .when()
                .post("/MGfE8NBevifZsH6Ji/6fa9cbca-4328-4fef-809f-a31e4000d498/answer")
                .then()
                .spec(response)
                .log().body()
                .body("errors.name[0]", is("Опрос недоступен"))
                .body("errors.text[0]", is("Вы уже проходили этот опрос. Повторное прохождение недоступно"))
                .body("errors.code[0]", is("SURVEY_ALREADY_TAKEN"));
    }

}
