package com.griddynamics.dms.repository.insuranceData;

import com.griddynamics.dms.model.data.InsuranceData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceDataRepository extends CrudRepository<InsuranceData, String> {

    InsuranceData getAllBy();
}