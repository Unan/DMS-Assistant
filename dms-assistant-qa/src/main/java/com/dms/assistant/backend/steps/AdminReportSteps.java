package com.dms.assistant.backend.steps;

import com.dms.assistant.backend.clients.AdminClient;
import com.dms.assistant.backend.models.Employee;
import com.dms.assistant.backend.models.EmployeeList;
import com.dms.assistant.backend.models.Relative;
import com.dms.assistant.backend.models.Token;
import io.qameta.allure.Step;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.dms.assistant.backend.constants.Constants.REPORT_DATE_FORMAT;

@Component
public class AdminReportSteps {

    @Autowired
    private AdminClient adminClient;

    @Autowired
    private AdminEmployeeAvailabilitySteps adminEmployeeAvailabilitySteps;

    @Autowired
    private BaseSteps baseSteps;

    @Step("Getting final report by USER")
    public void getMakeReportByUser(Token token) {
        baseSteps.checkStatusCodeForbidden(adminClient.makeReport(token.getToken()));
    }

    @Step("Getting final report")
    public XSSFWorkbook makeReport(Token token) {
        try {
            return new XSSFWorkbook(adminClient.makeReport(token.getToken()).body().asInputStream());
        } catch (Exception e) {
            Assertions.assertThat(false).as("Unable to convert response body to XLSX file").isTrue();
            return null;
        }
    }

    @Step("Checking employee: {employee.email}")
    public void checkEmployee(Employee employee, Row row) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(String.valueOf(row.getCell(1))).as("Invalid fullName").isEqualTo(employee.getFullName());
        softly.assertThat(String.valueOf(row.getCell(2))).as("Invalid birthDate").isEqualTo(REPORT_DATE_FORMAT.format(employee.getBirthDate()));
        softly.assertThat(String.valueOf(row.getCell(3))).as("Invalid address").isEqualTo(employee.getAddress());
        softly.assertThat(String.valueOf(row.getCell(4))).as("Invalid role").isEqualTo(employee.getRole());
        softly.assertThat(String.valueOf(row.getCell(5))).as("Invalid phoneNumber").isEqualTo(employee.getPhoneNumber());
        softly.assertThat(String.valueOf(row.getCell(6))).as("Invalid insuranceType").isEqualTo(employee.getInsurance().takeInsuranceTypeInRussian());
        softly.assertThat(Integer.valueOf(String.valueOf(row.getCell(7)))).as("Invalid amount").isEqualTo(employee.getInsurance().getAmount());
        softly.assertThat(Double.valueOf(String.valueOf(row.getCell(8)))).as("Invalid coefficient").isEqualTo(employee.getInsurance().getCoefficient());
        softly.assertThat(Double.valueOf(String.valueOf(row.getCell(9)))).as("Invalid final amount").isEqualTo(employee.getInsurance().takeFinalAmount());
        softly.assertAll();
    }

    @Step("Checking relative: {relative.fullName}")
    public void checkRelative(Relative relative, Row row) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(String.valueOf(row.getCell(1))).as("Invalid fullName").isEqualTo(relative.getFullName());
        softly.assertThat(String.valueOf(row.getCell(2))).as("Invalid birthDate").isEqualTo(REPORT_DATE_FORMAT.format(relative.getBirthDate()));
        softly.assertThat(String.valueOf(row.getCell(3))).as("Invalid address").isEqualTo(relative.getAddress());
        softly.assertThat(String.valueOf(row.getCell(4))).as("Invalid role").isEqualTo("Родственник");
        softly.assertThat(String.valueOf(row.getCell(5))).as("Invalid phoneNumber").isEqualTo(relative.getPhoneNumber());
        softly.assertThat(String.valueOf(row.getCell(6))).as("Invalid insuranceType").isEqualTo(relative.getInsurance().takeInsuranceTypeInRussian());
        softly.assertThat(Integer.valueOf(String.valueOf(row.getCell(7)))).as("Invalid amount").isEqualTo(relative.getInsurance().getAmount());
        softly.assertThat(Double.valueOf(String.valueOf(row.getCell(8)))).as("Invalid coefficient").isEqualTo(relative.getInsurance().getCoefficient());
        softly.assertThat(Double.valueOf(String.valueOf(row.getCell(9)))).as("Invalid final amount").isEqualTo(relative.getInsurance().takeFinalAmount());
        softly.assertAll();
    }

    @Step("Checking file title")
    public void checkFileTitle(Row row) {
        Assertions.assertThat(String.valueOf(row.getCell(0))).isEqualTo("Список застрахованных лиц");
    }

    @Step("Checking file cell names")
    public void checkFileCellNames(Row row) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(String.valueOf(row.getCell(0))).isEqualTo("№ п/п");
        softly.assertThat(String.valueOf(row.getCell(1))).isEqualTo("ФИО");
        softly.assertThat(String.valueOf(row.getCell(2))).isEqualTo("Дата рождения");
        softly.assertThat(String.valueOf(row.getCell(3))).isEqualTo("Адрес проживания");
        softly.assertThat(String.valueOf(row.getCell(4))).isEqualTo("Должность");
        softly.assertThat(String.valueOf(row.getCell(5))).isEqualTo("Телефон");
        softly.assertThat(String.valueOf(row.getCell(6))).isEqualTo("№ Прогр.");
        softly.assertThat(String.valueOf(row.getCell(7))).isEqualTo("Страховая премия по прогр., р.");
        softly.assertThat(String.valueOf(row.getCell(8))).isEqualTo("Коэффициент");
        softly.assertThat(String.valueOf(row.getCell(9))).isEqualTo("Итоговая страховая премия, р.");
        softly.assertAll();
    }

    @Step("Checking file employees")
    public void checkFileEmployees(XSSFSheet sheet, Token token) {
        EmployeeList employees = adminEmployeeAvailabilitySteps.getAllEmployees(token);
        int userRowNumber = 3;
        for (Employee employee : employees) {
            checkEmployee(employee, sheet.getRow(userRowNumber));
            userRowNumber++;
            for (Relative relative : employee.getRelatives()) {
                checkRelative(relative, sheet.getRow(userRowNumber));
                userRowNumber++;
            }
        }
    }

    @Step("Checking make report service")
    public void checkMakeReport(Token token) {
        XSSFWorkbook workbook = makeReport(token);
        XSSFSheet sheet = workbook.getSheet("Employee insurance");
        checkFileTitle(sheet.getRow(1));
        checkFileCellNames(sheet.getRow(2));
        checkFileEmployees(sheet, token);
    }
}
