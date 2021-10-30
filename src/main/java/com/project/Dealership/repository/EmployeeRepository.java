package com.project.Dealership.repository;

import com.project.Dealership.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByTotalSalesCountGreaterThanEqualAndProfileEquals(Integer total, String profile);

    List<Employee> findByIsActiveTrue();

    Optional<Employee> findByCpf(String cpf);

}
