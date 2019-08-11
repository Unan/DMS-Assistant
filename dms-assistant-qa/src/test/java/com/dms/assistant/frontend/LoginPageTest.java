package com.dms.assistant.frontend;

import com.dms.assistant.ConfigDMS;
import com.dms.assistant.frontend.steps.LoginPageSteps;
import com.dms.assistant.frontend.utils.DefaultTestListener;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.dms.assistant.backend.constants.Constants.CORRECT_EMAIL_1;
import static com.dms.assistant.backend.constants.Constants.INCORRECT_EMAIL;
import static com.dms.assistant.frontend.constants.Constants.SURVEY_ROUTE;

@ContextConfiguration(classes = {ConfigDMS.class})
@Listeners({DefaultTestListener.class})
@Epic("UI")
@Feature("Authorization")
@Story("[DMS-25] Implement layout of Auth page")
public class LoginPageTest extends AbstractTestNGSpringContextTests {

    @Autowired
    LoginPageSteps loginPageSteps;

    @BeforeMethod(groups = {"UI"})
    public void openLoginPage() {
        loginPageSteps.openLoginPage();
    }

    @Test(groups = {"Smoke", "UI"}, description = "Page initialization test")
    public void pageInitializationTest() {
        loginPageSteps.checkElementsDisplaying();
        loginPageSteps.checkFeedbackLink();
    }

    @Test(groups = {"UI"}, description = "Test login authorization")
    public void loginAuthorizationTest() {
        loginPageSteps.checkLoginAuthorization();
    }

    @Test(groups = {"UI"}, description = "Google authorization button test")
    public void signInButtonTest() {
        loginPageSteps.checkGoogleAuthorization();
    }

    @Test(groups = {"UI"}, description = "Checking sign in with correct email", enabled = false)
    public void signInWithCorrectLoginTest() {
        loginPageSteps.signInWithLogin(CORRECT_EMAIL_1);
        loginPageSteps.checkRedirect(SURVEY_ROUTE);
    }

    @Test(groups = {"UI"}, description = "Checking sign in with incorrect email")
    public void signInWithIncorrectLoginTest() {
        loginPageSteps.signInWithLogin(INCORRECT_EMAIL);
        loginPageSteps.checkErrorMessage();
    }
}
