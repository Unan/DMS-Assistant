package com.dms.assistant.backend.steps;

import com.dms.assistant.backend.clients.AdminClient;
import com.dms.assistant.backend.models.InsuranceData;
import com.dms.assistant.backend.models.Token;
import feign.Response;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminInsuranceDataSteps {

    @Autowired
    private AdminClient adminClient;

    @Step("Putting new insuranceData")
    public Response putInsuranceData(InsuranceData putInsuranceData, Token token) {
        return adminClient.putInsuranceData(putInsuranceData, token.getToken());
    }
}
