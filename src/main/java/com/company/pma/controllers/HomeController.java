package com.company.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.company.pma.dao.EmployeeRepository;
import com.company.pma.dao.ProjectRepository;
import com.company.pma.dto.ChartData;
import com.company.pma.dto.EmployeeProject;
import com.company.pma.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;

	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		Map<String, Object> map=new HashMap<>();
		
		model.addAttribute("versionNumber", ver);
		
		List<Project> projects=projectRepository.findAll();
		model.addAttribute("projectList", projects);
		
		List<ChartData> projectData=projectRepository.getProjectStatus();
		
		//lets convert projectData Object into json structure for use in javascript	
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonString=objectMapper.writeValueAsString(projectData);
		
		model.addAttribute("projectStatusCount", jsonString);
		
		//List<Employee> employees=employeeRepository.findAll();
		List<EmployeeProject> employeesProjectCount=employeeRepository.employeeProjects();
		model.addAttribute("employeesProjectCount", employeesProjectCount);
		return "main/home";
	}
	
	
}
