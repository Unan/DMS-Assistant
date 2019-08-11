package com.dms.assistant.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Statistics {

    @NotNull
    @Min(value = 0)
    private int employeesNumber;
    @NotNull
    @Min(value = 0)
    private int employeesHaveNone;
    @NotNull
    @Min(value = 0)
    private int employeesHaveStandard;
    @NotNull
    @Min(value = 0)
    private int employeesHaveBusiness;
    @NotNull
    @Min(value = 0)
    private int employeesHaveVip;
    @NotNull
    @Min(value = 0)
    private int relativesNumber;
    @NotNull
    @Min(value = 0)
    private int relativesHaveNone;
    @NotNull
    @Min(value = 0)
    private int relativesHaveStandard;
    @NotNull
    @Min(value = 0)
    private int relativesHaveBusiness;
    @NotNull
    @Min(value = 0)
    private int relativesHaveVip;
    @NotNull
    @Min(value = 0)
    private int relativesHaveVipChild;

    public Statistics(@JsonProperty(value = "employeesNumber", required = true) int employeesNumber,
                      @JsonProperty(value = "employeesHaveNone", required = true) int employeesHaveNone,
                      @JsonProperty(value = "employeesHaveStandard", required = true) int employeesHaveStandard,
                      @JsonProperty(value = "employeesHaveBusiness", required = true) int employeesHaveBusiness,
                      @JsonProperty(value = "employeesHaveVip", required = true) int employeesHaveVip,
                      @JsonProperty(value = "relativesNumber", required = true) int relativesNumber,
                      @JsonProperty(value = "relativesHaveNone", required = true) int relativesHaveNone,
                      @JsonProperty(value = "relativesHaveStandard", required = true) int relativesHaveStandard,
                      @JsonProperty(value = "relativesHaveBusiness", required = true) int relativesHaveBusiness,
                      @JsonProperty(value = "relativesHaveVip", required = true) int relativesHaveVip,
                      @JsonProperty(value = "relativesHaveVipChild", required = true) int relativesHaveVipChild) {

        this.employeesNumber = employeesNumber;
        this.employeesHaveNone = employeesHaveNone;
        this.employeesHaveStandard = employeesHaveStandard;
        this.employeesHaveBusiness = employeesHaveBusiness;
        this.employeesHaveVip = employeesHaveVip;
        this.relativesNumber = relativesNumber;
        this.relativesHaveNone = relativesHaveNone;
        this.relativesHaveStandard = relativesHaveStandard;
        this.relativesHaveBusiness = relativesHaveBusiness;
        this.relativesHaveVip = relativesHaveVip;
        this.relativesHaveVipChild = relativesHaveVipChild;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public int getEmployeesHaveNone() {
        return employeesHaveNone;
    }

    public void setEmployeesHaveNone(int employeesHaveNone) {
        this.employeesHaveNone = employeesHaveNone;
    }

    public int getEmployeesHaveStandard() {
        return employeesHaveStandard;
    }

    public void setEmployeesHaveStandard(int employeesHaveStandard) {
        this.employeesHaveStandard = employeesHaveStandard;
    }

    public int getEmployeesHaveBusiness() {
        return employeesHaveBusiness;
    }

    public void setEmployeesHaveBusiness(int employeesHaveBusiness) {
        this.employeesHaveBusiness = employeesHaveBusiness;
    }

    public int getEmployeesHaveVip() {
        return employeesHaveVip;
    }

    public void setEmployeesHaveVip(int employeesHaveVip) {
        this.employeesHaveVip = employeesHaveVip;
    }

    public int getRelativesNumber() {
        return relativesNumber;
    }

    public void setRelativesNumber(int relativesNumber) {
        this.relativesNumber = relativesNumber;
    }

    public int getRelativesHaveNone() {
        return relativesHaveNone;
    }

    public void setRelativesHaveNone(int relativesHaveNone) {
        this.relativesHaveNone = relativesHaveNone;
    }

    public int getRelativesHaveStandard() {
        return relativesHaveStandard;
    }

    public void setRelativesHaveStandard(int relativesHaveStandard) {
        this.relativesHaveStandard = relativesHaveStandard;
    }

    public int getRelativesHaveBusiness() {
        return relativesHaveBusiness;
    }

    public void setRelativesHaveBusiness(int relativesHaveBusiness) {
        this.relativesHaveBusiness = relativesHaveBusiness;
    }

    public int getRelativesHaveVip() {
        return relativesHaveVip;
    }

    public void setRelativesHaveVip(int relativesHaveVip) {
        this.relativesHaveVip = relativesHaveVip;
    }

    public int getRelativesHaveVipChild() {
        return relativesHaveVipChild;
    }

    public void setRelativesHaveVipChild(int relativesHaveVipChild) {
        this.relativesHaveVipChild = relativesHaveVipChild;
    }
}