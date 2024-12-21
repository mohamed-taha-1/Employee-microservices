package com.employee.employeeService.controllers;

import com.employee.api.EmployeeApi;
import com.employee.employeeService.services.EmployeeService;
import com.employee.model.EmployeeDTO;
import com.employee.model.EmployeeSearchCriteriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class API implements EmployeeApi {
    @Autowired
    private EmployeeService employeeService;


    @Override
    public ResponseEntity<String> addEmployee(EmployeeDTO employeeDTO) {
        String id =employeeService.save(employeeDTO).toString();
        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(Long employeeId) {
       employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployeeById(Long employeeId) {
        EmployeeDTO employeeDTO= employeeService.getEmplyeeById(employeeId);
        return ResponseEntity.ok(employeeDTO);
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> searchEmployees(EmployeeSearchCriteriaDTO employeeSearchCriteriaDTO) {
        return ResponseEntity.ok(employeeService.searchEmployees(employeeSearchCriteriaDTO));
    }


    @Override
    public ResponseEntity<EmployeeDTO> updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeDTO newEmployee= employeeService.updateEmployee(employeeDTO,employeeId);
        return ResponseEntity.ok(newEmployee);
    }
}
