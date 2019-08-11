package com.dms.assistant.frontend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value = "com.dms.assistant.frontend")
@PropertySource("config.properties")
public class ConfigFrontEnd {

    @Value("${HomePageURL}")
    public String HomePageURL;
}
