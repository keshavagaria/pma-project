package com.company.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.company.pma.dto.ChartData;
import com.company.pma.dto.TimeChartData;
import com.company.pma.entities.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{

	@Override
	public List<Project> findAll();
	
	
	@Query(nativeQuery = true,value = "SELECT stage as label, COUNT(*) as value " + 
			"FROM project " + 
			"GROUP BY stage")
	public List<ChartData> getProjectStatus();


	public Project findByProjectId(long projId);
	
	@Query(nativeQuery = true,value = "select name as projectName, start_date as startDate, end_date as endDate"
			+ "from project")
	public List<TimeChartData> getTimeData();
	
}
