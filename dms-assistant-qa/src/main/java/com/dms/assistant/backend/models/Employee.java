package com.dms.assistant.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class Employee {
    @NotNull
    private String fullName;
    @NotNull
    private String email;
    @NotNull
    private Date birthDate;
    @NotNull
    private String address;
    @NotNull
    private String phoneNumber;
    @Valid
    @NotNull
    private Insurance insurance;
    @NotNull
    private Date hireDate;
    @NotNull
    private String role;
    @NotNull
    private InternalRole internalRole;
    @Valid
    @NotNull
    private RelativeList relatives;

    public Employee(@JsonProperty(value = "fullName", required = true) String fullName,
                    @JsonProperty(value = "email", required = true) String email,
                    @JsonProperty(value = "birthDate", required = true) Date birthDate,
                    @JsonProperty(value = "address", required = true) String address,
                    @JsonProperty(value = "phoneNumber", required = true) String phoneNumber,
                    @JsonProperty(value = "insurance", required = true) Insurance insurance,
                    @JsonProperty(value = "hireDate", required = true) Date hireDate,
                    @JsonProperty(value = "role", required = true) String role,
                    @JsonProperty(value = "internalRole", required = true) InternalRole internalRole,
                    @JsonProperty(value = "relatives", required = true) RelativeList relatives) {

        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.insurance = insurance;
        this.hireDate = hireDate;
        this.role = role;
        this.internalRole = internalRole;
        this.relatives = relatives;
    }

    public Employee copy(Employee that) {
        return new Employee(that.getFullName(), that.getEmail(), that.getBirthDate(), that.getAddress(), that.getPhoneNumber(),
                this.insurance.copy(that.getInsurance()), that.getHireDate(), that.getRole(), that.getInternalRole(), this.relatives.copy(that.getRelatives()));
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

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public void setInsuranceType(Insurance.InsuranceType insuranceType) {
        this.insurance.setInsuranceType(insuranceType);
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public RelativeList getRelatives() {
        return relatives;
    }

    public void setRelatives(RelativeList relatives) {
        this.relatives = relatives;
    }

    public InternalRole getInternalRole() {
        return internalRole;
    }

    public void setInternalRole(InternalRole internalRole) {
        this.internalRole = internalRole;
    }

    public void addRelative(Relative relative) {
        this.relatives.add(relative);
    }

    public void deleteRelative(int id) {
        this.relatives.remove(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(fullName, employee.fullName) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(birthDate, employee.birthDate) &&
                Objects.equals(address, employee.address) &&
                Objects.equals(phoneNumber, employee.phoneNumber) &&
                Objects.equals(insurance, employee.insurance) &&
                Objects.equals(hireDate, employee.hireDate) &&
                Objects.equals(role, employee.role) &&
                Objects.equals(internalRole, employee.internalRole) &&
                Objects.equals(relatives, employee.relatives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, email, birthDate, address, phoneNumber, insurance, hireDate, role, internalRole, relatives);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", insurance=" + insurance +
                ", hireDate=" + hireDate +
                ", role='" + role + '\'' +
                ", internalRole='" + internalRole + '\'' +
                ", relatives=" + relatives +
                '}';
    }

    public enum InternalRole {
        ADMIN,
        USER
    }
}