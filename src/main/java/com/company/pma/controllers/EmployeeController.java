package com.company.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.pma.entities.Employee;
import com.company.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	//Field Injection
	@Autowired
	EmployeeService empService;
	


	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee> employees=empService.getAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}
	
	@RequestMapping("/new")
	public String displayEmployeeForm(Model model)
	{
		Employee aEmployee=new Employee();
		model.addAttribute("employee", aEmployee);
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee(@Valid Employee employee,Errors errors) {
		
		if(errors.hasErrors())
			return "employees/new-employee";
		//use a redirect to prevent duplicate submissions
		empService.save(employee);
		return "redirect:/employees/new";
		
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long empId, Model model) {
		
		Employee employee=empService.findByEmployeeId(empId);
		model.addAttribute("employee",employee);
		return "employees/new-employee";
		
	}
	
	@GetMapping("/delete")
	public String displayEmployeeDeleteForm(@RequestParam("id") long empId, Model model) {
		
		Employee employee=empService.findByEmployeeId(empId);
		empService.delete(employee);
		
		return "redirect:/employees";
		
	}
	
	
}
