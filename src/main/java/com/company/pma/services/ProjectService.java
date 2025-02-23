package com.company.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pma.dao.ProjectRepository;
import com.company.pma.dto.ChartData;
import com.company.pma.dto.TimeChartData;
import com.company.pma.entities.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	public Project save(Project project) {
		
		return projectRepository.save(project);
	}
	
	public List<Project> getAll(){
		return projectRepository.findAll();
	}
	
	public List<ChartData> getProjectStatus() {
		return projectRepository.getProjectStatus();
	}

	

	public Project findByProjectId(long projId) {
		// TODO Auto-generated method stub
		return projectRepository.findByProjectId(projId);
	}

	public void delete(Project project) {
		
		projectRepository.delete(project);
		
	}
	
	public List<TimeChartData> getTimeData(){
		
		return projectRepository.getTimeData();
	}
}
