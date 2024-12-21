package com.employee.employeeService.mappers;

import com.employee.employeeService.model.Employee;
import com.employee.model.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

   EmployeeDTO mapToDTO(Employee employeeEntity);

   @Mapping(target = "employeeId", ignore = true)
   Employee mapTOEntity(EmployeeDTO employeeDTO);

   default List<EmployeeDTO> mapToListOfDTOs(List<Employee> employeesList) {
      return employeesList.stream().map(this::mapToDTO).toList();
   }

}
