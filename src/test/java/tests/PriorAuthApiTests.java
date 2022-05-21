package tests;

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
    @DisplayName("Отображение первой формы оценки")
    void firstAnswerPage() {
        given()
                .spec(request)
                .body("[{\"comment\":null,\"options\":[],\"questionId\":40391,\"startedAt\":\"2022-05-17T18:10:37.179Z"
                        + "\",\"value\":7},{\"comment\":null,\"options\":[],\"questionId\":40392,\"startedAt\":\"2022"
                        + "-05-17T18:10:37.179Z\",\"value\":6},{\"comment\":null,\"options\":[],\"questionId\":40393,"
                        + "\"startedAt\":\"2022-05-17T18:10:37.179Z\",\"value\":5}]")
                .when()
                .post("/MGfE8NBevifZsH6Ji/6fa9cbca-4328-4fef-809f-a31e4000d498/answer")
                .then()
                .spec(response)
                .log().body()
                .body("payload.item.name.ru", is("<p>Исходя из вашего опыта использования сайта, насколько"
                        + " вероятно, что Вы порекомендуете его своим друзьям и знакомым по шкале от 0"
                        + " до 10?</p><p></p>"))
                .body("payload.item.description.ru", is("<p>где 0 – точно не порекомендую, 10 – точно"
                        + " порекомендую</p><p></p>"));
    }

    @Tag("api")
    @Test
    @DisplayName("Отображение второй формы оценки")
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
                .body("payload.item.name.ru", is("<p>Что нужно улучшить в работе сайта, чтобы Вы поставили"
                        + " более высокую оценку?</p>"));
    }

    @Tag("api")
    @Test
    @DisplayName("Отображение третьей формы оценки")
    void thirdAnswerPage() {
        given()
                .spec(request)
                .body("[{\"comment\":null,\"options\":[],\"questionId\":40390,\"startedAt\":\"2022-05-17T17:53:58.158Z"
                        + "\",\"value\":null,\"skipped\":true}]")
                .when()
                .post("/MGfE8NBevifZsH6Ji/6fa9cbca-4328-4fef-809f-a31e4000d498/answer")
                .then()
                .spec(response)
                .log().body()
                .body("payload.item.name.ru", is("<p>Благодарим за высокую оценку. Что Вы хотели бы улучшить"
                        + " в работе сайта?</p>"));
    }

}
