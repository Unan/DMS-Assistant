package com.griddynamics.dms.service;

import com.griddynamics.dms.model.employee.Employee;

public interface SurveyService {

    Employee findEmployeeByEmail(String email);

    void saveEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}