package com.griddynamics.dms.repository.employee;

import com.griddynamics.dms.model.employee.Employee;
import com.griddynamics.dms.model.employee.InsuranceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

    Employee findByEmail(String email);

    List<Employee> getAllBy();

    List<Employee> findAllByInsuranceInsuranceTypeLike(InsuranceType insuranceType);
}