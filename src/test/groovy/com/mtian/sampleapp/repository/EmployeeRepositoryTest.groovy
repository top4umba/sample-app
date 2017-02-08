package com.mtian.sampleapp.repository

import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.Matchers.hasSize
import static org.junit.Assert.assertThat

import org.hamcrest.Matchers
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import com.mtian.sampleapp.Application
import com.mtian.sampleapp.domain.Employee

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository repository;

	@Test
	void shouldPersistEmployee(){
		Employee employee = new Employee(name: "Mikhail Tian")
		
		def id = repository.saveAndFlush(employee).getId()
		Employee savedEmployee = repository.findOne id

		assertThat savedEmployee.getName(), equalTo ("Mikhail Tian")
	}

	@Test
	void shouldUpdateEmployee(){
		Employee employee = new Employee(name: "Mikhail Tian")
		def id = repository.saveAndFlush(employee).getId()
		Employee updatingEmployee = new Employee(id: id, name: "Updated Name")
		
		repository.saveAndFlush updatingEmployee
		def savedEmployees = repository.findAll()
		
		assertThat savedEmployees, hasSize (1)
		assertThat savedEmployees[0].name, equalTo ("Updated Name")
	}
	
	@After
	void cleanRepository(){
		repository.deleteAll()
	}
}
