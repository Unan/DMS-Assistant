package com.griddynamics.dms.service;

import com.griddynamics.dms.model.employee.Employee;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayInputStream;
import java.util.List;

public interface AdminService {

    ByteArrayInputStream makeReport();

    List<Employee> findEmployeesWithoutInsurance();

    List<Employee> getAllEmployees();

    void uploadEmployeesFromExcelFile(XSSFWorkbook workbook) throws Exception;
}