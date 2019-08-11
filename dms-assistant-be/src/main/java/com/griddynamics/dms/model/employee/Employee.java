package com.griddynamics.dms.model.employee;

import javax.persistence.*;
import java.util.*;

@Entity
public class Employee {
    @Id
    private String email;

    private String fullName;

    private Date birthDate;

    private String phoneNumber;

    private String address;

    @OneToOne(fetch = FetchType.EAGER)
    private Insurance insurance;

    private Date hireDate;

    private String role;

    @Enumerated(EnumType.STRING)
    private InternalRole internalRole;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Relative> relatives;

    public Employee() {
        insurance = new Insurance();
        internalRole = InternalRole.USER;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
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

    public InternalRole getInternalRole() {
        return internalRole;
    }

    public void setInternalRole(InternalRole internalRole) {
        this.internalRole = internalRole;
    }

    public List<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Relative> relatives) {
        this.relatives = relatives;
    }
}