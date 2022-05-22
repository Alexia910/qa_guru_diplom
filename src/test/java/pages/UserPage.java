package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UserPage {

    //locators
    private SelenideElement mainHeader = $("#header");
    private SelenideElement deposits = $("#left-panel .submenu:nth-child(6)");
    private SelenideElement newDepositButton = $(byText("Открыть депозит"));
    private SelenideElement firstField = $(".form-control");
    private SelenideElement secondField = $(".form-controls .form-control");
    private SelenideElement stepTitle = $(".step-title");
    private SelenideElement agreeButton = $(".wizard-actions div .btn:nth-child(4)");
    private SelenideElement finishMessage =  $(".form-group");



    //actions
    public void checkMainHeader(String header) {
        mainHeader.shouldBe(visible);
    }

    public void goToNewDeposit() {
        deposits.click();
        newDepositButton.click();
    }

    public void enterDataInForm(String first, String second) {
        firstField.selectOptionByValue(first);
        firstField.pressEnter();
        secondField.selectOptionByValue(second);
        firstField.pressEnter();
    }

    public void checkStepTitle(String title) {
        stepTitle.shouldHave(text(title));
    }

    public void goToFinishStep() {
        agreeButton.hover().click();
    }

    public void checkFinish(String message) {
        finishMessage.shouldHave(text(message));
    }
}
