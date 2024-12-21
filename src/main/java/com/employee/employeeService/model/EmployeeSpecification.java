package com.employee.employeeService.model;

import com.employee.model.EmployeeSearchCriteriaDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeSpecification {

    @Autowired
    private  EntityManager em;

    public static Specification<Employee> withSearchCriteria(EmployeeSearchCriteriaDTO searchCriteria) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (searchCriteria.getEmployeeFirstName() != null && !searchCriteria.getEmployeeFirstName().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeFirstName")), "%" + searchCriteria.getEmployeeFirstName().toLowerCase() + "%"));
            }

            if (searchCriteria.getEmployeeLastName() != null && !searchCriteria.getEmployeeLastName().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeLastName")), "%" + searchCriteria.getEmployeeLastName().toLowerCase() + "%"));
            }

            return predicate;
        };
    }

    public  List<Employee> findAll(EmployeeSearchCriteriaDTO searchCriteria){

        CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery= criteriaBuilder.createQuery(Employee.class);

        // select * from employee
        Root<Employee> root= criteriaQuery.from(Employee.class);

        // prepare where clause
        Predicate andPredicate = criteriaBuilder.conjunction();

        if (searchCriteria.getEmployeeFirstName() != null && !searchCriteria.getEmployeeFirstName().isEmpty()) {
            andPredicate = criteriaBuilder.and(andPredicate,
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeFirstName")), "%" + searchCriteria.getEmployeeFirstName().toLowerCase() + "%"));
        }

        if (searchCriteria.getEmployeeLastName() != null && !searchCriteria.getEmployeeLastName().isEmpty()) {
            andPredicate = criteriaBuilder.and(andPredicate,
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeLastName")), "%" + searchCriteria.getEmployeeLastName().toLowerCase() + "%"));
        }

        criteriaQuery.where(andPredicate);
        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        return query.getResultList();

    }
}
