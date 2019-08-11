package com.griddynamics.dms.repository.employee;

import com.griddynamics.dms.model.employee.Relative;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelativeRepository extends CrudRepository<Relative, String> {

    List<Relative> getAllBy();

}