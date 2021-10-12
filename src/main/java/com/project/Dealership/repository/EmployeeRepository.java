package com.project.Dealership.repository;

import com.project.Dealership.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByTotalSalesCountGreaterThanEqualAndProfileEquals(Integer total, String profile);

    List<Employee> findByIsActiveTrue();
}
