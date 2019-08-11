package com.dms.assistant;

import com.dms.assistant.backend.ConfigBackEnd;
import com.dms.assistant.frontend.ConfigFrontEnd;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {ConfigBackEnd.class, ConfigFrontEnd.class})
public class ConfigDMS {
}
