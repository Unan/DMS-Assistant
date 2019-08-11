package com.dms.assistant.backend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class TakeData {

    public static <T> T takeData(String jsonFile, Class<T> currentClass) {
        try {
            File file = new ClassPathResource("json-data/" + jsonFile + ".json").getFile();
            String contents = new String(Files.readAllBytes(file.toPath()));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(contents, currentClass);
        } catch (IOException e) {
            return null;
        }
    }
}
