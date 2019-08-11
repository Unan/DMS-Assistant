package com.dms.assistant.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Insurance {

    @NotNull
    private int id;
    @NotNull
    private InsuranceType insuranceType;
    @NotNull
    private int amount;
    @NotNull
    private double coefficient;

    public Insurance() {

    }

    public Insurance(@JsonProperty(value = "id") int id,
                     @JsonProperty(value = "insuranceType", required = true) InsuranceType insuranceType,
                     @JsonProperty(value = "amount", required = true) int amount,
                     @JsonProperty(value = "coefficient") double coefficient) {

        this.id = id;
        this.insuranceType = insuranceType;
        this.amount = amount;
        this.coefficient = coefficient;
    }

    public Insurance copy(Insurance that) {
        return new Insurance(this.id, that.getInsuranceType(), that.getAmount(), this.coefficient);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public String takeInsuranceTypeAsString() {
        switch (this.insuranceType) {
            case STANDARD:
                return "Basic";
            case BUSINESS:
                return "Business";
            case VIP:
                return "V.I.P.";
            case VIP_CHILD:
                return "Child V.I.P.";
            default:
                return null;
        }
    }

    public String takeInsuranceTypeInRussian() {
        switch (this.insuranceType) {
            case STANDARD:
                return "Стандарт";
            case BUSINESS:
                return "Бизнес";
            case VIP:
                return "VIP";
            case VIP_CHILD:
                return "VIP дети";
            default:
                return StringUtils.EMPTY;
        }
    }

    public Object takeFinalAmount() {
        return this.coefficient * this.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insurance insurance = (Insurance) o;
        return amount == insurance.amount &&
                insuranceType == insurance.insuranceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(insuranceType, amount);
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "id=" + id +
                ", insuranceType=" + insuranceType +
                ", amount=" + amount +
                ", coefficient=" + coefficient +
                '}';
    }

    public enum InsuranceType {
        NONE,
        STANDARD,
        BUSINESS,
        VIP,
        VIP_CHILD,
        INVALID
    }
}
