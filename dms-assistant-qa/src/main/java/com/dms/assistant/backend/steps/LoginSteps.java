package com.dms.assistant.backend.steps;

import com.dms.assistant.backend.clients.LoginClient;
import com.dms.assistant.backend.models.Auth;
import com.dms.assistant.backend.models.Token;
import com.dms.assistant.backend.utils.ObjMapper;
import feign.Response;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class LoginSteps {

    @Autowired
    private LoginClient loginClient;

    @Autowired
    private ObjMapper objMapper;

    @Autowired
    private BaseSteps baseSteps;

    @Step("Trying sign in as {auth.login}")
    public Response signInAs(Auth auth) {
        return loginClient.getEmployeeTokenResponse(auth);
    }

    @Step("Check that response have body with token")
    public void checkTokenResponseBody(Response response) {
        try {
            Token token = objMapper.body2Object(response.body().toString(), Token.class);
            objMapper.checkValidation(token);
        } catch (Exception ignored) {
            assertThat(false).as("Incorrect Response").isTrue();
        }
    }

    @Step("Checking incorrect login")
    public void incorrectLoginCheck(Auth auth) {
        Response signInResponse = getInvalidEmployeeToken(auth);
        baseSteps.checkStatusCodeUnauthorized(signInResponse);
        baseSteps.checkResponseBodyIsNull(signInResponse);
    }

    @Step("Checking correct login")
    public void correctLoginCheck(Auth auth) {
        Response signInResponse = signInAs(auth);
        baseSteps.checkStatusCodeOK(signInResponse);
        checkTokenResponseBody(signInResponse);
    }

    @Step("Getting token by correct email: {auth.login}")
    public Token getEmployeeToken(Auth auth) {
        return loginClient.getEmployeeToken(auth);
    }

    @Step("Getting token by incorrect email: {auth.login}")
    public Response getInvalidEmployeeToken(Auth auth) {
        return loginClient.getInvalidEmployeeToken(auth);
    }
}
