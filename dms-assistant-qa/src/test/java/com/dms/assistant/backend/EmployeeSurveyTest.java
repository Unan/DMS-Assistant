package com.dms.assistant.backend;

import com.dms.assistant.ConfigDMS;
import com.dms.assistant.backend.models.Employee;
import com.dms.assistant.backend.models.Insurance;
import com.dms.assistant.backend.models.Token;
import com.dms.assistant.backend.steps.BaseSteps;
import com.dms.assistant.backend.steps.EmployeeSurveySteps;
import com.dms.assistant.backend.steps.LoginSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.dms.assistant.backend.constants.Constants.*;

@ContextConfiguration(classes = ConfigDMS.class)
@Epic("Services")
@Feature("Survey API tests")
@Story("[DMS-19] Implement Survey service")
public class EmployeeSurveyTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private EmployeeSurveySteps employeeSurveySteps;

    @Autowired
    private LoginSteps loginSteps;

    @Autowired
    private BaseSteps baseSteps;

    @Test(description = "Getting information about employee by correct email", groups = {"Services", "Smoke"})
    public void getEmployeeTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee employee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEmployee(EMPLOYEE_3, employee);
    }

    @Test(description = "Editing on new correct employee fullName", groups = {"Services"})
    public void editOnCorrectEmployeeFullNameTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.editEmployeeFullName(putEmployee, FULL_NAME_1);
        employeeSurveySteps.checkPutEmployeeIncorrect(putEmployee, token);
    }

    @Test(description = "Editing on new correct employee address", groups = {"Services"})
    public void editOnCorrectEmployeeAddressTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.editEmployeeAddress(putEmployee, ADDRESS_1);
        employeeSurveySteps.checkPutEmployeeIncorrect(putEmployee, token);
    }

    @Test(description = "Editing on new correct employee birthDate", groups = {"Services"})
    public void editOnCorrectEmployeeBirthDateTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.editEmployeeBirthDate(putEmployee, BIRTH_DATE_1);
        employeeSurveySteps.checkPutEmployeeIncorrect(putEmployee, token);
    }

    @Test(description = "Editing on new correct employee hireDate", groups = {"Services"})
    public void editOnCorrectEmployeeHireDateTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.editEmployeeHireDate(putEmployee, HIRE_DATE_1);
        employeeSurveySteps.checkPutEmployeeIncorrect(putEmployee, token);
    }

    @Test(description = "Editing on new correct employee phoneNumber", groups = {"Services"})
    public void editOnCorrectEmployeePhoneNumberTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.editEmployeePhoneNumber(putEmployee, PHONE_NUMBER_1);
        employeeSurveySteps.checkPutEmployeeIncorrect(putEmployee, token);
    }

    @Test(description = "Editing on new correct employee role", groups = {"Services"})
    public void editOnCorrectEmployeeRoleTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.editEmployeeRole(putEmployee, ROLE_HR);
        employeeSurveySteps.checkPutEmployeeIncorrect(putEmployee, token);
    }

    @Test(description = "Editing on new correct employee insuranceType", groups = {"Services"})
    public void editOnCorrectEmployeeInsuranceTypeTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        List<Insurance.InsuranceType> insuranceTypes = (EnumUtils.getEnumList(Insurance.InsuranceType.class));
        for (Insurance.InsuranceType insuranceType : insuranceTypes.subList(0, insuranceTypes.size() - 1)) {
            employeeSurveySteps.checkEditEmployeeInsuranceType(putEmployee, insuranceType, token);
        }
    }

    @Test(description = "Editing on INVALID employee insuranceType", groups = {"Services"})
    public void editOnIncorrectEmployeeInsuranceTypeTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.editEmployeeInsuranceType(putEmployee, Insurance.InsuranceType.INVALID);
        employeeSurveySteps.checkPutEmployeeIncorrect(putEmployee, token);
    }

    @Test(description = "Editing on new correct relative fullName", groups = {"Services"})
    public void editOnCorrectRelativeFullNameTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.editRelativeFullName(putEmployee, REL_0, FULL_NAME_2);
        employeeSurveySteps.checkPutEmployeeCorrect(putEmployee, token);
    }

    @Test(description = "Editing on new correct relative birthDate", groups = {"Services"})
    public void editOnCorrectRelativeBirthDateTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.editRelativeBirthDate(putEmployee, REL_0, BIRTH_DATE_2);
        employeeSurveySteps.checkPutEmployeeCorrect(putEmployee, token);
    }

    @Test(description = "Editing on new correct relative address", groups = {"Services"})
    public void editOnCorrectRelativeAddressTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.editRelativeAddress(putEmployee, REL_0, ADDRESS_2);
        employeeSurveySteps.checkPutEmployeeCorrect(putEmployee, token);
    }

    @Test(description = "Editing on new correct relative phoneNumber", groups = {"Services"})
    public void editOnCorrectRelativePhoneNumberTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.editRelativePhoneNumber(putEmployee, REL_0, PHONE_NUMBER_2);
        employeeSurveySteps.checkPutEmployeeCorrect(putEmployee, token);
    }

    @Test(description = "Editing on new correct relative insuranceType", groups = {"Services"})
    public void editOnCorrectRelativeInsuranceTypeTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        List<Insurance.InsuranceType> insuranceTypes = (EnumUtils.getEnumList(Insurance.InsuranceType.class));
        for (Insurance.InsuranceType insuranceType : insuranceTypes.subList(0, insuranceTypes.size() - 1)) {
            employeeSurveySteps.checkEditRelativeInsuranceType(putEmployee, REL_0, insuranceType, token);
        }
    }

    @Test(description = "Editing on INVALID relative insuranceType", groups = {"Services"})
    public void editOnIncorrectRelativeInsuranceType() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_1);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.editRelativeInsuranceType(putEmployee, REL_0, Insurance.InsuranceType.INVALID);
        employeeSurveySteps.checkPutEmployeeIncorrect(putEmployee, token);
    }

    @Test(description = "Adding new correct relative", groups = {"Services"})
    public void putCorrectRelativeTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_2);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.addEmployeeRelative(putEmployee, RELATIVE_1);
        employeeSurveySteps.checkPutEmployeeCorrect(putEmployee, token);
    }

    @Test(description = "Deleting relative", groups = {"Services"})
    public void deleteRelativeTest() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_5);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.deleteRelative(putEmployee, REL_0);
        employeeSurveySteps.checkPutEmployeeCorrect(putEmployee, token);
    }

    @Test(description = "Editing on null relative fullName", groups = {"Services", "Negative"})
    public void editOnNullRelativeFullName() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEditIncorrectRelativeFullName(putEmployee, REL_0, null, token);
    }

    @Test(description = "Editing on null relative birthDate", groups = {"Services", "Negative"})
    public void editOnNullRelativeBirthDate() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEditIncorrectRelativeBirthDate(putEmployee, REL_0, null, token);
    }

    @Test(description = "Editing on null relative address", groups = {"Services", "Negative"})
    public void editOnNullRelativeAddress() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEditIncorrectRelativeAddress(putEmployee, REL_0, null, token);
    }

    @Test(description = "Editing on null relative phoneNumber", groups = {"Services", "Negative"})
    public void editOnNullRelativePhoneNumber() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEditIncorrectRelativeAddress(putEmployee, REL_0, null, token);
    }

    @Test(description = "Editing on empty relative fullName", groups = {"Services", "Negative"})
    public void editOnEmptyRelativeFullName() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEditIncorrectRelativeFullName(putEmployee, REL_0, EMPTY, token);
    }

    @Test(description = "Editing on empty relative address", groups = {"Services", "Negative"})
    public void editOnEmptyNullRelativeAddress() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEditIncorrectRelativeAddress(putEmployee, REL_0, EMPTY, token);
    }

    @Test(description = "Editing on empty relative phoneNumber", groups = {"Services", "Negative"})
    public void editOnEmptyRelativePhoneNumber() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEditIncorrectRelativePhoneNumber(putEmployee, REL_0, EMPTY, token);
    }

    @Test(description = "Editing on invalid relative phoneNumber with less than 10 digits", groups = {"Services", "Negative"})
    public void edinOnInvalidRelativePhoneNumber1() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEditIncorrectRelativePhoneNumber(putEmployee, REL_0, INVALID_PHONE_NUMBER_LESS_10, token);
    }

    @Test(description = "Editing on invalid relative phoneNumber with more than 10 digits", groups = {"Services", "Negative"})
    public void edinOnInvalidRelativePhoneNumber2() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEditIncorrectRelativePhoneNumber(putEmployee, REL_0, INVALID_PHONE_NUMBER_MORE_10, token);
    }

    @Test(description = "Editing on invalid relative phoneNumber with letters", groups = {"Services", "Negative"})
    public void edinOnInvalidRelativePhoneNumber3() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEditIncorrectRelativePhoneNumber(putEmployee, REL_0, INVALID_PHONE_NUMBER_WITH_LETTERS, token);
    }

    @Test(description = "Editing on invalid relative phoneNumber beginning from zero", groups = {"Services", "Negative"})
    public void edinOnInvalidRelativePhoneNumber4() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEditIncorrectRelativePhoneNumber(putEmployee, REL_0, INVALID_PHONE_NUMBER_ZERO_START, token);
    }

    @Test(description = "Editing on invalid relative phoneNumber with spaces (case 1)", groups = {"Services", "Negative"})
    public void edinOnInvalidRelativePhoneNumber5() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEditIncorrectRelativePhoneNumber(putEmployee, REL_0, INVALID_PHONE_NUMBER_WITH_SPACE_1, token);
    }

    @Test(description = "Editing on invalid relative phoneNumber with spaces (case 2)", groups = {"Services", "Negative"})
    public void edinOnInvalidRelativePhoneNumber6() {
        Token token = loginSteps.getEmployeeToken(CORRECT_LOGIN_3);
        Employee putEmployee = employeeSurveySteps.getEmployee(token.getToken());
        employeeSurveySteps.checkEditIncorrectRelativePhoneNumber(putEmployee, REL_0, INVALID_PHONE_NUMBER_WITH_SPACE_2, token);
    }

    @AfterMethod(groups = {"Negative"})
    public void saveInitialData() {
        baseSteps.saveInitialThirdEmployee();
    }
}
