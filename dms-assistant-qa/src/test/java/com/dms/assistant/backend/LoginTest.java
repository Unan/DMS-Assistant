package com.dms.assistant.backend;

import com.dms.assistant.ConfigDMS;
import com.dms.assistant.backend.steps.LoginSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static com.dms.assistant.backend.constants.Constants.CORRECT_LOGIN_1;
import static com.dms.assistant.backend.constants.Constants.INCORRECT_LOGIN;

@ContextConfiguration(classes = ConfigDMS.class)
@Epic("Services")
@Feature("Login API tests")
@Story("[DMS-16] Integrate with SSO")
public class LoginTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private LoginSteps loginSteps;

    @Test(groups = {"Services", "Smoke"}, description = "Check login request with correct login")
    public void correctAuthTest() {
        loginSteps.correctLoginCheck(CORRECT_LOGIN_1);
    }

    @Test(groups = {"Services"}, description = "Check login request with incorrect login")
    public void incorrectAuthTest() {
        loginSteps.incorrectLoginCheck(INCORRECT_LOGIN);
    }
}
