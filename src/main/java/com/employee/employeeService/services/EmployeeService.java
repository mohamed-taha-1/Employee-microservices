package com.employee.employeeService.services;

import com.employee.employeeService.model.Employee;
import com.employee.model.EmployeeDTO;
import com.employee.model.EmployeeSearchCriteriaDTO;


import java.util.List;

public interface EmployeeService {
    EmployeeDTO getEmplyeeById(Long Id);
    Long save(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long employeeId);
    void deleteEmployee(Long employeeId);
    List<EmployeeDTO> getEmployees();
    List<EmployeeDTO> searchEmployees(EmployeeSearchCriteriaDTO searchCriteria);
}
