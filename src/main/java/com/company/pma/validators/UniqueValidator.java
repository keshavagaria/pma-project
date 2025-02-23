package com.company.pma.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.pma.dao.EmployeeRepository;
import com.company.pma.entities.Employee;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String>{

	@Autowired
	EmployeeRepository employeeRepo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		System.out.println("Entered Validation method");
		Employee emp=employeeRepo.findByEmail(value);
		
		if(emp!=null)
			return false;
		else
				return true;
		
	}

}
