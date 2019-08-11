package com.dms.assistant.backend.steps;

import com.dms.assistant.backend.clients.EmployeeClient;
import com.dms.assistant.backend.models.Employee;
import com.dms.assistant.backend.models.Insurance;
import com.dms.assistant.backend.models.Relative;
import com.dms.assistant.backend.models.Token;
import com.dms.assistant.backend.utils.ObjMapper;
import feign.Response;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.util.Date;

@Component
public class EmployeeSurveySteps {

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private ObjMapper objMapper;

    @Step("Getting information about employee by token")
    public Employee getEmployee(String token) {
        Employee employee = employeeClient.getEmployee(token);
        objMapper.checkValidation(employee);
        return employee;
    }

    @Step("Checking employee")
    public void checkEmployee(Employee expectedEmployee, Employee actualEmployee) {
        Assertions.assertThat(actualEmployee).as("Response returns incorrect data about employee").isEqualTo(expectedEmployee);
    }

    @Step("Getting information about employee by incorrect token {0}")
    public Response getEmployeeInvalid(String token) {
        return employeeClient.getEmployeeInvalid(token);
    }

    @Step("Checking that the response has status code 401 UNAUTHORIZED")
    public void checkEmployeeInvalid(Response response) {
        Assertions.assertThat(response.status()).as("Encountered unexpected status").isEqualTo(HttpURLConnection.HTTP_UNAUTHORIZED);
    }

    @Step("Checking that the response has status code 400 BAD_REQUEST")
    public void checkStatusCodeBadRequest(Response response) {
        Assertions.assertThat(response.status()).as("Encountered unexpected status").isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    @Step("Edit employee fullName on: {1}")
    public void editEmployeeFullName(Employee employee, String fullName) {
        employee.setFullName(fullName);
    }

    @Step("Edit employee birthDate on: {1}")
    public void editEmployeeBirthDate(Employee employee, Date birthDate) {
        employee.setBirthDate(birthDate);
    }

    @Step("Edit employee hireDate on: {1}")
    public void editEmployeeHireDate(Employee employee, Date hireDate) {
        employee.setHireDate(hireDate);
    }

    @Step("Edit employee address on: {1}")
    public void editEmployeeAddress(Employee employee, String address) {
        employee.setAddress(address);
    }

    @Step("Edit employee phoneNumber on: {1}")
    public void editEmployeePhoneNumber(Employee employee, String phoneNumber) {
        employee.setPhoneNumber(phoneNumber);
    }

    @Step("Edit employee role on: {1}")
    public void editEmployeeRole(Employee employee, String role) {
        employee.setRole(role);
    }

    @Step("Edit employee insurance type on: {1}")
    public void editEmployeeInsuranceType(Employee employee, Insurance.InsuranceType insuranceType) {
        employee.setInsuranceType(insuranceType);
    }

    @Step("Edit relative[{1}] fullName on: {2}")
    public void editRelativeFullName(Employee employee, Integer id, String fullName) {
        employee.getRelatives().get(id).setFullName(fullName);
    }

    @Step("Edit relative[{1}] birthDate on: {2}")
    public void editRelativeBirthDate(Employee employee, Integer id, Date birthDate) {
        employee.getRelatives().get(id).setBirthDate(birthDate);
    }

    @Step("Edit relative[{1}] address on: {2}")
    public void editRelativeAddress(Employee employee, Integer id, String address) {
        employee.getRelatives().get(id).setAddress(address);
    }

    @Step("Edit relative[{1}] phoneNumber on: {2}")
    public void editRelativePhoneNumber(Employee employee, Integer id, String phoneNumber) {
        employee.getRelatives().get(id).setPhoneNumber(phoneNumber);
    }

    @Step("Edit relative[{1}] insurance type on: {2}")
    public void editRelativeInsuranceType(Employee employee, Integer id, Insurance.InsuranceType insuranceType) {
        employee.getRelatives().get(id).getInsurance().setInsuranceType(insuranceType);
    }

    @Step("Adding new correct relative")
    public void addEmployeeRelative(Employee employee, Relative relative) {
        employee.addRelative(relative);
    }

    @Step("Deleting relative")
    public void deleteRelative(Employee employee, int id) {
        employee.deleteRelative(id);
    }

    @Step("Putting new information about employee")
    public Employee putEmployee(Employee employee, Token token) {
        Employee employee1 = employeeClient.putEmployee(employee, token.getToken());
        objMapper.checkValidation(employee1);
        return employee1;
    }

    @Step("Putting incorrect information about employee")
    public Response putEmployeeIncorrect(Employee employee, Token token) {
        return employeeClient.putEmployeeIncorrect(employee, token.getToken());
    }

    @Step("Checking edit employee insurance type on: {1}")
    public void checkEditEmployeeInsuranceType(Employee putEmployee, Insurance.InsuranceType insuranceType, Token token) {
        editEmployeeInsuranceType(putEmployee, insuranceType);
        checkPutEmployeeCorrect(putEmployee, token);
    }

    @Step("Checking edit relative[{1}] insurance type on: {2}")
    public void checkEditRelativeInsuranceType(Employee putEmployee, Integer id, Insurance.InsuranceType insuranceType, Token token) {
        editRelativeInsuranceType(putEmployee, id, insuranceType);
        checkPutEmployeeCorrect(putEmployee, token);
    }

    @Step("Checking put new correct information about employee: {putEmployee.email}")
    public void checkPutEmployeeCorrect(Employee putEmployee, Token token) {
        putEmployee(putEmployee, token);
        Employee tempEmployee = getEmployee(token.getToken());
        checkEmployee(tempEmployee, putEmployee);
    }

    @Step("Checking put incorrect information about employee: {putEmployee.email}")
    public void checkPutEmployeeIncorrect(Employee putEmployee, Token token) {
        Response response = putEmployeeIncorrect(putEmployee, token);
        checkStatusCodeBadRequest(response);
    }

    @Step("Checking incorrect edit relative[{1}] fullName on: {2}")
    public void checkEditIncorrectRelativeFullName(Employee putEmployee, Integer id, String fullName, Token token) {
        editRelativeFullName(putEmployee, id, fullName);
        checkPutEmployeeIncorrect(putEmployee, token);
    }

    @Step("Checking incorrect edit relative[{1}] birthDate on: {2}")
    public void checkEditIncorrectRelativeBirthDate(Employee putEmployee, Integer id, Date birthDate, Token token) {
        editRelativeBirthDate(putEmployee, id, birthDate);
        checkPutEmployeeIncorrect(putEmployee, token);
    }

    @Step("Checking incorrect edit relative[{1}] address on: {2}")
    public void checkEditIncorrectRelativeAddress(Employee putEmployee, Integer id, String address, Token token) {
        editRelativeAddress(putEmployee, id, address);
        checkPutEmployeeIncorrect(putEmployee, token);
    }

    @Step("Checking incorrect edit relative[{1}] phoneNumber on: {2}")
    public void checkEditIncorrectRelativePhoneNumber(Employee putEmployee, Integer id, String phoneNumber, Token token) {
        editRelativePhoneNumber(putEmployee, id, phoneNumber);
        checkPutEmployeeIncorrect(putEmployee, token);
    }
}
