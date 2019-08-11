package com.dms.assistant.frontend.steps;

import com.codeborne.selenide.WebDriverRunner;
import com.dms.assistant.frontend.pages.LoginPage;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class LoginPageSteps {

    @Autowired
    LoginPage loginPage;

    @Step("Check that elements displaying correctly")
    public void checkElementsDisplaying() {
        loginPage.checkPageElementsInitialization();
    }

    @Step("Check feedback link")
    public void checkFeedbackLink() {
        loginPage.checkFeedbackLink();
    }

    @Step("Check login authorization")
    public void checkLoginAuthorization() {
        loginPage.checkLoginFieldIsNotVisible();
        loginPage.switchAuthorizationType();
        loginPage.checkLoginFieldIsVisible();
        loginPage.checkSignInButtonIsVisible();
    }

    @Step("Check Google authorization redirect")
    public void checkGoogleAuthorization() {
        loginPage.clickSignInWithGoogle();
        loginPage.checkSignInWithGoogleRedirect();
    }

    @Step("Page opening")
    public LoginPage openLoginPage() {
        return loginPage.openPage();
    }

    @Step("Sign in with email: {0}")
    public void signInWithLogin(String email) {
        loginPage.switchAuthorizationType();
        loginPage.fillLoginField(email);
        loginPage.clickSignInButton();
    }

    @Step("Checking redirect on: {0}")
    public void checkRedirect(String route) {
        assertThat(WebDriverRunner.url()).as("Redirect on " + route + " page failed").contains(route);
    }

    @Step("Checking error message is visible")
    public void checkErrorMessage() {
        loginPage.checkErrorMessageIsVisible();
    }
}
