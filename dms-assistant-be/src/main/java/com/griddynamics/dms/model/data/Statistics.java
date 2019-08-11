package com.griddynamics.dms.model.data;

public class Statistics {

    private int employeesNumber;

    private int employeesHaveNone;

    private int employeesHaveStandard;

    private int employeesHaveBusiness;

    private int employeesHaveVip;

    private int relativesNumber;

    private int relativesHaveNone;

    private int relativesHaveStandard;

    private int relativesHaveBusiness;

    private int relativesHaveVip;

    private int relativesHaveVipChild;

    public void incrementEmployeeNumber() {
        employeesNumber++;
    }

    public void incrementEmployeesHaveNone() {
        employeesHaveNone++;
    }

    public void incrementEmployeesHaveStandard() {
        employeesHaveStandard++;
    }

    public void incrementEmployeesHaveBusiness() {
        employeesHaveBusiness++;
    }

    public void incrementEmployeesHaveVip() {
        employeesHaveVip++;
    }

    public void incrementRelativesNumber() {
        relativesNumber++;
    }

    public void incrementRelativesHaveNone() {
        relativesHaveNone++;
    }

    public void incrementRelativesHaveStandard() {
        relativesHaveStandard++;
    }

    public void incrementRelativesHaveBusiness() {
        relativesHaveBusiness++;
    }

    public void incrementRelativesHaveVip() {
        relativesHaveVip++;
    }

    public void incrementRelativesHaveVipChild() {
        relativesHaveVipChild++;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        employeesNumber = employeesNumber;
    }

    public int getEmployeesHaveNone() {
        return employeesHaveNone;
    }

    public void setEmployeesHaveNone(int surveyUnfilled) {
        employeesHaveNone = surveyUnfilled;
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