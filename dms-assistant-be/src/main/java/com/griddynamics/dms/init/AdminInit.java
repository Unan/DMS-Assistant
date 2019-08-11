package com.griddynamics.dms.init;

import com.griddynamics.dms.model.employee.Employee;
import com.griddynamics.dms.model.employee.Insurance;
import com.griddynamics.dms.model.employee.InternalRole;
import com.griddynamics.dms.repository.employee.EmployeeRepository;
import com.griddynamics.dms.repository.employee.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Profile("prod")
@Component
public class AdminInit {
    private final EmployeeRepository employeeRepository;
    private final InsuranceRepository insuranceRepository;

    @Value("${admin}")
    private String adminEmail;

    @Autowired
    public AdminInit(EmployeeRepository employeeRepository, InsuranceRepository insuranceRepository) {
        this.employeeRepository = employeeRepository;
        this.insuranceRepository = insuranceRepository;
    }

    @PostConstruct
    private void adminInit() {
        Employee employeeAdmin = new Employee();
        Insurance insurance = new Insurance();
        employeeAdmin.setEmail(adminEmail);
        employeeAdmin.setInternalRole(InternalRole.ADMIN);

        employeeAdmin.setInsurance(insurance);
        insuranceRepository.save(insurance);
        employeeRepository.save(employeeAdmin);

    }
}
