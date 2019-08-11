package com.griddynamics.dms.repository.employee;

import com.griddynamics.dms.model.employee.Insurance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends CrudRepository<Insurance, String> {

}