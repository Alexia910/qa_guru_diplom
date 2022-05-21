package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AuthorizationPage {

    //locators
    private SelenideElement loginFormHeader = $$(".login-form").find(text("Вход в систему"));
    private SelenideElement loginButtons = $(".login-buttons");
    private SelenideElement recoveryButton = $(".recovery-link");
    private SelenideElement newClientTab= $(byText("Стать клиентом"));
    private SelenideElement demoButton = $(".link_demo span");
    private SelenideElement infoTabs= $(".features_tabs");
    private SelenideElement questionTab = $(byText("Вопросы и ответы"));
    private SelenideElement firstQuestion = $(".faq_accordeon_wrapper"
            + " .faq_item:nth-child(1) .question_header");
    private SelenideElement fourthQuestion = $(".faq_accordeon_wrapper"
            + " .faq_item:nth-child(4) .question_header");
    private SelenideElement thirdAnswerTitle = $(".faq_accordeon_wrapper .faq_item:nth-child(3)");
    private SelenideElement thirdAnswer = $(".faq_accordeon_wrapper .faq_item:nth-child(3) .answer_container");
    private SelenideElement socials = $(".socials");
    private SelenideElement inst = $(".insta");
    private SelenideElement twitter = $(".tw");
    private SelenideElement facebook = $(".fb");
    private SelenideElement vk = $(".vk");
    private SelenideElement telegram = $(".tg");
    private SelenideElement currency = $(".currency_rate");
    private SelenideElement currencyInIb =  $$(".rates_kind").find(text("В ИНТЕРНЕТ-БАНКЕ"));
    private SelenideElement currencyCash = $(".rates_kind_list span:nth-child(2)");
    private SelenideElement usdTab = $(".currTabs .cel:nth-child(1)");
    private SelenideElement rubTab = $$(".currTabs .cel:nth-child(3)").find(text("RUB"));
    private SelenideElement rateKind = $(".currTabs .tab-pane:nth-child(3) .rate tr:nth-child(3)");



    //actions
    public void checkLoginForm() {
        loginFormHeader.shouldBe(visible);
    }

    public void checkLoginButton(String title) {
        loginButtons.shouldHave(text(title));
    }

    public void checkRecoveryButton(String title) {
        recoveryButton.shouldHave(text(title));
    }

    public void switchNewClientTab() {
        newClientTab.click();
    }

    public void checkDemoButton(String title) {
        demoButton.shouldHave(text(title));
    }

    public void goToDemoVersion() {
        demoButton.click();
    }

    public void goToQuestionTab() {
        infoTabs.scrollTo();
        questionTab.click();
    }

    public void checkFirstQuestion(String title) {
        firstQuestion.shouldHave(text(title));
    }

    public void checkFourthQuestion(String title) {
        fourthQuestion.shouldHave(text(title));
    }
    public void checkThirdAnswer(String answer) {
        thirdAnswerTitle.click();
        thirdAnswer.shouldHave(text
                (answer));
    }

    public void goToSocials() {
        socials.scrollTo();
    }

    public void checkInst() {
        inst.shouldBe(visible);
    }

    public void checkFb() {
        facebook.shouldBe(visible);
    }

    public void checkVk() {
        vk.shouldBe(visible);
    }

    public void checkTg() {
        telegram.shouldBe(visible);
    }

    public void checkTwitter() {
        twitter.shouldBe(visible);
    }

    public void goToCurrencyRatesBlock(String name) {
        currency.scrollTo();
        currency.shouldHave(text(name));
    }

    public void goToCurrencyRates(String currency,String title) {
        currencyInIb.click();
        currencyCash.click();
        usdTab.shouldHave(text(currency));
        rubTab.click();
        rateKind.shouldHave(text(title));
    }

}
