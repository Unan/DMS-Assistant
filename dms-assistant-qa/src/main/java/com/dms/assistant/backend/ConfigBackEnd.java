package com.dms.assistant.backend;

import com.dms.assistant.ConfigDMS;
import com.dms.assistant.backend.clients.AdminClient;
import com.dms.assistant.backend.clients.EmployeeClient;
import com.dms.assistant.backend.clients.FakeSMTPClient;
import com.dms.assistant.backend.clients.LoginClient;
import com.dms.assistant.backend.utils.DefaultClient;
import com.dms.assistant.backend.utils.DefaultLogger;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@Configuration
@ComponentScan(value = "com.dms.assistant.backend")
@ContextConfiguration(classes = {ConfigDMS.class})
public class ConfigBackEnd extends AbstractTestNGSpringContextTests {

    @Value("${server.address}")
    private String dmsAddress;

    @Value("${smtp.address}")
    private String smtpAddress;

    @Bean
    public AdminClient adminClient() {
        return Feign.builder()
                .client(new DefaultClient(null, null))
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logLevel(Logger.Level.FULL)
                .logger(new DefaultLogger(AdminClient.class))
                .target(AdminClient.class, dmsAddress);
    }

    @Bean
    public LoginClient loginClient() {
        return Feign.builder()
                .client(new DefaultClient(null, null))
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logLevel(Logger.Level.FULL)
                .logger(new DefaultLogger(LoginClient.class))
                .target(LoginClient.class, dmsAddress);
    }

    @Bean
    public EmployeeClient employeeClient() {
        return Feign.builder()
                .client(new DefaultClient(null, null))
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logLevel(Logger.Level.FULL)
                .logger(new DefaultLogger(EmployeeClient.class))
                .target(EmployeeClient.class, dmsAddress);
    }

    @Bean
    public FakeSMTPClient fakeSMTPClient() {
        return Feign.builder()
                .client(new DefaultClient(null, null))
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logLevel(Logger.Level.FULL)
                .logger(new DefaultLogger(FakeSMTPClient.class))
                .target(FakeSMTPClient.class, smtpAddress);
    }
}
