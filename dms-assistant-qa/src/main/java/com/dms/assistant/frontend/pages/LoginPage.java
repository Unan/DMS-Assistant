package com.dms.assistant.frontend.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.*;
import static com.dms.assistant.frontend.constants.Constants.GOOGLE_AUTHORIZATION_URL;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class LoginPage {

    private static final SelenideElement WELCOME_IMAGE = $(By.className("login-welcome-img"));
    private static final SelenideElement WELCOME_TITLE = $(By.className("login-welcome-title"));
    private static final SelenideElement WELCOME_DESCRIPTION = $(By.className("login-welcome-description"));
    private static final SelenideElement FOOTER_COPYRIGHT = $(By.className("copyright"));
    private static final SelenideElement FOOTER_COMPANY_NAME = $(By.className("copyright"));
    private static final SelenideElement FOOTER_COMPANY_IMAGE = $(By.className("footer-company-logo"));
    private static final SelenideElement LOGIN_FIELD = $("#mat-input-0");
    private static final SelenideElement SIGN_IN_WITH_GOOGLE_BUTTON = $(By.className("google-sign-in"));
    private static final SelenideElement SWITCH_AUTHORIZATION_TYPE_BUTTON = $(By.className("login-toggle"));
    private static final SelenideElement FEEDBACK_LINK = $(By.xpath("//a[contains(@class, 'footer-link')]"));
    private static final SelenideElement SIGN_IN_BUTTON = $(By.xpath("//button[contains(@class, btn-submit)]"));
    private static final SelenideElement ERROR_MESSAGE = $(By.xpath("//div[contains(@class, error-container-box)]"));

    @Value("${HomePageURL}")
    public String HomePageURL;

    public LoginPage openPage() {
        open(HomePageURL);
        assertThat(WebDriverRunner.url()).as("Wrong URL").contains(HomePageURL);
        return page(LoginPage.class);
    }

    public LoginPage fillLoginField(String login) {
        LOGIN_FIELD.setValue(login);
        return page(LoginPage.class);
    }

    public LoginPage clickSignInWithGoogle() {
        SIGN_IN_WITH_GOOGLE_BUTTON.click();
        return page(LoginPage.class);
    }

    public LoginPage switchAuthorizationType() {
        SWITCH_AUTHORIZATION_TYPE_BUTTON.click();
        return page(LoginPage.class);
    }

    public LoginPage clickFeedbackLink() {
        FEEDBACK_LINK.click();
        return page(LoginPage.class);
    }

    public LoginPage clickSignInButton() {
        SIGN_IN_BUTTON.click();
        return page(LoginPage.class);
    }

    public void checkPageElementsInitialization() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(WELCOME_IMAGE.isDisplayed()).as("\"Welcome\" image is not displayed").isTrue();
        softly.assertThat(WELCOME_TITLE.isDisplayed()).as("\"Welcome\" title is not displayed").isTrue();
        softly.assertThat(WELCOME_DESCRIPTION.isDisplayed()).as("\"Welcome\" description is not displayed").isTrue();
        softly.assertThat(FOOTER_COPYRIGHT.isDisplayed()).as("Footer copyright is not displayed").isTrue();
        softly.assertThat(FOOTER_COMPANY_NAME.isDisplayed()).as("Footer company name is not displayed").isTrue();
        softly.assertThat(FOOTER_COMPANY_IMAGE.isDisplayed()).as("Footer company image is not displayed").isTrue();
        softly.assertThat(SIGN_IN_WITH_GOOGLE_BUTTON.isDisplayed()).as("\"Sign in wth Google\" button is not displayed").isTrue();
        softly.assertThat(FEEDBACK_LINK.isDisplayed()).as("Feedback link is not displayed").isTrue();
        softly.assertAll();
    }

    public void checkFeedbackLink() {
        assertThat(FEEDBACK_LINK.attr("href")).as("Wrong feedbackLink").isEqualTo("mailto:ingrid-support@griddynamics.com");
    }

    public void checkSignInWithGoogleRedirect() {
        assertThat(WebDriverRunner.url()).as("Redirect on Google authorization failed").contains(GOOGLE_AUTHORIZATION_URL);
    }

    public void checkLoginFieldIsNotVisible() {
        assertThat(LOGIN_FIELD.isDisplayed()).as("Login filed name is displayed").isFalse();
    }

    public void checkLoginFieldIsVisible() {
        assertThat(LOGIN_FIELD.isDisplayed()).as("Login filed name is not displayed").isTrue();
    }

    public void checkSignInButtonIsVisible() {
        assertThat(SIGN_IN_BUTTON.isDisplayed()).as("\"Sign in\" button is not displayed").isTrue();
    }

    public void checkErrorMessageIsVisible() {
        assertThat(ERROR_MESSAGE.isDisplayed()).as("Error message is not displayed").isTrue();
    }
}
