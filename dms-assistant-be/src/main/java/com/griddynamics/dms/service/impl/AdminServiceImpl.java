package com.griddynamics.dms.service.impl;

import com.griddynamics.dms.model.employee.*;
import com.griddynamics.dms.repository.employee.EmployeeRepository;
import com.griddynamics.dms.repository.employee.InsuranceRepository;
import com.griddynamics.dms.repository.employee.RelativeRepository;
import com.griddynamics.dms.service.AdminService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final EmployeeRepository employeeRepository;
    private final InsuranceRepository insuranceRepository;
    private final RelativeRepository relativeRepository;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private final String relativeRole = "Родственник";

    @Value("${admin}")
    private String adminEmail;

    @Autowired
    public AdminServiceImpl(EmployeeRepository employeeRepository,
                            InsuranceRepository insuranceRepository,
                            RelativeRepository relativeRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.insuranceRepository = insuranceRepository;
        this.relativeRepository = relativeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllBy();
    }

    @Override
    public List<Employee> findEmployeesWithoutInsurance() {
        return employeeRepository.findAllByInsuranceInsuranceTypeLike(InsuranceType.NONE);
    }

    @Override
    public ByteArrayInputStream makeReport() {

        ArrayList<Employee> employees = new ArrayList<>(employeeRepository.getAllBy());
        ArrayList<Relative> relatives = new ArrayList<>(relativeRepository.getAllBy());

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Employee insurance");
            Object[][] dataTable = new Object[employees.size() + 3 + relatives.size()][20];

            Object[] firstLine = {};
            Object[] secondLine = {"Список застрахованных лиц"};
            Object[] thirdLine = {
                    "№ п/п",
                    "ФИО",
                    "Дата рождения",
                    "Адрес проживания",
                    "Должность",
                    "Телефон",
                    "№ Прогр.",
                    "Страховая премия по прогр., р.",
                    "Коэффициент",
                    "Итоговая страховая премия, р."
            };
            dataTable[0] = firstLine;
            dataTable[1] = secondLine;
            dataTable[2] = thirdLine;

            int numeration = 0;

            for (Employee employee : employees) {
                Insurance insurance = employee.getInsurance();
                Object[] dataLineEmployee = {
                        numeration + 1,
                        getStringOrNull(employee.getFullName()),
                        getDateOrNull(employee.getBirthDate()),
                        getStringOrNull(employee.getAddress()),
                        getStringOrNull(employee.getRole()),
                        getStringOrNull(employee.getPhoneNumber()),
                        getStringOrNull(insuranceTypeInRussian(insurance.getInsuranceType())),
                        getStringOrNull(String.valueOf(insurance.getAmount())),
                        getStringOrNull(String.valueOf(insurance.getCoefficient())),
                        getStringOrNull(String.valueOf(insurance.getCoefficient() * insurance.getAmount())),
                };
                dataTable[numeration + 3] = dataLineEmployee;
                numeration++;
                for (Relative relative: employee.getRelatives()){
                    insurance = relative.getInsurance();
                    Object[] dataLineRelative = {
                            numeration + 1,
                            getStringOrNull(relative.getFullName()),
                            getDateOrNull(relative.getBirthDate()),
                            getStringOrNull(relative.getAddress()),
                            getStringOrNull(relativeRole),
                            getStringOrNull(relative.getPhoneNumber()),
                            getStringOrNull(insuranceTypeInRussian(insurance.getInsuranceType())),
                            getStringOrNull(String.valueOf(insurance.getAmount())),
                            getStringOrNull(String.valueOf(insurance.getCoefficient())),
                            getStringOrNull(String.valueOf(insurance.getCoefficient() * insurance.getAmount())),
                    };
                    dataTable[numeration + 3] = dataLineRelative;
                    numeration++;
                }

            }
            int rowNum = 0;
            for (Object[] data : dataTable) {
                sheet.autoSizeColumn(rowNum);
                Row row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (Object field : data) {
                    Cell cell = row.createCell(colNum++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getStringOrNull(String arg) {
        return StringUtils.isEmpty(arg) ? null : arg;
    }

    private String getDateOrNull(Date date) {
        return date == null ? "" : dateFormat.format(date);
    }

    private String insuranceTypeInRussian(InsuranceType insuranceType) {
        String insuranceTypeInRussian = "";
        switch (insuranceType) {
            case STANDARD:
                insuranceTypeInRussian = "Стандарт";
                break;
            case BUSINESS:
                insuranceTypeInRussian = "Бизнес";
                break;
            case VIP:
                insuranceTypeInRussian = "VIP";
                break;
            case VIP_CHILD:
                insuranceTypeInRussian = "VIP дети";
                break;
        }
        return insuranceTypeInRussian;
    }

    @Override
    public void uploadEmployeesFromExcelFile(XSSFWorkbook workbook) throws Exception {

        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Insurance> insurances = new ArrayList<>();

        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int i = 6; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            String email = String.valueOf(row.getCell(5));


            if (email != null) {
                Employee employee = new Employee();
                employee.setFullName(String.valueOf(row.getCell(1)));
                employee.setRole(String.valueOf(row.getCell(2)));

                String string = String.valueOf(row.getCell(3));
                employee.setHireDate(dateFormat.parse(string));

                employee.setPhoneNumber(String.valueOf(row.getCell(4)));
                employee.setEmail(String.valueOf(row.getCell(5)));
                employee.setAddress(String.valueOf(row.getCell(6)));

                string = String.valueOf(row.getCell(8));
                employee.setBirthDate(dateFormat.parse(string));

                if (employee.getEmail().equals(adminEmail)) {
                    employee.setInternalRole(InternalRole.ADMIN);
                }
                insurances.add(employee.getInsurance());
                employees.add(employee);
            }
        }
        insuranceRepository.saveAll(insurances);
        employeeRepository.saveAll(employees);
    }
}