package com.dms.assistant.frontend.steps;

import com.codeborne.selenide.WebDriverRunner;
import com.dms.assistant.ConfigDMS;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Parameters;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

@Component
@ContextConfiguration(classes = {ConfigDMS.class})
public class RoutingSteps extends AbstractTestNGSpringContextTests {

    @Value("${HomePageURL}")
    public String HomePageURL;

    @Step("Checking {route} page")
    @Parameters({"route"})
    public void openPageByRoute(String route) {
        open(HomePageURL + route);
        assertThat(WebDriverRunner.url()).as("Redirect on " + route + " page failed").contains(route);
    }
}
