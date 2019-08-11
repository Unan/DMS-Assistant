package com.dms.assistant.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Rate {

    @NotNull
    @Min(value = 0)
    private int id;
    @NotNull
    @Min(value = 0)
    private int min;
    @NotNull
    @Min(value = 0)
    private int max;
    @NotNull
    @Min(value = 0)
    private double coefficient;

    public Rate(@JsonProperty(value = "id") int id,
                @JsonProperty(value = "min", required = true) int min,
                @JsonProperty(value = "max", required = true) int max,
                @JsonProperty(value = "coefficient", required = true) double coefficient) {

        this.id = id;
        this.min = min;
        this.max = max;
        this.coefficient = coefficient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return min == rate.min &&
                max == rate.max &&
                Double.compare(rate.coefficient, coefficient) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max, coefficient);
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", min=" + min +
                ", max=" + max +
                ", coefficient=" + coefficient +
                '}';
    }
}