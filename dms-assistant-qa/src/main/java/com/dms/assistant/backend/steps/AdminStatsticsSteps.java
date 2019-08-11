package com.dms.assistant.backend.steps;

import com.dms.assistant.backend.clients.AdminClient;
import com.dms.assistant.backend.clients.EmployeeClient;
import com.dms.assistant.backend.clients.LoginClient;
import com.dms.assistant.backend.constants.Constants;
import com.dms.assistant.backend.models.*;
import com.dms.assistant.backend.utils.ObjMapper;
import feign.Response;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;

import static com.dms.assistant.backend.constants.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class AdminStatsticsSteps {

    @Autowired
    private LoginClient loginClient;

    @Autowired
    private AdminClient adminClient;

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private ObjMapper objMapper;

    @Step("Get statistics as admin")
    public Statistics getStatisticsAsAdmin() {
        Token token = loginClient.getEmployeeToken(Constants.ADMIN);
        Statistics statistics = adminClient.getStatistics(token.getToken());
        objMapper.checkValidation(statistics);
        return statistics;
    }

    @Step("Try to get statistics as simple user")
    public void getStatisticsAsUser() {
        Token token = loginClient.getEmployeeToken(CORRECT_LOGIN_1);
        Response response = adminClient.getStatisticsAsResponse(token.getToken());
        assertThat(response.status()).as("Incorrect status of response").isEqualTo(HttpURLConnection.HTTP_FORBIDDEN);
    }

    @Step("Check statistics contains correct data")
    public void checkStatisticParameters(Statistics statistics, EmployeeList employees) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(statistics.getEmployeesNumber()).as(NOT_EXPECTED_NUMBER + "employees").isEqualTo(employees.size());
        softly.assertThat(statistics.getEmployeesHaveNone()).as(NOT_EXPECTED_NUMBER + "employees without insurance").isEqualTo(employees.getNumberOfEmployeesByInsuranceType(Insurance.InsuranceType.NONE));
        softly.assertThat(statistics.getEmployeesHaveStandard()).as(NOT_EXPECTED_NUMBER + "employees with standard").isEqualTo(employees.getNumberOfEmployeesByInsuranceType(Insurance.InsuranceType.STANDARD));
        softly.assertThat(statistics.getEmployeesHaveBusiness()).as(NOT_EXPECTED_NUMBER + "employees with business").isEqualTo(employees.getNumberOfEmployeesByInsuranceType(Insurance.InsuranceType.BUSINESS));
        softly.assertThat(statistics.getEmployeesHaveVip()).as(NOT_EXPECTED_NUMBER + "employees with VIP").isEqualTo(employees.getNumberOfEmployeesByInsuranceType(Insurance.InsuranceType.VIP));
        softly.assertThat(statistics.getRelativesNumber()).as(NOT_EXPECTED_NUMBER + "relatives").isEqualTo(employees.getNumberOfRelatives());
        softly.assertThat(statistics.getRelativesHaveNone()).as(NOT_EXPECTED_NUMBER + "relatives without insurance").isEqualTo(employees.getNumberOfRelativesByInsuranceType(Insurance.InsuranceType.NONE));
        softly.assertThat(statistics.getRelativesHaveStandard()).as(NOT_EXPECTED_NUMBER + "relatives with standard").isEqualTo(employees.getNumberOfRelativesByInsuranceType(Insurance.InsuranceType.STANDARD));
        softly.assertThat(statistics.getRelativesHaveBusiness()).as(NOT_EXPECTED_NUMBER + "relatives with business").isEqualTo(employees.getNumberOfRelativesByInsuranceType(Insurance.InsuranceType.BUSINESS));
        softly.assertThat(statistics.getRelativesHaveVip()).as(NOT_EXPECTED_NUMBER + "relatives with VIP").isEqualTo(employees.getNumberOfRelativesByInsuranceType(Insurance.InsuranceType.VIP));
        softly.assertThat(statistics.getRelativesHaveVipChild()).as(NOT_EXPECTED_NUMBER + "relatives with child with").isEqualTo(employees.getNumberOfRelativesByInsuranceType(Insurance.InsuranceType.VIP_CHILD));
        softly.assertAll();
    }

    @Step("Change employee {employeeEmail} insurance type on {insuranceType}")
    public EmployeeList changeEmployeeInsuranceData(EmployeeList employees, String employeeEmail, Insurance.InsuranceType insuranceType) {
        Token token = loginClient.getEmployeeToken(new Auth(employeeEmail));
        Employee employee = employees.getEmployeeByEmail(employeeEmail);
        employee.setInsuranceType(insuranceType);
        employeeClient.putEmployee(employee, token.getToken());
        employees.getEmployeeByEmail(employeeEmail).setInsuranceType(insuranceType);
        return employees;
    }

    @Step("Restore employee data")
    public void restoreData() {
        for (Employee employee : EMPLOYEES) {
            Token token = loginClient.getEmployeeToken(new Auth(employee.getEmail()));
            employeeClient.putEmployee(employee, token.getToken());
        }
    }
}
