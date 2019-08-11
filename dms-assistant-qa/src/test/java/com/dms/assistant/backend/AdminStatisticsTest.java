package com.dms.assistant.backend;

import com.dms.assistant.ConfigDMS;
import com.dms.assistant.backend.models.EmployeeList;
import com.dms.assistant.backend.models.Insurance;
import com.dms.assistant.backend.models.Statistics;
import com.dms.assistant.backend.steps.AdminStatsticsSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static com.dms.assistant.backend.constants.Constants.CORRECT_EMAIL_1;
import static com.dms.assistant.backend.constants.Constants.EMPLOYEES;

@ContextConfiguration(classes = ConfigDMS.class)
@Epic("Services")
@Feature("Admin API tests")
@Story("[DMS-102] Return statistics")
public class AdminStatisticsTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AdminStatsticsSteps adminStatsticsSteps;

    @Test(groups = {"Services", "Smoke"}, description = "Sending statistics get request as user")
    public void getStatisticsByUser() {
        adminStatsticsSteps.getStatisticsAsUser();
    }

    @Test(groups = {"Services", "Smoke"}, description = "Check statistics parameters")
    public void getStatisticsByAdmin() {
        Statistics statistics = adminStatsticsSteps.getStatisticsAsAdmin();
        adminStatsticsSteps.checkStatisticParameters(statistics, EMPLOYEES);
    }

    @Test(groups = "Services", description = "Check statistics after changing data")
    public void changeStatistics() {
        Statistics statistics = adminStatsticsSteps.getStatisticsAsAdmin();
        adminStatsticsSteps.checkStatisticParameters(statistics, EMPLOYEES);
        EmployeeList changedEmployees = adminStatsticsSteps.changeEmployeeInsuranceData(EMPLOYEES, CORRECT_EMAIL_1, Insurance.InsuranceType.BUSINESS);
        statistics = adminStatsticsSteps.getStatisticsAsAdmin();
        adminStatsticsSteps.checkStatisticParameters(statistics, changedEmployees);
    }

    @AfterClass(groups = "Services", description = "Restoring data")
    public void restoreData() {
        adminStatsticsSteps.restoreData();
    }
}
