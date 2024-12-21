package com.employee.employeeService;

import com.employee.employeeService.mappers.EmployeeMapper;
import com.employee.employeeService.model.Employee;
import com.employee.employeeService.model.EmployeeRepository;
import com.employee.employeeService.model.EmployeeSpecification;
import com.employee.employeeService.services.impl.EmployeeServiceImpl;
import com.employee.model.EmployeeDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;
    @Mock
    private EmployeeSpecification employeeSpecification;

    @Test
    @DisplayName("create-new-employee")
    void save(){
        EmployeeDTO employeeDTO = new EmployeeDTO()
                .employeeEmail("mohamedtahaabdel@gmail.com")
                .employeeFirstName("mohamed")
                .employeeLastName("taha");

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        when(employeeMapper.mapTOEntity(any(EmployeeDTO.class))).thenReturn(new Employee());
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee());

        employeeService.save(employeeDTO);

        verify(employeeRepository , times(1)).save(employeeArgumentCaptor.capture());
    }

    @Test
    @DisplayName("get-employee-by-id")
    void getEmployeeDetails(){
        Employee mockEmployee = new Employee();
        mockEmployee.setEmployeeId(1L);
        mockEmployee.setEmployeeFirstName("mohamed taha");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(mockEmployee));
        when( employeeMapper.mapToDTO(mockEmployee)).thenReturn(new EmployeeDTO());

        employeeService.getEmplyeeById(1L);

        verify(employeeRepository , times(1)).findById(1L);
    }

    @Test
    @DisplayName("find-all-employees")
    void getEmployees(){
        when(employeeRepository.findAll()).thenReturn(List.of(new Employee()));
        when(employeeMapper.mapToListOfDTOs(anyList())).thenReturn(List.of(new EmployeeDTO()));

        employeeService.getEmployees();

        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("delete-employee-by-id")
    void deleteEmployee(){
        Employee mockEmployee = new Employee();
        mockEmployee.setEmployeeId(1L);
        mockEmployee.setEmployeeFirstName("mohamed taha");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(mockEmployee));
        when( employeeMapper.mapToDTO(mockEmployee)).thenReturn(new EmployeeDTO());
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);

      verify(employeeRepository, times(1)).deleteById(1L);

    }
}
