package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import pages.AuthorizationPage;
import pages.UserPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;


public class PriorAuthUiTests extends TestBase{

    AuthorizationPage authorizationPage = new AuthorizationPage();
    UserPage userPage = new UserPage();

    @Tag("ui")
    @Test
    @DisplayName("Проверка отображения формы входа")
    void displayingLoginForm() {
        step("Наличие названия формы", () -> {
            authorizationPage.checkLoginForm();
        });
        step("Наличие кнопок на форме входа", () -> {
            authorizationPage.checkLoginButton("Войти");
            authorizationPage.checkLoginButton("Регистрация");
            authorizationPage.checkRecoveryButton("Восстановление доступа");
        });
    }

    @Tag("ui")
    @Test
    @DisplayName("Проверка перехода в демо-версию АЧ")
    void switchToDemo() {
        step("Переход на вкладку Стать клиентом", () -> {
            authorizationPage.switchNewClientTab();
        });
        step("Наличие кнопки перехода в демо-версию", () -> {
            $(".link_demo span").should(Condition.visible, Duration.ofSeconds(30));
            authorizationPage.checkDemoButton("Демо-версия");
        });
        step("Переход в демо-версию", () -> {
            authorizationPage.goToDemoVersion();
            userPage.checkMainHeader("Рабочий стол");
        });
    };

    @Tag("ui")
    @Test
    @DisplayName("Отображение элементов на вкладке Вопросы и ответы")
    void switchToQuestions() {
        step("Переход на вкладку Вопросы и ответы", () -> {
            authorizationPage.goToQuestionTab();
        });
        step("Отображение списка вопросов", () -> {
            authorizationPage.checkFirstQuestion("Как подключиться к Интернет-банку самостоятельно?");
            authorizationPage.checkFourthQuestion("Можно ли получать М-код, находясь за границей?");
        });
        step("Отображение ответа", () -> {
            authorizationPage.checkThirdAnswer(
                    "М-код поступает в виде смс-сообщения на зарегистрированный в Банке номер мобильного телефона"
                    + " и служит дополнительной мерой безопасности при проведении платежей");
        });

    };

    @Tag("ui")
    @Test
    @DisplayName("Отображение иконок соцсетей")
    void socialIcons() {
        step("Отображение иконки instagram", () -> {
            authorizationPage.goToSocials();
            authorizationPage.checkInst();
        });
        step("Отображение иконки twitter", () -> {
            authorizationPage.checkTwitter();
        });
        step("Отображение иконки facebook", () -> {
            authorizationPage.checkFb();
        });
        step("Отображение иконки vk", () -> {
            authorizationPage.checkVk();
        });
        step("Отображение иконки telegram", () -> {
            authorizationPage.checkTg();
        });
    };

    @Tag("ui")
    @Test
    @DisplayName("Отображение курсов валют за наличные")
    void currencyRateForCards() {
        step("Отображение блока курсов валют", () -> {
            authorizationPage.goToCurrencyRatesBlock("Курсы валют");
        });
        step("Переход к курсу валют за наличные", () -> {
            authorizationPage.goToCurrencyRates("USD", "от 5 000 до 50 000 RUB");
        });
    };

    @Tag("ui")
    @Test
    @DisplayName("Создание депозита")
    void depositFile() throws Exception {
        step("Переход к странице создания депозита", () -> {
            authorizationPage.switchNewClientTab();
            authorizationPage.goToDemoVersion();
            userPage.goToNewDeposit();
        });
        step("Заполнение форм при создании депозита", () -> {
            userPage.enterDataInForm("655", "6");
        });
        step("Отображение страницы договора оферты", () -> {
            userPage.checkStepTitle("Договор оферты");
        });
        step("Отображение страницы завершения операции", () -> {
            userPage.goToFinishStep();
            userPage.checkStepTitle("Открытие электронного депозита");
            userPage.checkFinish("Заявление на открытие онлайн-депозита находится в обработке.");
        });
    };

}
