package com.dms.assistant.backend.steps;

import com.dms.assistant.backend.clients.AdminClient;
import com.dms.assistant.backend.models.Employee;
import com.dms.assistant.backend.models.EmployeeList;
import com.dms.assistant.backend.models.Token;
import com.dms.assistant.backend.utils.ObjMapper;
import feign.Response;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.dms.assistant.backend.constants.Constants.EMPLOYEES;
import static com.dms.assistant.backend.constants.Constants.EMPLOYEES_WITHOUT_INSURANCE;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class AdminEmployeeAvailabilitySteps {

    @Autowired
    private AdminClient adminClient;

    @Autowired
    private ObjMapper objMapper;

    @Autowired
    private BaseSteps baseSteps;

    @Step("Checking all employees status code = 200 and body structure")
    public void checkAllEmployeesStatus(Token token) {
        adminClient.getAllEmployees(token.getToken());
    }

    @Step("Checking employees without insurance status code = 200 and body structure")
    public void checkEmployeesWithoutInsuranceStatus(Token token) {
        adminClient.getEmployeesWithoutInsurance(token.getToken());
    }

    @Step("Get list of all employees from server")
    public EmployeeList getAllEmployees(Token token) {
        EmployeeList employees = adminClient.getAllEmployees(token.getToken());
        objMapper.checkValidation(employees);
        return employees;
    }

    @Step("Checking all employees")
    public void checkAllEmployees(EmployeeList employees) {
        assertThat(employees).as("Response returns incorrect data about all employees").isEqualTo(EMPLOYEES);
    }

    @Step("Get list of employees without insurance")
    public EmployeeList getEmployeesWithoutInsurance(Token token) {
        EmployeeList employeesWithoutIns = adminClient.getEmployeesWithoutInsurance(token.getToken());
        objMapper.checkValidation(employeesWithoutIns);
        return employeesWithoutIns;
    }

    @Step("Checking list of employees without insurance")
    public void checkEmployeesWithoutInsurance(EmployeeList employeesWithoutIns) {
        assertThat(employeesWithoutIns).as("Response returns incorrect data about employees without insurance").isEqualTo(EMPLOYEES_WITHOUT_INSURANCE);
    }

    @Step("Getting list of all employees by USER")
    private Response getAllEmployeesByUser(Token token) {
        return adminClient.getAllEmployeesByUser(token.getToken());
    }

    @Step("Checking unobtainable ADMIN service employees by USER")
    public void checkGetAllEmployeesByUser(Token token) {
        Response response = getAllEmployeesByUser(token);
        baseSteps.checkStatusCodeForbidden(response);
    }

    @Step("Getting list of all employees without insurance by USER")
    private Response getAllEmployeesWithoutInsuranceByUser(Token token) {
        return adminClient.getEmployeesWithoutInsuranceByUser(token.getToken());
    }

    @Step("Checking unobtainable ADMIN service employeesWithoutInsurance by USER")
    public void checkGetAllEmployeesWithoutInsuranceByUser(Token token) {
        Response response = getAllEmployeesWithoutInsuranceByUser(token);
        baseSteps.checkStatusCodeForbidden(response);
    }

    @Step("Get employee by email: {employeeEmail}")
    public Employee getEmployeeByEmail(Token token, String employeeEmail) {
        return adminClient.getEmployeeByEmail(token.getToken(), employeeEmail);
    }

    @Step("Get employee by wrong email")
    public void getEmployeeByIncorrectEmail(Token token, String incorrectEmail) {
        Response response = adminClient.getEmployeeByEmailInvalid(token.getToken(), incorrectEmail);
        baseSteps.checkStatusCodeNotFound(response);
    }

    @Step("Try to get employee by mail as user")
    public void getEmployeeByEmailAsUser(Token token, String email) {
        Response response = adminClient.getEmployeeByEmailInvalid(token.getToken(), email);
        baseSteps.checkStatusCodeForbidden(response);
    }
}
