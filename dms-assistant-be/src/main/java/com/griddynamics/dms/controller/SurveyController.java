package com.griddynamics.dms.controller;

import com.griddynamics.dms.config.security.user.User;
import com.griddynamics.dms.model.employee.Employee;
import com.griddynamics.dms.model.data.InsuranceData;
import com.griddynamics.dms.service.InsuranceDataService;
import com.griddynamics.dms.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/user")
public class SurveyController {

    private final SurveyService surveyService;
    private final InsuranceDataService insuranceDataService;

    @Autowired
    public SurveyController(SurveyService surveyService,
                            InsuranceDataService insuranceDataService
    ) {
        this.surveyService = surveyService;
        this.insuranceDataService = insuranceDataService;
    }

    @User
    @GetMapping("/survey")
    public ResponseEntity<Employee> survey(@RequestAttribute Employee employee) {
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @User
    @GetMapping("/getInsuranceData")
    public ResponseEntity<InsuranceData> getData() {
        if (insuranceDataService.getData() != null)
            return ResponseEntity.ok(insuranceDataService.getData());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @User
    @PutMapping("/save")
    public ResponseEntity<Employee> save(@RequestBody Employee receivedEmployee,
                                         @RequestAttribute Employee employee
    ) {
        if (
                !receivedEmployee.getEmail().equals(employee.getEmail()) ||
                !receivedEmployee.getFullName().equals(employee.getFullName()) ||
                !receivedEmployee.getBirthDate().equals(employee.getBirthDate()) ||
                !receivedEmployee.getAddress().equals(employee.getAddress()) ||
                !receivedEmployee.getHireDate().equals(employee.getHireDate()) ||
                !receivedEmployee.getPhoneNumber().equals(employee.getPhoneNumber()) ||
                !receivedEmployee.getInternalRole().equals(employee.getInternalRole()) ||
                !receivedEmployee.getRole().equals(employee.getRole())
        ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        surveyService.saveEmployee(receivedEmployee);
        return ResponseEntity.ok(receivedEmployee);
    }
}