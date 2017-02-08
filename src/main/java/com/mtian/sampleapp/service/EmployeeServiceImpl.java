package com.mtian.sampleapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtian.sampleapp.domain.Employee;
import com.mtian.sampleapp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;

	@Override
	public Employee get(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Employee save(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public void update(Long id, Employee employee) {
		employee.setId(id);
		repository.save(employee);
	}

}
