package com.dms.assistant.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class InsuranceData {

    @NotNull
    private int id;
    @NotNull
    @Min(value = 0)
    private int standardAmount;
    @NotNull
    @Min(value = 0)
    private int businessAmount;
    @NotNull
    @Min(value = 0)
    private int vipAmount;
    @NotNull
    @Min(value = 0)
    private int vipChildAmount;
    @Valid
    @NotNull
    private List<Rate> rates;

    public InsuranceData(@JsonProperty(value = "id") int id,
                         @JsonProperty(value = "standardAmount", required = true) int standardAmount,
                         @JsonProperty(value = "businessAmount", required = true) int businessAmount,
                         @JsonProperty(value = "vipAmount", required = true) int vipAmount,
                         @JsonProperty(value = "vipChildAmount", required = true) int vipChildAmount,
                         @JsonProperty(value = "rates", required = true) List<Rate> rates) {
        this.id = id;
        this.standardAmount = standardAmount;
        this.businessAmount = businessAmount;
        this.vipAmount = vipAmount;
        this.vipChildAmount = vipChildAmount;
        this.rates = rates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStandardAmount() {
        return standardAmount;
    }

    public void setStandardAmount(int standardAmount) {
        this.standardAmount = standardAmount;
    }

    public int getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(int businessAmount) {
        this.businessAmount = businessAmount;
    }

    public int getVipAmount() {
        return vipAmount;
    }

    public void setVipAmount(int vipAmount) {
        this.vipAmount = vipAmount;
    }

    public int getVipChildAmount() {
        return vipChildAmount;
    }

    public void setVipChildAmount(int vipChildAmount) {
        this.vipChildAmount = vipChildAmount;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceData that = (InsuranceData) o;
        return standardAmount == that.standardAmount &&
                businessAmount == that.businessAmount &&
                vipAmount == that.vipAmount &&
                vipChildAmount == that.vipChildAmount &&
                rates.equals(that.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(standardAmount, businessAmount, vipAmount, vipChildAmount, rates);
    }

    @Override
    public String toString() {
        return "InsuranceData{" +
                "id=" + id +
                ", standardAmount=" + standardAmount +
                ", businessAmount=" + businessAmount +
                ", vipAmount=" + vipAmount +
                ", vipChildAmount=" + vipChildAmount +
                ", rates=" + rates +
                '}';
    }
}
