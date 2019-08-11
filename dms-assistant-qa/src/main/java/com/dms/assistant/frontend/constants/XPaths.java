package com.dms.assistant.frontend.constants;

public interface XPaths {
    String RELATIVE_FAMILY_NAME_XPATH = ".//input[contains(@placeholder, 'Family name')]";
    String RELATIVE_NAME_XPATH = ".//input[contains(@placeholder, 'First name')]";
    String RELATIVE_SECOND_NAME_XPATH = ".//input[contains(@placeholder, 'Second name')]";
    String RELATIVE_BIRTH_DATE_CHOOSER_XPATH = ".//button[contains(@class, 'mat-icon-button')]";
    String RELATIVE_PHONE_NUMBER_XPATH = ".//input[contains(@placeholder, 'Phone number')]";
    String RELATIVE_ADDRESS_XPATH = ".//textarea[contains(@placeholder, 'Address')]";
    String RELATIVE_INSURANCE_TYPE_CHOOSER = ".//div[contains(@class, 'mat-select-arrow-wrapper')]";
    String RELATIVE_REMOVE_BUTTON_XPATH = ".//button[contains(@class, 'button-delete')]";
    String DATE_PICKER_XPATH = "//mat-calendar";
}
