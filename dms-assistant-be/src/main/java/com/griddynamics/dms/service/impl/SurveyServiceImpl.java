package com.griddynamics.dms.service.impl;

import com.griddynamics.dms.model.employee.Employee;
import com.griddynamics.dms.model.employee.Insurance;
import com.griddynamics.dms.model.employee.Relative;
import com.griddynamics.dms.repository.employee.EmployeeRepository;
import com.griddynamics.dms.repository.employee.InsuranceRepository;
import com.griddynamics.dms.repository.employee.RelativeRepository;
import com.griddynamics.dms.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final EmployeeRepository employeeRepository;
    private final InsuranceRepository insuranceRepository;
    private final RelativeRepository relativeRepository;

    @Autowired
    public SurveyServiceImpl(EmployeeRepository employeeRepository,
                             InsuranceRepository insuranceRepository,
                             RelativeRepository relativeRepository

    ) {
        this.employeeRepository = employeeRepository;
        this.insuranceRepository = insuranceRepository;
        this.relativeRepository = relativeRepository;
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public void saveEmployee(Employee employee) {

        ArrayList<Insurance> insurances = new ArrayList<>();
        ArrayList<Relative> relatives = new ArrayList<>(employee.getRelatives());

        for (Relative relative : relatives) {
            insurances.add(relative.getInsurance());
        }
        insurances.add(employee.getInsurance());

        insuranceRepository.saveAll(insurances);
        relativeRepository.saveAll(relatives);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {

        List<Insurance> insurances = new ArrayList<>();
        List<Relative> relatives = new ArrayList<>(employee.getRelatives());

        for (Relative relative : relatives) {
            insurances.add(relative.getInsurance());
        }
        insurances.add(employee.getInsurance());

        employeeRepository.delete(employee);
        relativeRepository.deleteAll(relatives);
        insuranceRepository.deleteAll(insurances);
    }
}