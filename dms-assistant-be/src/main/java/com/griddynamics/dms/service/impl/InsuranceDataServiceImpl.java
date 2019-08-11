package com.griddynamics.dms.service.impl;

import com.griddynamics.dms.model.data.InsuranceData;
import com.griddynamics.dms.model.data.Statistics;
import com.griddynamics.dms.model.employee.Employee;
import com.griddynamics.dms.model.employee.Relative;
import com.griddynamics.dms.repository.employee.EmployeeRepository;
import com.griddynamics.dms.repository.insuranceData.InsuranceDataRepository;
import com.griddynamics.dms.repository.insuranceData.RateRepository;
import com.griddynamics.dms.service.InsuranceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceDataServiceImpl implements InsuranceDataService {

    private final EmployeeRepository employeeRepository;
    private final InsuranceDataRepository insuranceDataRepository;
    private final RateRepository rateRepository;

    @Autowired
    public InsuranceDataServiceImpl(EmployeeRepository employeeRepository,
                                    InsuranceDataRepository insuranceDataRepository,
                                    RateRepository rateRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.insuranceDataRepository = insuranceDataRepository;
        this.rateRepository = rateRepository;
    }

    @Override
    public InsuranceData getData() {
        return insuranceDataRepository.getAllBy();
    }

    @Override
    public void setData(InsuranceData insuranceData){

        InsuranceData currentInsuranceData = getData();
        if (currentInsuranceData != null) {
            for (int i = 0; i < insuranceData.getRates().size(); i++) {
                insuranceData.getRates().get(i).setId(currentInsuranceData.getRates().get(i).getId());
            }
            insuranceData.setId(currentInsuranceData.getId());
        }
        rateRepository.saveAll(insuranceData.getRates());
        insuranceDataRepository.save(insuranceData);
    }

    @Override
    public Statistics getStatistics() {
        Statistics statistics = new Statistics();
        Iterable<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {
            switch (employee.getInsurance().getInsuranceType()) {
                case NONE:
                    statistics.incrementEmployeesHaveNone();
                    break;
                case STANDARD:
                    statistics.incrementEmployeesHaveStandard();
                    break;
                case BUSINESS:
                    statistics.incrementEmployeesHaveBusiness();
                    break;
                case VIP:
                    statistics.incrementEmployeesHaveVip();
                    break;
            }
            statistics.incrementEmployeeNumber();

            for (Relative relative : employee.getRelatives()) {
                switch (relative.getInsurance().getInsuranceType()) {
                    case NONE:
                        statistics.incrementRelativesHaveNone();
                        break;
                    case STANDARD:
                        statistics.incrementRelativesHaveStandard();
                        break;
                    case BUSINESS:
                        statistics.incrementRelativesHaveBusiness();
                        break;
                    case VIP:
                        statistics.incrementRelativesHaveVip();
                        break;
                    case VIP_CHILD:
                        statistics.incrementRelativesHaveVipChild();
                        break;
                }
                statistics.incrementRelativesNumber();
            }
        }
        return statistics;
    }
}
