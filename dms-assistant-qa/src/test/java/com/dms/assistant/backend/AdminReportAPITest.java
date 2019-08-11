package com.dms.assistant.backend;

import com.dms.assistant.ConfigDMS;
import com.dms.assistant.backend.models.Token;
import com.dms.assistant.backend.steps.AdminReportSteps;
import com.dms.assistant.backend.steps.LoginSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static com.dms.assistant.backend.constants.Constants.ADMIN;
import static com.dms.assistant.backend.constants.Constants.CORRECT_LOGIN_3;

@ContextConfiguration(classes = ConfigDMS.class)
@Epic("Services")
@Feature("Admin API tests")
@Story("[DMS] Make report")
public class AdminReportAPITest extends AbstractTestNGSpringContextTests {

    @Autowired
    private LoginSteps loginSteps;

    @Autowired
    private AdminReportSteps adminReportSteps;

    @Test(description = "Verification of the correct content of the final report", groups = {"Services", "temp", "Smoke"})
    public void getMakeReportByAdmin() {
        Token token = loginSteps.getEmployeeToken(ADMIN);
        adminReportSteps.checkMakeReport(token);
    }

    @Test(description = "Getting final report by USER", groups = {"Services"})
    public void getMakeReportByUser() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        adminReportSteps.getMakeReportByUser(token);
    }
}
