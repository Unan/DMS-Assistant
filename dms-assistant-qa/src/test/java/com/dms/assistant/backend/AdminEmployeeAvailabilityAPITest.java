package com.dms.assistant.backend;

import com.dms.assistant.ConfigDMS;
import com.dms.assistant.backend.models.Employee;
import com.dms.assistant.backend.models.EmployeeList;
import com.dms.assistant.backend.models.Token;
import com.dms.assistant.backend.steps.AdminEmployeeAvailabilitySteps;
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
@Feature("Admin API tests")
@Story("[DMS-20] Return list of employees")
public class AdminEmployeeAvailabilityAPITest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AdminEmployeeAvailabilitySteps adminEmployeeAvailabilitySteps;

    @Autowired
    private LoginSteps loginSteps;

    @Test(description = "Smoke all employees status test", groups = {"Services", "Smoke"})
    public void smokeAllEmployeesStatusTest() {
        Token token = loginSteps.getEmployeeToken(ADMIN);
        adminEmployeeAvailabilitySteps.checkAllEmployeesStatus(token);
    }

    @Test(description = "Smoke employees without insurance status test", groups = {"Services", "Smoke"})
    public void smokeEmployeesWithoutInsuranceStatusTest() {
        Token token = loginSteps.getEmployeeToken(ADMIN);
        adminEmployeeAvailabilitySteps.checkEmployeesWithoutInsuranceStatus(token);
    }

    @Test(description = "Getting all employees availability test", groups = {"Services"})
    public void getAllEmployeesAvailabilityTest() {
        Token token = loginSteps.getEmployeeToken(ADMIN);
        EmployeeList employees = adminEmployeeAvailabilitySteps.getAllEmployees(token);
        adminEmployeeAvailabilitySteps.checkAllEmployees(employees);
    }

    @Test(description = "Getting all employees without insurance availability test", groups = {"Services"})
    public void getAllEmployeesWithoutInsuranceAvailabilityTest() {
        Token token = loginSteps.getEmployeeToken(ADMIN);
        EmployeeList employeesWithoutIns = adminEmployeeAvailabilitySteps.getEmployeesWithoutInsurance(token);
        adminEmployeeAvailabilitySteps.checkEmployeesWithoutInsurance(employeesWithoutIns);
    }

    @Test(description = "Getting all employees by USER", groups = {"Services"})
    public void getAllEmployeesByUser() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        adminEmployeeAvailabilitySteps.checkGetAllEmployeesByUser(token);
    }

    @Test(description = "Getting all employees without insurance by USER", groups = {"Services"})
    public void getAllEmployeesWithoutInsuranceByUser() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        adminEmployeeAvailabilitySteps.checkGetAllEmployeesWithoutInsuranceByUser(token);
    }

    @Test(description = "Getting employee by email as ADMIN", groups = {"Services"})
    public void getEmployeeByEmail() {
        Token token = loginSteps.getEmployeeToken(ADMIN);
        Employee employee = adminEmployeeAvailabilitySteps.getEmployeeByEmail(token, CORRECT_EMAIL_1);
    }

    @Test(description = "Getting employee by email as USER", groups = {"Services"})
    public void getEmployeeByEmailAsUser() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        adminEmployeeAvailabilitySteps.getEmployeeByEmailAsUser(token, CORRECT_EMAIL_3);
    }

    @Test(description = "Getting employee by incorrect mail", groups = {"Services"})
    public void getEmployeeByIncorrectEmail() {
        Token token = loginSteps.getEmployeeToken(ADMIN);
        adminEmployeeAvailabilitySteps.getEmployeeByIncorrectEmail(token, INCORRECT_EMAIL);
    }
}
