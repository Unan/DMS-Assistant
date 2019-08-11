package com.dms.assistant.frontend;

import com.dms.assistant.ConfigDMS;
import com.dms.assistant.frontend.steps.LoginPageSteps;
import com.dms.assistant.frontend.steps.SurveyPageSteps;
import com.dms.assistant.frontend.utils.DefaultTestListener;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.dms.assistant.backend.constants.Constants.*;
import static com.dms.assistant.frontend.constants.SurveyTestConstants.NUMBER_OF_FORMS_FOR_RELATIVE_SAVING_TEST;
import static com.dms.assistant.frontend.constants.SurveyTestConstants.NUMBER_OF_FORMS_FOR_SMOKE;

@ContextConfiguration(classes = {ConfigDMS.class})
@Listeners({DefaultTestListener.class})
@Epic("UI")
@Feature("Insurance")
@Story("[DMS-27] Implement layout of Survey Form page")
public class SurveyPageTest extends AbstractTestNGSpringContextTests {

    @Autowired
    SurveyPageSteps surveyPageSteps;

    @Autowired
    LoginPageSteps loginPageSteps;

    @Test(groups = {"Smoke", "UI"}, description = "Page initialization test", enabled = false)
    public void pageInitializationTest() {
        loginPageSteps.openLoginPage();
        loginPageSteps.signInWithLogin(CORRECT_EMAIL_2);
        surveyPageSteps.checkEmployeeFields();
        surveyPageSteps.checkButtonsAndLabelsOnPage();
        surveyPageSteps.checkAdditionalPersonsFormsNotExist();
    }

    @Test(groups = {"Smoke", "UI"}, description = "Additional persons forms smoke test", enabled = false)
    public void addingAndRemovingPersonsTest() {
        loginPageSteps.openLoginPage();
        loginPageSteps.signInWithLogin(CORRECT_EMAIL_2);
        surveyPageSteps.addAdditionalPersons(NUMBER_OF_FORMS_FOR_SMOKE);
        surveyPageSteps.checkAdditionalPersonFormsNumber(NUMBER_OF_FORMS_FOR_SMOKE);
        surveyPageSteps.checkAdditionalPersonElementsAreVisible();
        surveyPageSteps.checkAdditionalPersonFormsAreEditable();
        surveyPageSteps.removeAllAdditionPersons();
    }

    @Test(groups = {"UI"}, description = "Relatives information saving test", enabled = false)
    public void relativesInformationSavingTest() {
        loginPageSteps.openLoginPage();
        loginPageSteps.signInWithLogin(CORRECT_EMAIL_2);
        surveyPageSteps.addAdditionalPersons(NUMBER_OF_FORMS_FOR_RELATIVE_SAVING_TEST);
        surveyPageSteps.checkAdditionalPersonFormsNumber(NUMBER_OF_FORMS_FOR_RELATIVE_SAVING_TEST);
        surveyPageSteps.fillRelativeForm(RELATIVE_1);
        surveyPageSteps.saveProfile();
        surveyPageSteps.openPage();
        surveyPageSteps.checkAdditionalPersonFormsNumber(NUMBER_OF_FORMS_FOR_RELATIVE_SAVING_TEST);
        surveyPageSteps.checkRelative(RELATIVE_1);
    }
}
