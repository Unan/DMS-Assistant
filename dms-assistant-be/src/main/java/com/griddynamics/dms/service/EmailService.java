package com.griddynamics.dms.service;

import com.griddynamics.dms.model.employee.Employee;

import java.util.List;

public interface EmailService {

    void notifyAllEmployees(List<Employee> employees);

    void remindEmployeesWithoutInsurance(List<Employee> employees);
}