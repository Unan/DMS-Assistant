package com.dms.assistant.backend.clients;

import com.dms.assistant.backend.models.Employee;
import com.dms.assistant.backend.models.EmployeeList;
import com.dms.assistant.backend.models.InsuranceData;
import com.dms.assistant.backend.models.Statistics;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface AdminClient {

    @RequestLine("GET /api/v1/admin/employees")
    @Headers("x-token: {token}")
    EmployeeList getAllEmployees(@Param("token") String token);

    @RequestLine("GET /api/v1/admin/employees")
    @Headers("x-token: {token}")
    Response getAllEmployeesByUser(@Param("token") String token);

    @RequestLine("GET /api/v1/admin/employeesWithoutInsurance")
    @Headers("x-token: {token}")
    EmployeeList getEmployeesWithoutInsurance(@Param("token") String token);

    @RequestLine("GET /api/v1/admin/employeesWithoutInsurance")
    @Headers("x-token: {token}")
    Response getEmployeesWithoutInsuranceByUser(@Param("token") String token);

    @RequestLine("GET /api/v1/admin/statistics")
    @Headers("x-token: {token}")
    Statistics getStatistics(@Param("token") String token);

    @RequestLine("GET /api/v1/admin/statistics")
    @Headers("x-token: {token}")
    Response getStatisticsAsResponse(@Param("token") String token);

    @RequestLine("GET /api/v1/admin/getEmployee/{email}")
    @Headers("x-token: {token}")
    Employee getEmployeeByEmail(@Param("token") String token, @Param("email") String email);

    @RequestLine("GET /api/v1/admin/getEmployee/{email}")
    @Headers("x-token: {token}")
    Response getEmployeeByEmailInvalid(@Param("token") String token, @Param("email") String email);

    @RequestLine("PUT /api/v1/admin/setInsuranceData")
    @Headers({"Content-Type: application/json", "x-token: {token}"})
    Response putInsuranceData(InsuranceData insuranceData, @Param("token") String token);

    @RequestLine("GET /api/v1/admin/report")
    @Headers("x-token: {token}")
    Response makeReport(@Param("token") String token);

    @RequestLine("GET /api/v1/admin/notifyAllEmployees")
    @Headers("x-token: {token}")
    Response sendNotifications(@Param("token") String token);

    @RequestLine("GET /api/v1/admin/remindEmployeesWithoutInsurance")
    @Headers("x-token: {token}")
    Response sendReminds(@Param("token") String token);
}
