package com.dms.assistant.frontend.models;

import com.dms.assistant.backend.models.Employee;
import org.assertj.core.api.SoftAssertions;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.Objects;

public class EmployeeFront {

    @NotEmpty
    private String fullName;
    @NotEmpty
    private String email;
    @NotEmpty
    private Date birthDate;
    @NotEmpty
    private String address;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private Integer amount;

    public EmployeeFront() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeFront that = (EmployeeFront) o;
        return Objects.equals(fullName, that.fullName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(amount, that.amount);
    }

    public void equals(Employee o) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(this.fullName).as("Invalid fullName").isEqualTo(o.getFullName());
        softly.assertThat(this.email).as("Invalid email").isEqualTo(o.getEmail());
        softly.assertThat(this.birthDate).as("Invalid birthDate").isEqualTo(o.getBirthDate());
        softly.assertThat(this.address).as("Invalid address").isEqualTo(o.getAddress());
        softly.assertThat(this.phoneNumber).as("Invalid phoneNumber").isEqualTo(o.getPhoneNumber());
        softly.assertAll();
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, email, birthDate, address, phoneNumber, amount);
    }

    @Override
    public String toString() {
        return "EmployeeFront{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
