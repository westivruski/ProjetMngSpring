package com.ikubinfo.suggestion.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ikubinfo.suggestion.model.Project;


	
public class ProjectRowMapper implements RowMapper<Project> {
		
	@Override
		public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
		Project project = new Project();

		project.setId(rs.getInt("id"));
		project.setName(rs.getString("name"));
		project.setDescription(rs.getString("description"));
		project.setEmail(rs.getString("email"));
		project.setCreatedTime(rs.getTimestamp("createdtime"));
		project.setModifiedTime(rs.getTimestamp("modifiedtime"));
		
		return project;
		}
	}