package com.griddynamics.dms.service;

import com.griddynamics.dms.model.data.InsuranceData;
import com.griddynamics.dms.model.data.Statistics;

public interface InsuranceDataService {

    InsuranceData getData();

    void setData(InsuranceData insuranceData);

    Statistics getStatistics();
}