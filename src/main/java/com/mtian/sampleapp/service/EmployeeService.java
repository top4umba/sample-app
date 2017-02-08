package com.mtian.sampleapp.service;

import com.mtian.sampleapp.domain.Employee;


public interface EmployeeService {
	
	Employee get(Long id);
	
	Employee save(Employee employee);
	
	void update(Long id, Employee employee);
	
}
