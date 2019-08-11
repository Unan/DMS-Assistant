package com.dms.assistant.backend.clients;

import com.dms.assistant.backend.models.Auth;
import com.dms.assistant.backend.models.Token;
import feign.Headers;
import feign.RequestLine;
import feign.Response;

public interface LoginClient {

    @RequestLine("POST /api/v1/test/backdoor")
    @Headers("Content-Type: application/json")
    Token getEmployeeToken(Auth auth);

    @RequestLine("POST /api/v1/test/backdoor")
    @Headers("Content-Type: application/json")
    Response getEmployeeTokenResponse(Auth auth);

    @RequestLine("POST /api/v1/test/backdoor")
    @Headers("Content-Type: application/json")
    Response getInvalidEmployeeToken(Auth auth);
}
