package com.dms.assistant.backend.models;

import java.util.ArrayList;

public class RelativeList extends ArrayList<Relative> {

    public RelativeList copy(RelativeList relativeList) {
        RelativeList relatives = new RelativeList();
        for(int i = 0; i < this.size(); i++) {
            relatives.add(this.get(i).copy(relativeList.get(i)));
        }
        return relatives;
    }

    public RelativeList getRelativeByInsuranceType(Insurance.InsuranceType insuranceType) {
        RelativeList relatives = new RelativeList();
        for (Relative relative : this) {
            if (relative.getInsurance().getInsuranceType().equals(insuranceType)) {
                relatives.add(relative);
            }
        }
        return relatives;
    }
}
