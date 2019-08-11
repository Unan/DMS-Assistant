package com.dms.assistant.frontend.steps;

import com.dms.assistant.backend.models.Employee;
import com.dms.assistant.backend.models.EmployeeList;
import com.dms.assistant.backend.models.Token;
import com.dms.assistant.backend.steps.AdminEmployeeAvailabilitySteps;
import com.dms.assistant.backend.steps.LoginSteps;
import com.dms.assistant.frontend.models.EmployeeFront;
import com.dms.assistant.frontend.pages.AdminPage;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.dms.assistant.backend.constants.Constants.ADMIN;
import static com.dms.assistant.backend.models.Insurance.InsuranceType.NONE;

@Component
public class AdminPageSteps {

    @Autowired
    private LoginSteps loginSteps;

    @Autowired
    private AdminEmployeeAvailabilitySteps adminEmployeeAvailabilitySteps;

    @Autowired
    private AdminPage adminPage;

    @Step("Opening admin page")
    public void openAdminPage() {
        adminPage.openPage();
    }

    @Step("Checking that all admin fields is correctly visible")
    public void checkAdminFieldsIsVisible() {
        adminPage.checkAdminFieldsIsVisible();
        adminPage.checkInsuranceSelectIsVisible();
    }

    @Step("Select all insurances")
    public void selectAllInsurances() {
        adminPage.selectEmployeeFilledType(AdminPage.FilledType.ALL);
    }

    @Step("Select filled insurances")
    public void selectFilledInsurances() {
        adminPage.selectEmployeeFilledType(AdminPage.FilledType.FILLED);
    }

    @Step("Select not filled insurance")
    public void selectNotFilledInsurances() {
        adminPage.selectEmployeeFilledType(AdminPage.FilledType.NOT_FILLED);
    }

    @Step("Check employee {actualEmployee.fullName}")
    public void checkEmployee(EmployeeFront actualEmployee, Employee expectedEmployee) {
        actualEmployee.equals(expectedEmployee);
    }

    @Step("Check all employees in table")
    public void checkAllInsuranceSelect() {
        Token token = loginSteps.getEmployeeToken(ADMIN);
        EmployeeList allEmployees = adminEmployeeAvailabilitySteps.getAllEmployees(token);
        for (int i = 0; i < 5; i++) {
            EmployeeFront employeeFront = adminPage.getEmployeeById(i);
            checkEmployee(employeeFront, allEmployees.get(i));
        }
    }

    @Step("Check filled employees in table")
    public void checkFilledInsuranceSelect() {
        Token token = loginSteps.getEmployeeToken(ADMIN);
        EmployeeList filledEmployees = adminEmployeeAvailabilitySteps.getAllEmployees(token).getEmployeeByNotInsuranceType(NONE);
        for (int i = 0; i < filledEmployees.size(); i++) {
            EmployeeFront employeeFront = adminPage.getEmployeeById(i);
            checkEmployee(employeeFront, filledEmployees.get(i));
        }
    }

    @Step("Checking not filled employees in table")
    public void checkNotFilledInsuranceSelect() {
        Token token = loginSteps.getEmployeeToken(ADMIN);
        EmployeeList notFilledEmployees = adminEmployeeAvailabilitySteps.getAllEmployees(token).getEmployeeByInsuranceType(NONE);
        for (int i = 0; i < notFilledEmployees.size(); i++) {
            EmployeeFront employeeFront = adminPage.getEmployeeById(i);
            checkEmployee(employeeFront, notFilledEmployees.get(i));
        }
    }
}
