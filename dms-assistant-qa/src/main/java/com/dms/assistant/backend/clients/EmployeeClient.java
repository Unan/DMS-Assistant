package com.dms.assistant.backend.clients;

import com.dms.assistant.backend.models.Employee;
import com.dms.assistant.backend.models.InsuranceData;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface EmployeeClient {

    @RequestLine("GET /api/v1/user/survey")
    @Headers("x-token: {token}")
    Employee getEmployee(@Param("token") String token);

    @RequestLine("GET /api/v1/user/survey")
    @Headers("x-token: {token}")
    Response getEmployeeInvalid(@Param("token") String token);

    @RequestLine("GET /api/v1/user/getInsuranceData")
    @Headers("x-token: {token}")
    InsuranceData getInsuranceData(@Param("token") String token);

    @RequestLine("PUT /api/v1/user/save")
    @Headers({"Content-Type: application/json", "x-token: {token}"})
    Employee putEmployee(Employee employee, @Param("token") String token);

    @RequestLine("PUT /api/v1/user/save")
    @Headers({"Content-Type: application/json", "x-token: {token}"})
    Response putEmployeeIncorrect(Employee employee, @Param("token") String token);
}
