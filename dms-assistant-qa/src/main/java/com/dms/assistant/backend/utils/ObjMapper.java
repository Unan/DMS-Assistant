package com.dms.assistant.backend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@Component
public class ObjMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public <T> void checkValidation(T object) {
        if (!isValid(object)) {
            throw new NullPointerException("Invalid data in response");
        }
    }

    public <T> void checkValidation(ArrayList<T> object) {
        if (!isValid(object)) {
            throw new NullPointerException("Invalid data in response");
        }
    }

    private <T> boolean isValid(T object) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        if (constraintViolations.size() != 0) {
            try {
                StringBuilder attachment = new StringBuilder("In object\n" + objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(object) + ":\n");
                for (ConstraintViolation<T> violation : constraintViolations) {
                    attachment.append(violation.getPropertyPath()).append(StringUtils.SPACE).append(violation.getMessage()).append("\n");
                }
                Allure.addAttachment("JSON Schema violation", attachment.toString());
                return false;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    private <T> boolean isValid(ArrayList<T> object) {
        boolean result = true;
        for (T obj : object) {
            if (!isValid(obj)) {
                result = false;
            }
        }
        return result;
    }

    public <T> T body2Object(String response, Class<T> currentClass) throws IOException {
        return objectMapper.readValue(response, currentClass);
    }

    public <T> String objest2Json(T object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }
}
