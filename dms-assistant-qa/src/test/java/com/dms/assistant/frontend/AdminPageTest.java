package com.dms.assistant.frontend;

import com.dms.assistant.ConfigDMS;
import com.dms.assistant.frontend.steps.AdminPageSteps;
import com.dms.assistant.frontend.steps.LoginPageSteps;
import com.dms.assistant.frontend.utils.DefaultTestListener;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.dms.assistant.backend.constants.Constants.ADMIN_EMAIL;

@ContextConfiguration(classes = {ConfigDMS.class})
@Listeners({DefaultTestListener.class})
@Epic("UI")
@Feature("Admin page UI tests")
@Story("[DMS-57] Implement layout of Admin page")
public class AdminPageTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AdminPageSteps adminPageSteps;

    @Autowired
    private LoginPageSteps loginPageSteps;

    @Test(description = "Admin page initializing test", groups = {"UI", "Smoke"}, enabled = false)
    public void checkAllAdminFieldsTest() {
        loginPageSteps.openLoginPage();
        loginPageSteps.signInWithLogin(ADMIN_EMAIL);
        adminPageSteps.checkAdminFieldsIsVisible();
    }

    @Test(description = "Checking all employee select", groups = {"UI"})
    public void checkAllInsuranceSelectTest() {
        loginPageSteps.openLoginPage();
        loginPageSteps.signInWithLogin(ADMIN_EMAIL);
        adminPageSteps.selectAllInsurances();
        adminPageSteps.checkAllInsuranceSelect();
    }

    @Test(description = "Checking employee that filled insurance select", groups = {"UI"})
    public void checkFilledInsuranceSelectTest() {
        loginPageSteps.openLoginPage();
        loginPageSteps.signInWithLogin(ADMIN_EMAIL);
        adminPageSteps.selectFilledInsurances();
        adminPageSteps.checkFilledInsuranceSelect();
    }

    @Test(description = "Checking employee that not filled insurance select", groups = {"UI"})
    public void checkNotFilledInsuranceSelectTest() {
        loginPageSteps.openLoginPage();
        loginPageSteps.signInWithLogin(ADMIN_EMAIL);
        adminPageSteps.selectNotFilledInsurances();
        adminPageSteps.checkNotFilledInsuranceSelect();
    }
}
