package tests;

import org.junit.jupiter.api.*;
import pages.AuthorizationPage;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;


public class PriorAuthUiTests extends TestBase{

    AuthorizationPage authorizationPage = new AuthorizationPage();

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
    @DisplayName("Отображение кнопок в header")
    void buttonsHeader() {
        String atm = "Банкоматы и отделения";
        String help = "Онлайн-помощник";
        String cources = "Курсы";
        step("Отображение кнопки " + atm, () -> {
            authorizationPage.atmButton(atm);
        });
        step("Отображение кнопки " + help, () -> {
            authorizationPage.helpButton(help);
        });
        step("Отображение кнопки " + cources, () -> {
            authorizationPage.courcesButton(cources);
        });
    };

}
