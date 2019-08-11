package com.dms.assistant.backend.steps;

import com.dms.assistant.backend.clients.EmployeeClient;
import com.dms.assistant.backend.models.InsuranceData;
import com.dms.assistant.backend.models.Token;
import com.dms.assistant.backend.utils.ObjMapper;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeInsuranceDataSteps {

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private ObjMapper objMapper;

    @Step("Getting Insurance Pricing Information")
    public InsuranceData getInsuranceData(Token token) {
        InsuranceData insuranceData = employeeClient.getInsuranceData(token.getToken());
        objMapper.checkValidation(insuranceData);
        return insuranceData;
    }
}
