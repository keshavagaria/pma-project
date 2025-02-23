package com.company.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.pma.dao.EmployeeRepository;
import com.company.pma.dao.ProjectRepository;
import com.company.pma.dto.TimeChartData;
import com.company.pma.entities.Employee;
import com.company.pma.entities.Project;
import com.company.pma.services.EmployeeService;
import com.company.pma.services.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects=proService.getAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}
	
	@RequestMapping("/new")
	public String displayProjectForm(Model model)
	{
		Project aProject=new Project();
		List<Employee> employees=empService.getAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);	
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project,@RequestParam List<Long> employees,Model model) {
		
		
		proService.save(project);
//		Iterable<Employee> chosenEmployee=empRepo.findAllById(employees);
//		for(Employee emp:chosenEmployee) {
//			emp.setTheProject(project);
//			empRepo.save(emp);
//		}
		
		//use a redirect to prevent duplicate submissions
		return "redirect:/projects/new";
		
	}
	
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long projId, Model model) {
		
		Project project=proService.findByProjectId(projId);
		model.addAttribute("project",project);
		return "projects/new-project";
		
	}
	
	@GetMapping("/delete")
	public String displayProjectDeleteForm(@RequestParam("id") long projId, Model model) {
		
		Project project=proService.findByProjectId(projId);
		proService.delete(project);
		
		return "redirect:/projects";
		
	}
	
	@GetMapping("/timelines")
    public String displayProjectTimelines(Model model) throws JsonProcessingException {

		List<TimeChartData> timeLineData=proService.getTimeData();
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonTimeLineString=objectMapper.writeValueAsString(timeLineData);

		System.out.println("-------Project TImeline-----------");
		System.out.println(jsonTimeLineString);

		model.addAttribute("projectTimeList", jsonTimeLineString);
        return "projects/project-timelines";
    }

}
