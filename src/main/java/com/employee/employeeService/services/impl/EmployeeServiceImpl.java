package com.employee.employeeService.services.impl;

import com.employee.employeeService.config.EntityNotFoundException;
import com.employee.employeeService.mappers.EmployeeMapper;
import com.employee.employeeService.model.Employee;
import com.employee.employeeService.model.EmployeeRepository;
import com.employee.employeeService.model.EmployeeSpecification;
import com.employee.employeeService.services.EmployeeService;
import com.employee.model.EmployeeDTO;
import com.employee.model.EmployeeSearchCriteriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private  EmployeeRepository employeeRepository;
    @Autowired
    private  EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeSpecification employeeSpecification;

    @Override
    public EmployeeDTO getEmplyeeById(Long Id) {
     Optional<Employee> employeeEntity= employeeRepository.findById(Id);
     if (employeeEntity.isPresent()) {
         return employeeMapper.mapToDTO(employeeEntity.get());
     }
        throw new EntityNotFoundException("Employee with ID " + Id + " not found");
    }
    @Override
    public Long save(EmployeeDTO employeeDTO) {
        Employee employeeEntity= employeeMapper.mapTOEntity(employeeDTO);
        Employee employee= employeeRepository.save(employeeEntity);
        return employee.getEmployeeId();
    }
    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long employeeId) {
        EmployeeDTO  existingEmployee= getEmplyeeById(employeeId);

       if(existingEmployee !=null){
           if (employeeDTO.getEmployeeEmail() != null) {
               existingEmployee.setEmployeeEmail(employeeDTO.getEmployeeEmail());
           }
           if (employeeDTO.getEmployeePhone() != null) {
               existingEmployee.setEmployeePhone(employeeDTO.getEmployeePhone());
           }
           if (employeeDTO.getEmployeeSalary() != null) {
               existingEmployee.setEmployeeSalary(employeeDTO.getEmployeeSalary());
           }
           if (employeeDTO.getEmployeeFirstName() != null) {
               existingEmployee.setEmployeeFirstName(employeeDTO.getEmployeeFirstName());
           }
           if (employeeDTO.getEmployeeLastName() != null) {
               existingEmployee.setEmployeeLastName(employeeDTO.getEmployeeLastName());
           }

           Employee newEmployee= employeeMapper.mapTOEntity(existingEmployee);
           newEmployee.setEmployeeId(employeeId);

           Employee employee= employeeRepository.save(newEmployee);

           return employeeMapper.mapToDTO(employee);
       }

        throw new EntityNotFoundException("Employee with ID " + employeeId + " not found");
    }
    @Override
    public void deleteEmployee(Long employeeId) {
        EmployeeDTO  existingEmployee= getEmplyeeById(employeeId);

        if(existingEmployee !=null) {
            employeeRepository.deleteById(employeeId);
        }else {
            throw new EntityNotFoundException("Employee with ID " + employeeId + " not found");
        }
    }
    @Override
    public List<EmployeeDTO> getEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeMapper.mapToListOfDTOs(employeeList);
    }
    @Override
    public List<EmployeeDTO> searchEmployees(EmployeeSearchCriteriaDTO searchCriteria) {
        List<Employee> employeeList = employeeSpecification.findAll(searchCriteria);
        return employeeMapper.mapToListOfDTOs(employeeList);
    }


}
