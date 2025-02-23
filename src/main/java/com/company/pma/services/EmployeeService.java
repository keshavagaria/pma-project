package com.company.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pma.dao.EmployeeRepository;
import com.company.pma.dto.EmployeeProject;
import com.company.pma.entities.Employee;

@Service
public class EmployeeService {

	//Field Injection
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public Employee save(Employee employee) {
		
		return employeeRepository.save(employee);
}
	
	
	public List<Employee> getAll(){
		return employeeRepository.findAll();
	}
	
	public List<EmployeeProject> employeeProjects(){
		return employeeRepository.employeeProjects();
	}


	public Employee findByEmployeeId(long empId) {
		// TODO Auto-generated method stub
		return employeeRepository.findByEmployeeId(empId);
	}



	public void delete(Employee employee) {
		
		employeeRepository.delete(employee);
	}
	
}
