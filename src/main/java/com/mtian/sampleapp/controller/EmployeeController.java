package com.mtian.sampleapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mtian.sampleapp.domain.Employee;
import com.mtian.sampleapp.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	private Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		log.info("Saving employee {}", employee);
		return employeeService.save(employee);
	}
	

	@PutMapping("/put/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateEmployee(@RequestBody Employee employee, @PathVariable long id) {
		log.info("Updating employee {}", employee);
		employeeService.update(id, employee);
	}
	
	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable long id){
		log.info("Get employee with id {}", id);
		return employeeService.get(id);
	}
}
