package com.dms.assistant.frontend.steps;

import com.dms.assistant.backend.models.Relative;
import com.dms.assistant.frontend.pages.SurveyPage;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyPageSteps {

    @Autowired
    SurveyPage surveyPage;

    @Step("Check that employee fields working correctly")
    public void checkEmployeeFields() {
        surveyPage.checkEmployeeFieldsVisible();
        surveyPage.checkReadOnlyEmployeeFields();
    }

    @Step("Page opening")
    public SurveyPage openPage() {
        return surveyPage.openPage();
    }

    @Step("Check that additional persons forms are not exist")
    public void checkAdditionalPersonsFormsNotExist() {
        surveyPage.checkAdditionalPersonsFormsNotExist();
    }

    @Step("Check that additional person elements are visible")
    public void checkAdditionalPersonElementsAreVisible() {
        surveyPage.checkAdditionPersonElementsAreVisible();
    }

    @Step("Check that buttons and labels on page displaying")
    public void checkButtonsAndLabelsOnPage() {
        surveyPage.checkButtonsIsVisible();
        surveyPage.checkLabelsIsVisible();
    }

    @Step("Check that number of forms is equal to {expectedNumber}")
    public void checkAdditionalPersonFormsNumber(int expectedNumber) {
        surveyPage.checkAdditionPersonFormsNumber(expectedNumber);
    }

    @Step("Delete all additional person forms")
    public void removeAllAdditionPersons() {
        surveyPage.removeAllAdditionPersonForms();
    }

    @Step("Add {number} additional person forms")
    public void addAdditionalPersons(int number) {
        surveyPage.addAdditionalPersonForms(number);
    }


    @Step("Check that additional person elements are editable")
    public void checkAdditionalPersonFormsAreEditable() {
        surveyPage.checkAdditionPersonElementsAreEditable();
    }

    @Step("Fill relative's form as {relative.fullName}")
    public void fillRelativeForm(Relative relative) {
        surveyPage.fillFullName(relative.getFullName());
        surveyPage.chooseRelativeBirthDate(relative.getBirthDate());
        surveyPage.fillRelativePhoneNumberField(relative.getPhoneNumber());
        surveyPage.fillRelativeAddressField(relative.getAddress());
        surveyPage.chooseRelativeInsuranceType(relative.getInsurance());
    }

    @Step("Check that relative date is equal")
    public void checkRelative(Relative relative) {
        surveyPage.checkThatRelativeFullNameIsEqual(relative.getFullName());
        surveyPage.checkThatRelativeBirthDateIsEqual(relative.getBirthDate());
        surveyPage.checkThatRelativePhoneNumberIsEqual(relative.getPhoneNumber());
        surveyPage.checkThatRelativeAddressIsEqual(relative.getAddress());
        surveyPage.checkThatRelativeInsuranceTypeIsEqual(relative.getInsurance());
    }

    @Step("Save profile")
    public void saveProfile() {
        surveyPage.clickSaveButton();
    }
}
