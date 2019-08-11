package com.dms.assistant.frontend.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.dms.assistant.backend.models.Insurance;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.readonly;
import static com.codeborne.selenide.Selenide.*;
import static com.dms.assistant.frontend.constants.Constants.DATE_FORMAT_SURVEY;
import static com.dms.assistant.frontend.constants.Constants.SURVEY_ROUTE;
import static com.dms.assistant.frontend.constants.XPaths.*;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class SurveyPage {

    private static final SelenideElement FORM_HEADER = $(By.xpath("//h1[.='Insurance form']"));
    private static final SelenideElement EMPLOYEE_LABEL = $(By.xpath("//span[.='Employee']"));
    private static final SelenideElement EMPLOYEE_INSURANCE_TYPE = $(By.xpath("//div[contains(@class, 'mat-select-value')]"));
    private static final SelenideElement ADD_PERSON_LABEL = $(By.xpath("//div[.='Add person']"));
    private static final SelenideElement ADD_PERSON_BUTTON = $(By.xpath("//button[contains(@class, 'button-add')]"));
    private static final SelenideElement SAVE_BUTTON = $(By.xpath("//button[contains(@class, 'button-save')]"));
    private static final ElementsCollection RELATIVE_FORMS = $$(By.xpath("//div[contains(@class, 'ng-star-inserted')][contains(@class, 'form-component')]"));
    private static final SelenideElement FIRST_RELATIVE_FAMILY_NAME_FIELD = RELATIVE_FORMS.first().$(By.xpath(RELATIVE_FAMILY_NAME_XPATH));
    private static final SelenideElement FIRST_RELATIVE_NAME_FIELD = RELATIVE_FORMS.first().$(By.xpath(RELATIVE_NAME_XPATH));
    private static final SelenideElement FIRST_RELATIVE_SECOND_NAME_FIELD = RELATIVE_FORMS.first().$(By.xpath(RELATIVE_SECOND_NAME_XPATH));
    private static final SelenideElement FIRST_RELATIVE_BIRTH_DATE = RELATIVE_FORMS.first().$(By.xpath(RELATIVE_BIRTH_DATE_CHOOSER_XPATH));
    private static final SelenideElement FIRST_RELATIVE_PHONE_NUMBER_FIELD = RELATIVE_FORMS.first().$(By.xpath(RELATIVE_PHONE_NUMBER_XPATH));
    private static final SelenideElement FIRST_RELATIVE_ADDRESS_FIELD = RELATIVE_FORMS.first().$(By.xpath(RELATIVE_ADDRESS_XPATH));
    private static final SelenideElement FIRST_RELATIVE_INSURANCE_TYPE_CHOOSER = RELATIVE_FORMS.first().$(By.xpath(RELATIVE_INSURANCE_TYPE_CHOOSER));
    private static final SelenideElement FIRST_RELATIVE_REMOVE_PERSON_BUTTON = RELATIVE_FORMS.first().$(By.xpath(RELATIVE_REMOVE_BUTTON_XPATH));
    private static final SelenideElement DATE_PICKER = $(By.xpath(DATE_PICKER_XPATH));
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_SURVEY);
    private static int locatorId = 1;
    private static final SelenideElement EMPLOYEE_FAMILY_NAME_FIELD = $(By.id(getEmployeeFieldsId()));
    private static final SelenideElement EMPLOYEE_NAME_FIELD = $(By.id(getEmployeeFieldsId()));
    private static final SelenideElement EMPLOYEE_SECOND_NAME_FIELD = $(By.id(getEmployeeFieldsId()));
    private static final SelenideElement EMPLOYEE_BIRTH_DATE_FIELD = $(By.id(getEmployeeFieldsId()));
    private static final SelenideElement EMPLOYEE_PHONE_NUMBER_FIELD = $(By.id(getEmployeeFieldsId()));
    private static final SelenideElement EMPLOYEE_ADDRESS_FIELD = $(By.id(getEmployeeFieldsId()));
    private static final SelenideElement EMPLOYEE_POSITION = $(By.id(getEmployeeFieldsId()));
    private static final SelenideElement EMPLOYMENT_DATE = $(By.id(getEmployeeFieldsId()));
    @Value("${HomePageURL}")
    public String HomePageURL;

    private static String getEmployeeFieldsId() {
        return String.format("mat-input-%s", locatorId++);
    }

    private String getFamilyNameFromFullName(String fullName) {
        return fullName.split(StringUtils.SPACE)[0];
    }

    private String getNameFromFullName(String fullName) {
        return fullName.split(StringUtils.SPACE)[1];
    }

    private String getSecondNameFromFullName(String fullName) {
        String[] splittedFullName = fullName.split(StringUtils.SPACE);
        if (splittedFullName.length == 3) {
            return splittedFullName[2];
        } else {
            return StringUtils.EMPTY;
        }
    }

    public SurveyPage openPage() {
        open(HomePageURL + SURVEY_ROUTE);
        assertThat(WebDriverRunner.url()).as("Wrong URL").contains(SURVEY_ROUTE);
        return page(SurveyPage.class);
    }

    public SurveyPage clickAddPersonButton() {
        ADD_PERSON_BUTTON.click();
        return page(SurveyPage.class);
    }

    public SurveyPage addAdditionalPersonForms(int numbers) {
        for (int index = 0; index < numbers; index++) {
            clickAddPersonButton();
        }
        return page(SurveyPage.class);
    }

    public SurveyPage clickSaveButton() {
        SAVE_BUTTON.click();
        return page(SurveyPage.class);
    }

    public SurveyPage clickFirstRelativeBirthdayChooser() {
        FIRST_RELATIVE_BIRTH_DATE.click();
        return page(SurveyPage.class);
    }

    public SurveyPage clickFirstRelativeInsuranceTypeChooser() {
        FIRST_RELATIVE_INSURANCE_TYPE_CHOOSER.click();
        return page(SurveyPage.class);
    }

    public SurveyPage fillFullName(String fullName) {
        fillRelativeFamilyNameField(getFamilyNameFromFullName(fullName));
        fillRelativeNameField(getNameFromFullName(fullName));
        fillRelativeSecondNameField(getSecondNameFromFullName(fullName));
        return page(SurveyPage.class);
    }

    public SurveyPage fillRelativeFamilyNameField(String value) {
        FIRST_RELATIVE_FAMILY_NAME_FIELD.setValue(value);
        return page(SurveyPage.class);
    }

    public SurveyPage fillRelativeNameField(String value) {
        FIRST_RELATIVE_NAME_FIELD.setValue(value);
        return page(SurveyPage.class);
    }

    public SurveyPage fillRelativeSecondNameField(String value) {
        FIRST_RELATIVE_SECOND_NAME_FIELD.setValue(value);
        return page(SurveyPage.class);
    }

    public SurveyPage fillRelativePhoneNumberField(String value) {
        FIRST_RELATIVE_PHONE_NUMBER_FIELD.setValue(value);
        return page(SurveyPage.class);
    }

    public SurveyPage fillRelativeAddressField(String value) {
        FIRST_RELATIVE_ADDRESS_FIELD.setValue(value);
        return page(SurveyPage.class);
    }

    public SurveyPage chooseRelativeBirthDate(Date dateToChoose) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateToChoose);
        int yearToChoose = calendar.get(Calendar.YEAR);
        String monthToChoose = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH).toUpperCase();
        int dayToChoose = calendar.get(Calendar.DAY_OF_MONTH);

        clickFirstRelativeBirthdayChooser();
        SelenideElement choosePeriodButton = DATE_PICKER.$(By.xpath("//button[contains(@class, 'mat-calendar-period-button')]"));
        SelenideElement previousPeriodButton = DATE_PICKER.$(By.xpath("//button[contains(@class, 'mat-calendar-previous-button')]"));
        SelenideElement nextPeriodButton = DATE_PICKER.$(By.xpath("//button[contains(@class, 'mat-calendar-next-button')]"));

        choosePeriodButton.click();
        while (true) {
            int earliestYearInPeriod = Integer.parseInt(DATE_PICKER.$(By.xpath(".//div[contains(@class, 'mat-calendar-body-cell')]")).getText());
            int latestYearInPeriod = Integer.parseInt(DATE_PICKER.$(By.xpath(".//tr[last()]//td[contains(@class, 'mat-calendar-body-cell')][last()]")).getText());
            if (earliestYearInPeriod < yearToChoose && yearToChoose < latestYearInPeriod) {
                DATE_PICKER.$(By.xpath("//div[.='" + yearToChoose + "']")).click();
                break;
            } else {
                if (yearToChoose < earliestYearInPeriod) {
                    previousPeriodButton.click();
                }
                if (yearToChoose > latestYearInPeriod) {
                    nextPeriodButton.click();
                }
            }
        }
        DATE_PICKER.$(By.xpath("//div[.='" + monthToChoose + "']")).click();
        DATE_PICKER.$(By.xpath("//div[.='" + dayToChoose + "']")).click();
        return page(SurveyPage.class);
    }

    private void chooseInsuranceFromList(Insurance insurance) {
        $(By.xpath(("//span[contains(text(),'" + insurance.takeInsuranceTypeAsString() + "')]"))).click();
    }

    public SurveyPage chooseRelativeInsuranceType(Insurance insurance) {
        clickFirstRelativeInsuranceTypeChooser();
        chooseInsuranceFromList(insurance);
        return page(SurveyPage.class);
    }

    public void checkThatRelativeFullNameIsEqual(String fullName) {
        SoftAssertions softly = new SoftAssertions();
        String familyName = getFamilyNameFromFullName(fullName);
        softly.assertThat(FIRST_RELATIVE_FAMILY_NAME_FIELD.getValue()).as("Not expected family name").isEqualTo(familyName);
        String name = getNameFromFullName(fullName);
        softly.assertThat(FIRST_RELATIVE_NAME_FIELD.getValue()).as("Not expected name").isEqualTo(name);
        String secondName = getSecondNameFromFullName(fullName);
        softly.assertThat(FIRST_RELATIVE_SECOND_NAME_FIELD.getValue()).as("Not expected second name").isEqualTo(secondName);
        softly.assertAll();
    }

    public void checkThatRelativeBirthDateIsEqual(Date date) {
        SelenideElement birthDateField = RELATIVE_FORMS.first().$(By.xpath("//input[contains(@placeholder, 'Date of birth')]"));
        assertThat(birthDateField.getValue()).as("Not expected date").isEqualTo(DATE_FORMAT.format(date));
    }

    public void checkThatRelativePhoneNumberIsEqual(String phoneNumber) {
        assertThat(FIRST_RELATIVE_PHONE_NUMBER_FIELD.getValue()).as("Not expected phone number").isEqualTo(phoneNumber);
    }

    public void checkThatRelativeAddressIsEqual(String address) {
        assertThat(FIRST_RELATIVE_ADDRESS_FIELD.getValue()).as("Not expected address").isEqualTo(address);
    }

    public void checkThatRelativeInsuranceTypeIsEqual(Insurance insurance) {
        SelenideElement insuranceField = RELATIVE_FORMS.first().$(By.xpath(".//span[contains(@class, 'ng-star-inserted')]//span"));
        assertThat(insuranceField.getText()).as("Not expected insurance type").isEqualTo(insurance.takeInsuranceTypeAsString());
    }

    public void checkButtonsIsVisible() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ADD_PERSON_BUTTON.isDisplayed()).as("'Add person' button is not displaying").isTrue();
        softly.assertThat(SAVE_BUTTON.isDisplayed()).as("Save button is not displaying").isTrue();
        softly.assertAll();
    }

    public void checkLabelsIsVisible() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ADD_PERSON_LABEL.isDisplayed()).as("'Add person' label is not displaying").isTrue();
        softly.assertThat(FORM_HEADER.isDisplayed()).as("Form header is not displaying").isTrue();
        softly.assertAll();
    }

    public void checkEmployeeFieldsVisible() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(FORM_HEADER.isDisplayed()).as("Form header is not displaying").isTrue();
        softly.assertThat(EMPLOYEE_LABEL.isDisplayed()).as("Employee label is not displaying").isTrue();
        softly.assertThat(EMPLOYEE_NAME_FIELD.isDisplayed()).as("Employee name field is not displaying").isTrue();
        softly.assertThat(EMPLOYEE_FAMILY_NAME_FIELD.isDisplayed()).as("Employee family name field is not displaying").isTrue();
        softly.assertThat(EMPLOYEE_SECOND_NAME_FIELD.isDisplayed()).as("Employee second name field is not displaying").isTrue();
        softly.assertThat(EMPLOYEE_BIRTH_DATE_FIELD.isDisplayed()).as("Employee birth date field is not displaying").isTrue();
        softly.assertThat(EMPLOYEE_PHONE_NUMBER_FIELD.isDisplayed()).as("Employee phone number is not displaying").isTrue();
        softly.assertThat(EMPLOYEE_ADDRESS_FIELD.isDisplayed()).as("Employee address field is not displaying").isTrue();
        softly.assertThat(EMPLOYEE_POSITION.isDisplayed()).as("Employee position is not displaying").isTrue();
        softly.assertThat(EMPLOYMENT_DATE.isDisplayed()).as("Employment date is not displaying").isTrue();
        softly.assertThat(EMPLOYEE_INSURANCE_TYPE.isDisplayed()).as("Employee insurance type checkbox is not displaying").isTrue();
        softly.assertAll();
    }

    public void checkReadOnlyEmployeeFields() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(EMPLOYEE_NAME_FIELD.is(readonly)).as("Employee field should not be editable").isTrue();
        softly.assertThat(EMPLOYEE_FAMILY_NAME_FIELD.is(readonly)).as("Employee family field should not be editable").isTrue();
        softly.assertThat(EMPLOYEE_SECOND_NAME_FIELD.is(readonly)).as("Employee second field should not be editable").isTrue();
        softly.assertThat(EMPLOYEE_BIRTH_DATE_FIELD.is(readonly)).as("Employee birth date should not be editable").isTrue();
        softly.assertThat(EMPLOYEE_POSITION.is(readonly)).as("Employee position should not be editable").isTrue();
        softly.assertThat(EMPLOYMENT_DATE.is(readonly)).as("Employment date should not be editable").isTrue();
        softly.assertThat(EMPLOYEE_ADDRESS_FIELD.is(readonly)).as("Employee address should not be editable").isTrue();
        softly.assertAll();
    }

    public void checkAdditionalPersonsFormsNotExist() {
        assertThat(RELATIVE_FORMS.isEmpty()).as("Additional persons forms should not exist").isTrue();
    }

    public void removeAllAdditionPersonForms() {
        RELATIVE_FORMS.forEach(form -> form.$(By.xpath(RELATIVE_REMOVE_BUTTON_XPATH)).click());
    }

    public void checkAdditionPersonFormsNumber(int expectedNumber) {
        assertThat(RELATIVE_FORMS.size()).as("Not expected number of additional person's fields").isEqualTo(expectedNumber);
    }

    public void checkAdditionPersonElementsAreVisible() {
        RELATIVE_FORMS.forEach(form -> assertThat(form.isDisplayed()).as("Some form is not displayed").isTrue());
    }

    public void checkAdditionPersonElementsAreEditable() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(FIRST_RELATIVE_FAMILY_NAME_FIELD.isEnabled()).as("Additional person's family name field is not editable").isTrue();
        softly.assertThat(FIRST_RELATIVE_NAME_FIELD.isEnabled()).as("Additional person's name field is not editable").isTrue();
        softly.assertThat(FIRST_RELATIVE_SECOND_NAME_FIELD.isEnabled()).as("Additional person's second name field is not editable").isTrue();
        softly.assertThat(FIRST_RELATIVE_BIRTH_DATE.isEnabled()).as("Additional person's birth date field is not editable").isTrue();
        softly.assertThat(FIRST_RELATIVE_PHONE_NUMBER_FIELD.isEnabled()).as("Additional person's phone number field is not editable").isTrue();
        softly.assertThat(FIRST_RELATIVE_ADDRESS_FIELD.isEnabled()).as("Additional person's address field is not editable").isTrue();
        softly.assertThat(FIRST_RELATIVE_INSURANCE_TYPE_CHOOSER.isEnabled()).as("Additional person's insurance type chooser is not editable").isTrue();
        softly.assertAll();
    }
}
