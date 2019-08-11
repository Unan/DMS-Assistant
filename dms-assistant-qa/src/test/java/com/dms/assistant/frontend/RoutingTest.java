package com.dms.assistant.frontend;

import com.dms.assistant.ConfigDMS;
import com.dms.assistant.frontend.steps.RoutingSteps;
import com.dms.assistant.frontend.utils.DefaultTestListener;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.dms.assistant.frontend.constants.Constants.*;

@Component
@ContextConfiguration(classes = {ConfigDMS.class})
@Listeners({DefaultTestListener.class})
@Epic("UI")
@Feature("Routing")
@Story("[DMS-29] Implement Routing in app")
public class RoutingTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public RoutingSteps routingSteps;

    @Test(groups = {"Smoke", "UI"}, description = "Routing test", enabled = false)
    public void routingTest() {
        routingSteps.openPageByRoute(LOGIN_ROUTE);
        routingSteps.openPageByRoute(SURVEY_ROUTE);
        routingSteps.openPageByRoute(ADMIN_ROUTE);
    }
}
