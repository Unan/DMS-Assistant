package com.griddynamics.dms.model.data;

import javax.persistence.*;
import java.util.List;

@Entity
public class InsuranceData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int standardAmount;

    private int businessAmount;

    private int vipAmount;

    private int vipChildAmount;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Rate> rates;

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
}
