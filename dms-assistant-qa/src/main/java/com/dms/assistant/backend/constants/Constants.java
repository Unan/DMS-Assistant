package com.dms.assistant.backend.constants;

import com.dms.assistant.backend.models.*;
import com.dms.assistant.backend.utils.TakeData;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface Constants {

    EmployeeList EMPLOYEES = TakeData.takeData("listOfEmployees", EmployeeList.class);
    EmployeeList EMPLOYEES_WITHOUT_INSURANCE = EMPLOYEES.getEmployeeByInsuranceType(Insurance.InsuranceType.NONE);
    InsuranceData INSURANCE_DATA = TakeData.takeData("insuranceData", InsuranceData.class);
    InsuranceData NEW_INSURANCE_DATA = TakeData.takeData("newInsuranceData", InsuranceData.class);
    String CORRECT_EMAIL_1 = "vkolesnikov@griddynamics.com";
    String CORRECT_EMAIL_2 = "okogina@griddynamics.com";
    String CORRECT_EMAIL_3 = "mlevshunov@griaddynamics.com";
    String ADMIN_EMAIL = "smironov@griddynamics.com";
    String CORRECT_EMAIL_5 = "aivanov@griddynamics.com";
    String INCORRECT_EMAIL = "incorect1@mail.com";
    Auth INCORRECT_LOGIN = new Auth(INCORRECT_EMAIL);
    Auth CORRECT_LOGIN_1 = new Auth(CORRECT_EMAIL_1);
    Auth CORRECT_LOGIN_2 = new Auth(CORRECT_EMAIL_2);
    Auth CORRECT_LOGIN_3 = new Auth(CORRECT_EMAIL_3);
    Auth ADMIN = new Auth(ADMIN_EMAIL);
    Auth CORRECT_LOGIN_5 = new Auth(CORRECT_EMAIL_5);
    Employee EMPLOYEE_3 = EMPLOYEES.getEmployeeByEmail(CORRECT_EMAIL_3);
    String ROLE_HR = "Human resources manager";
    String FULL_NAME_1 = "Ivanov Ivan Ivanovich";
    String ADDRESS_1 = "city Saratov, home 100";
    String PHONE_NUMBER_1 = "88001111111";
    Date BIRTH_DATE_1 = new Date(100, 10, 10);
    Date HIRE_DATE_1 = new Date(101, 2, 3);
    Relative RELATIVE_1 = TakeData.takeData("relative1", Relative.class);
    Integer REL_0 = 0;
    String FULL_NAME_2 = "Petrov Petr Petrovich";
    String ADDRESS_2 = "city Saratov, home 101";
    String PHONE_NUMBER_2 = "88002222222";
    Date BIRTH_DATE_2 = new Date(102, 10, 10);
    String NOT_EXPECTED_NUMBER = "Not expected number of ";
    String VALID_NOTIFY_TEXT = "Добрый день. \\nЦелью данного опроса является выбор программы ДМС (Добровольного медицинского страхования) на новый период с 01 июня 2017 по 31 мая 2018. \\nПо этой ссылке вы можете ознакомиться с предлагаемыми программами и их наполнением, списком лечебных учреждений, сравнительным анализом программ https://wiki.griddynamics.net/x/3kRDB \\nБенефитная программа находится здесь: https://wiki.griddynamics.net/x/b5kOBg \\nОбращаю внимание, что вы можете застраховать родственников по корпоративным тарифам. \\nПрошу пройти опрос до 12 мая. \\nЕсли у вас есть вопросы - задавайте любым удобным для вас способом. \\nЧтобы начать опрос, нажмите кнопку ниже. Благодарим за участие!!";
    String VALID_NOTIFY_SUBJECT = "ДМС";
    String VALID_EMAIL_SENDER_ADDRESS = "dms-assistant@griddynamics.com";
    String VALID_REMIND_TEXT = "Недавно мы связывались с Вами по поводу опроса, но, как оказалось, Вы не закончили этот опрос. Мы были бы очень рады Вашему участию. \\nДайте ответ ниже, чтобы начать или продолжить опрос. Спасибо, что уделили нам время.";
    String VALID_REMIND_SUBJECT = "Напоминание: нам важно Ваше мнение";
    int EXPECTED_NUMBER_OF_SUBJECTS = 1;
    String EMPTY = StringUtils.EMPTY;

    String INVALID_PHONE_NUMBER_MORE_10 = "88888888888";
    String INVALID_PHONE_NUMBER_LESS_10 = "900008888";
    String INVALID_PHONE_NUMBER_WITH_LETTERS = "8800900b00";
    String INVALID_PHONE_NUMBER_WITH_SPACE_1 = "80000002 1";
    String INVALID_PHONE_NUMBER_WITH_SPACE_2 = "80000002 41";
    String INVALID_PHONE_NUMBER_ZERO_START = "0909009989";

    String INVALID_FULL_NAME_LESS_2 = "Abra";
    String INVALID_FULL_NAME_MORE_3 = "Anton Anton Anton Anton";

    SimpleDateFormat REPORT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
}
