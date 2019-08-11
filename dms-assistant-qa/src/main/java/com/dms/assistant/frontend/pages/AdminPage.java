package com.dms.assistant.frontend.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.dms.assistant.backend.models.Insurance;
import com.dms.assistant.frontend.models.EmployeeFront;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static com.dms.assistant.frontend.constants.Constants.ADMIN_ROUTE;
import static com.dms.assistant.frontend.constants.Constants.DATE_FORMAT_ADMIN;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class AdminPage {

    private static final SelenideElement ADMIN_TITLE = $(By.className("article"));
    private static final SelenideElement PRICE_TITLE = $(By.className("prices-header"));
    private static final SelenideElement PRICE_INFO_TITLE = $(By.className("prices-info-header"));
    private static final SelenideElement LOGOUT_BUTTON = $x("//button[contains(@class, log-out-btn)]");
    private static final SelenideElement MAKE_REPORT_BUTTON = $x("//button[contains(@class, action-report-btn)]");
    private static final SelenideElement FILL_SURVEY_BUTTON = $x("//button[contains(@class, action-fill-survey-btn)]");
    private static final SelenideElement SAVE_PRICES_BUTTON = $x("//button[contains(@class, save-prices)]");
    private static final SelenideElement INSURANCE_SELECT = $(By.className("mat-select-value"));
    private static final SelenideElement ALL_INSURANCE_SELECT = findInsuranceSelect("All");
    private static final SelenideElement FILLED_INSURANCE_SELECT = findInsuranceSelect("Filled");
    private static final SelenideElement NOT_FILLED_INSURANCE_SELECT = findInsuranceSelect("Not filled");
    private static final SelenideElement STANDARD_PRICE_INPUT_FIELD = findPriceInputField('0');
    private static final SelenideElement BUSINESS_PRICE_INPUT_FIELD = findPriceInputField('1');
    private static final SelenideElement VIP_PRICE_INPUT_FIELD = findPriceInputField('2');
    private static final SelenideElement VIP_CHILD_PRICE_INPUT_FIELD = findPriceInputField('3');
    private static final SelenideElement STANDARD_PRICE_OUTPUT_FIELD = findPriceOutputField("Standard");
    private static final SelenideElement BUSINESS_PRICE_OUTPUT_FIELD = findPriceOutputField("Business");
    private static final SelenideElement VIP_PRICE_OUTPUT_FIELD = findPriceOutputField("V.I.P");
    private static final SelenideElement VIP_CHILD_PRICE_OUTPUT_FIELD = findPriceOutputField("V.I.P. child");
    private static final SelenideElement ALL_EMPLOYEES_TABLE = $x("//tbody");

    @Value("${HomePageURL}")
    private String HomePageURL;

    private static SelenideElement findInsuranceSelect(String type) {
        return $x("//span[contains(text(), '" + type + "')]");
    }

    private static SelenideElement findPriceInputField(Character number) {
        return $(By.id("mat-input-" + number));
    }

    private static SelenideElement findPriceOutputField(String type) {
        return $x("//p[contains(span, '" + type + "')]");
    }

    private AdminPage clickInsuranceSelect() {
        INSURANCE_SELECT.click();
        return page(AdminPage.class);
    }

    public AdminPage openPage() {
        open(HomePageURL + ADMIN_ROUTE);
        assertThat(WebDriverRunner.url()).as("Wrong URL").contains(ADMIN_ROUTE);
        return page(AdminPage.class);
    }

    public void checkAdminFieldsIsVisible() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ADMIN_TITLE.isDisplayed()).as("Admin title is not displaying").isTrue();
        softly.assertThat(PRICE_TITLE.isDisplayed()).as("Price title is not displaying").isTrue();
        softly.assertThat(PRICE_INFO_TITLE.isDisplayed()).as("Price info title is not displaying").isTrue();
        softly.assertThat(LOGOUT_BUTTON.isDisplayed()).as("Logout button is not displaying").isTrue();
        softly.assertThat(MAKE_REPORT_BUTTON.isDisplayed()).as("Make report button is not displaying").isTrue();
        softly.assertThat(FILL_SURVEY_BUTTON.isDisplayed()).as("Fill survey button is not displaying").isTrue();
        softly.assertThat(SAVE_PRICES_BUTTON.isDisplayed()).as("Save prices button is not displaying").isTrue();
        softly.assertThat(INSURANCE_SELECT.isDisplayed()).as("Insurance select is not displaying").isTrue();
        softly.assertThat(ALL_INSURANCE_SELECT.isDisplayed()).as("All insurance select is displaying").isFalse();
        softly.assertThat(FILLED_INSURANCE_SELECT.isDisplayed()).as("Filled insurance select is displaying").isFalse();
        softly.assertThat(NOT_FILLED_INSURANCE_SELECT.isDisplayed()).as("Not filled insurance select is displaying").isFalse();
        softly.assertThat(STANDARD_PRICE_INPUT_FIELD.isDisplayed()).as("Standard price input field is not displaying").isTrue();
        softly.assertThat(BUSINESS_PRICE_INPUT_FIELD.isDisplayed()).as("Business price input field is not displaying").isTrue();
        softly.assertThat(VIP_PRICE_INPUT_FIELD.isDisplayed()).as("VIP price input field is not displaying").isTrue();
        softly.assertThat(VIP_CHILD_PRICE_INPUT_FIELD.isDisplayed()).as("VIP Child price input field is not displaying").isTrue();
        softly.assertThat(STANDARD_PRICE_OUTPUT_FIELD.isDisplayed()).as("Standard price output field is not displaying").isTrue();
        softly.assertThat(BUSINESS_PRICE_OUTPUT_FIELD.isDisplayed()).as("Business price output field is not displaying").isTrue();
        softly.assertThat(VIP_PRICE_OUTPUT_FIELD.isDisplayed()).as("VIP price output field is not displaying").isTrue();
        softly.assertThat(VIP_CHILD_PRICE_OUTPUT_FIELD.isDisplayed()).as("VIP Child output input field is not displaying").isTrue();
        softly.assertThat(ALL_EMPLOYEES_TABLE.isDisplayed()).as("Table of all employees is not displaying").isTrue();
        softly.assertAll();
    }

    public void checkInsuranceSelectIsVisible() {
        SoftAssertions softly = new SoftAssertions();
        clickInsuranceSelect();
        softly.assertThat(ALL_INSURANCE_SELECT.isDisplayed()).as("All insurance select is not displaying").isTrue();
        softly.assertThat(FILLED_INSURANCE_SELECT.isDisplayed()).as("Filled insurance select is not displaying").isTrue();
        softly.assertThat(NOT_FILLED_INSURANCE_SELECT.isDisplayed()).as("Not filled insurance select is not displaying").isTrue();
        softly.assertAll();
    }

    public EmployeeFront getEmployeeById(Integer id) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT_ADMIN, Locale.ENGLISH);
            SelenideElement employeeElement = ALL_EMPLOYEES_TABLE.$$x("tr").get(id);
            EmployeeFront employeeFront = new EmployeeFront();
            employeeFront.setFullName(employeeElement.$x("td[contains(@class, 'mat-column-full-name')]").text());
            employeeFront.setEmail(employeeElement.$x("td[contains(@class, 'mat-column-email')]").text());
            employeeFront.setAddress(employeeElement.$x("td[contains(@class, 'mat-column-address')]").text());
            employeeFront.setPhoneNumber(employeeElement.$x("td[contains(@class, 'mat-column-phone-number')]").text());
            String amount = employeeElement.$x("td[contains(@class, 'mat-column-amount')]").text();
            if (amount.length() > 0) {
                employeeFront.setAmount(Integer.parseInt(amount));
            }
            else employeeFront.setAmount(0);
            employeeFront.setBirthDate(df.parse(employeeElement.$x("td[contains(@class, 'mat-column-birth-date')]").text()));
            return employeeFront;
        } catch (Exception e) {
            return new EmployeeFront();
        }
    }

    public AdminPage clickLogoutButton() {
        LOGOUT_BUTTON.click();
        return page(AdminPage.class);
    }

    public AdminPage clickMakeReportButton() {
        MAKE_REPORT_BUTTON.click();
        return page(AdminPage.class);
    }

    public AdminPage clickFillSurveyButton() {
        FILL_SURVEY_BUTTON.click();
        return page(AdminPage.class);
    }

    public AdminPage clickSavePricesButton() {
        SAVE_PRICES_BUTTON.click();
        return page(AdminPage.class);
    }

    public AdminPage selectEmployeeFilledType(FilledType filledType) {
        clickInsuranceSelect();
        switch (filledType) {
            case ALL:
                ALL_INSURANCE_SELECT.click();
                break;
            case FILLED:
                FILLED_INSURANCE_SELECT.click();
                break;
            case NOT_FILLED:
                NOT_FILLED_INSURANCE_SELECT.click();
                break;
        }
        return page(AdminPage.class);
    }

    public AdminPage clickEmployeeShowButtonById(Integer id) {
        ALL_EMPLOYEES_TABLE.$$x("//td[contains(@class, 'mat-column-actions')]").get(id).$x("button").click();
        return page(AdminPage.class);
    }

    public Integer getInsurancePrice(Insurance.InsuranceType insuranceType) {
        switch (insuranceType) {
            case STANDARD:
                return Integer.parseInt(StringUtils.remove(STANDARD_PRICE_OUTPUT_FIELD.text(), "Standard: "));
            case BUSINESS:
                return Integer.parseInt(StringUtils.remove(BUSINESS_PRICE_OUTPUT_FIELD.text(), "Business: "));
            case VIP:
                return Integer.parseInt(StringUtils.remove(VIP_PRICE_OUTPUT_FIELD.text(), "V.I.P.: "));
            case VIP_CHILD:
                return Integer.parseInt(StringUtils.remove(VIP_CHILD_PRICE_OUTPUT_FIELD.text(), "V.I.P. child: "));
        }
        return null;
    }

    public AdminPage setInsurancePrice(Insurance.InsuranceType insuranceType, Integer price) {
        switch (insuranceType) {
            case STANDARD:
                STANDARD_PRICE_INPUT_FIELD.setValue(price.toString());
                break;
            case BUSINESS:
                BUSINESS_PRICE_INPUT_FIELD.setValue(price.toString());
                break;
            case VIP:
                VIP_PRICE_INPUT_FIELD.setValue(price.toString());
                break;
            case VIP_CHILD:
                VIP_CHILD_PRICE_INPUT_FIELD.setValue(price.toString());
                break;
        }
        return page(AdminPage.class);
    }

    public enum FilledType {
        ALL,
        FILLED,
        NOT_FILLED
    }
}
