package com.griddynamics.dms.repository.insuranceData;

import com.griddynamics.dms.model.data.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends CrudRepository<Rate, String> {

}