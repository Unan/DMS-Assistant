package com.griddynamics.dms.controller;

import com.griddynamics.dms.config.security.admin.Admin;
import com.griddynamics.dms.model.data.InsuranceData;
import com.griddynamics.dms.model.data.Statistics;
import com.griddynamics.dms.model.employee.Employee;
import com.griddynamics.dms.service.AdminService;
import com.griddynamics.dms.service.EmailService;
import com.griddynamics.dms.service.InsuranceDataService;
import com.griddynamics.dms.service.SurveyService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;
    private final InsuranceDataService insuranceDataService;
    private final SurveyService surveyService;
    private final EmailService emailService;

    @Autowired
    public AdminController(AdminService adminService,
                           InsuranceDataService insuranceDataService,
                           SurveyService surveyService,
                           EmailService emailService
    ) {
        this.adminService = adminService;
        this.insuranceDataService = insuranceDataService;
        this.surveyService = surveyService;
        this.emailService = emailService;
    }

    @Admin
    @GetMapping("/notifyAllEmployees")
    public ResponseEntity<?> notifyAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>(adminService.getAllEmployees());
        if (employees.size() != 0) {
            emailService.notifyAllEmployees(employees);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Admin
    @GetMapping("/remindEmployeesWithoutInsurance")
    public ResponseEntity<?> notifyEmployeesWithoutInsurance() {
        ArrayList<Employee> employees = new ArrayList<>(adminService.findEmployeesWithoutInsurance());
        if (employees.size() != 0) {
            emailService.remindEmployeesWithoutInsurance(employees);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Admin
    @PutMapping("/setInsuranceData")
    public ResponseEntity<?> setData(@RequestBody InsuranceData insuranceData) {
        insuranceDataService.setData(insuranceData);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Admin
    @GetMapping("/statistics")
    public ResponseEntity<Statistics> getStatistics() {
        return ResponseEntity.ok(insuranceDataService.getStatistics());
    }

    @Admin
    @GetMapping("/getEmployee/{email}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String email) {
        Employee employee = surveyService.findEmployeeByEmail(email);
        if (employee == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.ok(employee);
    }

    @Admin
    @DeleteMapping("/deleteEmployee/{email}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String email) {
        Employee employee = surveyService.findEmployeeByEmail(email);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            surveyService.deleteEmployee(employee);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @Admin
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(adminService.getAllEmployees());
    }

    @Admin
    @GetMapping("/employeesWithoutInsurance")
    public ResponseEntity<List<Employee>> getEmployeesWithoutInsurance() {
        return ResponseEntity.ok(adminService.findEmployeesWithoutInsurance());
    }

    @Admin
    @GetMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        adminService.uploadEmployeesFromExcelFile(new XSSFWorkbook(file.getInputStream()));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Admin
    @GetMapping("/report")
    public ResponseEntity<InputStreamResource> excelEmployeeReport() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.add("Content-Disposition", "attachment; filename=Employee Insurance.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(adminService.makeReport()));
    }
}