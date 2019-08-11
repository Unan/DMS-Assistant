package com.griddynamics.dms.init;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.griddynamics.dms.model.data.InsuranceData;
import com.griddynamics.dms.model.employee.Employee;
import com.griddynamics.dms.model.employee.Insurance;
import com.griddynamics.dms.model.employee.Relative;
import com.griddynamics.dms.repository.employee.EmployeeRepository;
import com.griddynamics.dms.repository.employee.InsuranceRepository;
import com.griddynamics.dms.repository.employee.RelativeRepository;
import com.griddynamics.dms.repository.insuranceData.InsuranceDataRepository;
import com.griddynamics.dms.repository.insuranceData.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Profile("qa")
@Component
public class DataInit {

    private static final String EMPLOYEES = "classpath:json_data/employees.json";
    private static final String INSURANCE_DATA = "classpath:json_data/insurance_data.json";

    private final EmployeeRepository employeeRepository;
    private final InsuranceRepository insuranceRepository;
    private final RelativeRepository relativeRepository;
    private final ResourceLoader resourceLoader;
    private final InsuranceDataRepository insuranceDataRepository;
    private final RateRepository rateRepository;

    @Autowired
    public DataInit(EmployeeRepository employeeRepository,
                    InsuranceRepository insuranceRepository,
                    RelativeRepository relativeRepository,
                    ResourceLoader resourceLoader,
                    InsuranceDataRepository insuranceDataRepository, RateRepository rateRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.insuranceRepository = insuranceRepository;
        this.relativeRepository = relativeRepository;
        this.resourceLoader = resourceLoader;
        this.insuranceDataRepository = insuranceDataRepository;
        this.rateRepository = rateRepository;
    }

    @PostConstruct
    public void index() throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                resourceLoader.getResource(EMPLOYEES).getInputStream()))) {

            String content = reader.lines().collect(Collectors.joining());

            ArrayList<Employee> employees = new ObjectMapper().readValue(content,
                    new TypeReference<ArrayList<Employee>>() {
                    });

            ArrayList<Insurance> insurances = new ArrayList<>();
            ArrayList<Relative> relatives = new ArrayList<>();

            for (Employee employee : employees) {
                for (Relative relative : employee.getRelatives()) {
                    insurances.add(relative.getInsurance());
                }
                relatives.addAll(employee.getRelatives());
                insurances.add(employee.getInsurance());
            }
            insuranceRepository.saveAll(insurances);
            relativeRepository.saveAll(relatives);
            employeeRepository.saveAll(employees);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                resourceLoader.getResource(INSURANCE_DATA).getInputStream()))) {

            String content = reader.lines().collect(Collectors.joining());
            InsuranceData insuranceData = new ObjectMapper().readValue(content,
                    new TypeReference<InsuranceData>() {
                    });

            rateRepository.saveAll(insuranceData.getRates());
            insuranceDataRepository.save(insuranceData);
        }
    }
}