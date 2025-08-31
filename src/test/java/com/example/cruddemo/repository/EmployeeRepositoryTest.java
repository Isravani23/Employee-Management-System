package com.example.cruddemo.repository;

import com.example.entity.EmployeeDetails;
import com.example.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {
    @Autowired
    public EmployeeRepository employeeRepository;
    @Test
    void testFindById(){
        EmployeeDetails emp = new EmployeeDetails();
        emp.setFirstName("Alice");
        emp.setWorkLocation("Hyd");
        emp.setEmail("alice@corp.com");
        EmployeeDetails saved = employeeRepository.save(emp);
        assertThat(saved.getId()).isNotNull();
        Optional<EmployeeDetails> found = employeeRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo("Alice");
    }
    @Test
    void testFindByEmail() {
        EmployeeDetails emp = new EmployeeDetails();
        emp.setFirstName("Bob");
        emp.setWorkLocation("Blr");
        emp.setEmail("bob@corp.com");
        employeeRepository.save(emp);
        Optional<EmployeeDetails> found = employeeRepository.findByEmail("bob@corp.com");
        assertThat(found).isNotNull();
        assertThat(found.get().getWorkLocation()).isEqualTo("Blr");
    }
}
