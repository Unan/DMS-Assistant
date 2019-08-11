package com.dms.assistant.backend.steps;

import com.dms.assistant.backend.clients.EmployeeClient;
import com.dms.assistant.backend.clients.LoginClient;
import com.dms.assistant.backend.models.Employee;
import com.dms.assistant.backend.models.InsuranceData;
import com.dms.assistant.backend.models.Token;
import feign.Response;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;

import static com.dms.assistant.backend.constants.Constants.CORRECT_LOGIN_3;
import static com.dms.assistant.backend.constants.Constants.EMPLOYEE_3;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class BaseSteps {

    @Autowired
    private LoginClient loginClient;

    @Autowired
    private EmployeeClient employeeClient;

    @Step("Check that response has status code 200 OK")
    public void checkStatusCodeOK(Response response) {
        assertThat(response.status()).as("Encountered unexpected status").isEqualTo(HttpURLConnection.HTTP_OK);
    }

    @Step("Check that response has status code 401 UNAUTHORIZED")
    public void checkStatusCodeUnauthorized(Response response) {
        assertThat(response.status()).as("Encountered unexpected status").isEqualTo(HttpURLConnection.HTTP_UNAUTHORIZED);
    }

    @Step("Checking that the response has status code 403 FORBIDDEN")
    public void checkStatusCodeForbidden(Response response) {
        Assertions.assertThat(response.status()).as("Encountered unexpected status").isEqualTo(HttpURLConnection.HTTP_FORBIDDEN);
    }

    @Step("Check that response has status code 404 NOT FOUND")
    public void checkStatusCodeNotFound(Response response) {
        assertThat(response.status()).as("Encountered unexpected status").isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
    }

    @Step("Check that response body is null")
    public void checkResponseBodyIsNull(Response response) {
        assertThat(response.body()).isNull();
    }

    @Step("Checking insuranceData")
    public void checkInsuranceData(InsuranceData expectedInsuranceData, InsuranceData actualInsuranceData) {
        Assertions.assertThat(actualInsuranceData).as("Response returns incorrect data about insuranceData").isEqualTo(expectedInsuranceData);
    }

    public void saveInitialThirdEmployee() {
        Token token = loginClient.getEmployeeToken(CORRECT_LOGIN_3);
        Employee employee = employeeClient.getEmployee(token.getToken());
        employeeClient.putEmployee(employee.copy(EMPLOYEE_3), token.getToken());
    }
}
