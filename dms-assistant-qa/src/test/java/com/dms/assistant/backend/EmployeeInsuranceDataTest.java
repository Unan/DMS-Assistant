package com.dms.assistant.backend;

import com.dms.assistant.ConfigDMS;
import com.dms.assistant.backend.models.InsuranceData;
import com.dms.assistant.backend.models.Token;
import com.dms.assistant.backend.steps.BaseSteps;
import com.dms.assistant.backend.steps.EmployeeInsuranceDataSteps;
import com.dms.assistant.backend.steps.LoginSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static com.dms.assistant.backend.constants.Constants.*;

@ContextConfiguration(classes = ConfigDMS.class)
@Epic("Services")
@Feature("Survey API tests")
@Story("[DMS] Implement Insurance Data service")
public class EmployeeInsuranceDataTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private EmployeeInsuranceDataSteps employeeInsuranceDataSteps;

    @Autowired
    private LoginSteps loginSteps;

    @Autowired
    private BaseSteps baseSteps;

    @Test(description = "Getting Insurance Data by USER", groups = {"Services", "Smoke"})
    public void getInsuranceDataTestByUser() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_5);
        InsuranceData insuranceData = employeeInsuranceDataSteps.getInsuranceData(token);
        baseSteps.checkInsuranceData(INSURANCE_DATA, insuranceData);
    }

    @Test(description = "Getting Insurance Data by ADMIN", groups = {"Services", "Smoke"})
    public void getInsuranceDataTestByAdmin() {
        Token token = loginSteps.getEmployeeToken(ADMIN);
        InsuranceData insuranceData = employeeInsuranceDataSteps.getInsuranceData(token);
        baseSteps.checkInsuranceData(INSURANCE_DATA, insuranceData);
    }
}
