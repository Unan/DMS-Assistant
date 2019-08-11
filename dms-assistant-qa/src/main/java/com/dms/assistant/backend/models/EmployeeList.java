package com.dms.assistant.backend.models;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList extends ArrayList<Employee> {

    public EmployeeList getEmployeeByInsuranceType(Insurance.InsuranceType insuranceType) {
        EmployeeList employees = new EmployeeList();
        for (Employee employee : this) {
            if (employee.getInsurance().getInsuranceType().equals(insuranceType)) {
                employees.add(employee);
            }
        }
        return employees;
    }

    public Employee getEmployeeByEmail(String email) {
        for (Employee employee : this) {
            if (employee.getEmail().equals(email)) {
                return employee;
            }
        }
        return this.get(0);
    }

    public int getNumberOfRelativesByInsuranceType(Insurance.InsuranceType insuranceType) {
        int relativesNumber = 0;
        for (Employee employee : this) {
            relativesNumber += employee.getRelatives().getRelativeByInsuranceType(insuranceType).size();
        }
        return relativesNumber;
    }

    public int getNumberOfRelatives() {
        int relativesNumber = 0;
        for (Employee employee : this) {
            relativesNumber += employee.getRelatives().size();
        }
        return relativesNumber;
    }

    public int getNumberOfEmployeesByInsuranceType(Insurance.InsuranceType insuranceType) {
        return this.getEmployeeByInsuranceType(insuranceType).size();
    }

    public List<String> getEmployeesEmails() {
        List<String> emails = new ArrayList<String>();
        for (Employee employee : this) {
            emails.add(employee.getEmail());
        }
        return emails;
    }

    public EmployeeList getEmployeeByNotInsuranceType(Insurance.InsuranceType insuranceType) {
        EmployeeList employees = new EmployeeList();
        for (Employee employee : this) {
            if (!employee.getInsurance().getInsuranceType().equals(insuranceType)) {
                employees.add(employee);
            }
        }
        return employees;
    }
}
