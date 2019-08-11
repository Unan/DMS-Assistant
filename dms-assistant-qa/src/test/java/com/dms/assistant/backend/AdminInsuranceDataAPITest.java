package com.dms.assistant.backend;

import com.dms.assistant.ConfigDMS;
import com.dms.assistant.backend.models.InsuranceData;
import com.dms.assistant.backend.models.Token;
import com.dms.assistant.backend.steps.AdminInsuranceDataSteps;
import com.dms.assistant.backend.steps.BaseSteps;
import com.dms.assistant.backend.steps.EmployeeInsuranceDataSteps;
import com.dms.assistant.backend.steps.LoginSteps;
import feign.Response;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.dms.assistant.backend.constants.Constants.*;

@ContextConfiguration(classes = ConfigDMS.class)
@Epic("Services")
@Feature("Admin API tests")
@Story("[DMS] Return insurance data")
public class AdminInsuranceDataAPITest extends AbstractTestNGSpringContextTests {

    @Autowired
    private LoginSteps loginSteps;

    @Autowired
    private AdminInsuranceDataSteps adminInsuranceDataSteps;

    @Autowired
    private EmployeeInsuranceDataSteps employeeInsuranceDataSteps;

    @Autowired
    private BaseSteps baseSteps;

    @Test(description = ("Setting new correct insurance pricing information by ADMIN"), groups = {"Services", "Smoke"})
    public void setInsuranceDataByAdmin() {
        Token token = loginSteps.getEmployeeToken(ADMIN);
        adminInsuranceDataSteps.putInsuranceData(NEW_INSURANCE_DATA, token);
        InsuranceData insuranceData = employeeInsuranceDataSteps.getInsuranceData(token);
        baseSteps.checkInsuranceData(NEW_INSURANCE_DATA, insuranceData);
    }

    @Test(description = ("Setting new correct insurance pricing information by USER"), groups = {"Services"})
    public void setInsuranceDataByUser() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_5);
        Response response = adminInsuranceDataSteps.putInsuranceData(NEW_INSURANCE_DATA, token);
        baseSteps.checkStatusCodeForbidden(response);
    }

    @AfterMethod(description = "Putting init insurance data", groups = {"Services"})
    public void putInitInsuranceData() {
        Token token = loginSteps.getEmployeeToken(ADMIN);
        adminInsuranceDataSteps.putInsuranceData(INSURANCE_DATA, token);
    }
}
