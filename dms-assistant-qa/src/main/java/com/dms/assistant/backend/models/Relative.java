package com.dms.assistant.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class Relative {

    @NotNull
    private int id;
    @NotNull
    private String fullName;
    @NotNull
    private Date birthDate;
    @NotNull
    private String address;
    @NotNull
    private String phoneNumber;
    @Valid
    @NotNull
    private Insurance insurance;

    public Relative() {

    }

    public Relative(@JsonProperty(value = "id") int id,
                    @JsonProperty(value = "fullName", required = true) String fullName,
                    @JsonProperty(value = "birthDate", required = true) Date birthDate,
                    @JsonProperty(value = "address", required = true) String address,
                    @JsonProperty(value = "phoneNumber", required = true) String phoneNumber,
                    @JsonProperty(value = "insurance", required = true) Insurance insurance) {

        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.insurance = insurance;
    }

    public Relative copy(Relative that) {
        return new Relative(this.id, that.fullName, that.birthDate, that.address, that.phoneNumber, this.insurance.copy(that.insurance));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relative relative = (Relative) o;
        return Objects.equals(fullName, relative.fullName) &&
                Objects.equals(birthDate, relative.birthDate) &&
                Objects.equals(address, relative.address) &&
                Objects.equals(phoneNumber, relative.phoneNumber) &&
                Objects.equals(insurance, relative.insurance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, birthDate, address, phoneNumber, insurance);
    }

    @Override
    public String toString() {
        return "Relative{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", insurance=" + insurance +
                '}';
    }
}
