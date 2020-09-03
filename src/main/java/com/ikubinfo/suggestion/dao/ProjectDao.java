package com.ikubinfo.suggestion.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ikubinfo.suggestion.mapper.ProjectRowMapper;
import com.ikubinfo.suggestion.model.Project;
import com.ikubinfo.suggestion.utils.DatabaseConnectionManager;


@Repository
public class ProjectDao {
	
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert insertTestTable;
	//private TestTableMappingQuery testTableQuery;

	private static final String SearchQUERY = "Select project.name,project.description,project.createdtime,project.modifiedtime,project.email from project where name LIKE LOWER(?)";
	private static final String SelectQUERY = "Select * From project ";
	private static final String DeleteQUERY = "delete from project where id = ? and createdtime < (NOW() - INTERVAL '00:30:00')";
	private static final String CreateQUERY = "insert into project(name,description,email,createdtime) values (?,?,?,?)";
	private static final String UpdateQUERY = "update project set name=? where id=?";
	private static final String FindQUERY = "select createdtime from project where id = ?";
	

	
	@Autowired
	public void init(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insertTestTable = new SimpleJdbcInsert(dataSource).withTableName("test_table");// .usingColumns("first_name",
																							// "last_name")
		//this.testTableQuery = new TestTableMappingQuery(dataSource);
	}
	

	
	 
	 public List<Project> listProjects() {
		 String SQL = "Select project.id, project.name,project.description,project.createdtime,project.modifiedtime,project.email from project";   

			return jdbcTemplate.query(SQL, new ProjectRowMapper());
		}
	 
	 public List<Project> searchProjects(String name) {
		 String SQL = "Select project.name,project.description,project.createdtime,project.modifiedtime,project.email from project where name LIKE LOWER :name";   
		 MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		   namedParameters.addValue("name", name);
			return namedParameterJdbcTemplate.query
					(SQL,
					namedParameters,
					 new ProjectRowMapper());
		}

	 
	   public void insertP(Project project) {
	        String sql = CreateQUERY;
	        System.out.println("method starts");
	        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	        System.out.println("adf "+ project.getName());
	        jdbcTemplate.update("insert into project(name,description,email,createdtime) values (?,?,?,?)", project.getName(), project.getDescription(), project.getEmail(), date);
	        
	       
	    }
	    
	   public void deleteP(int projectId) {
	        String sql = DeleteQUERY;
	       
	        jdbcTemplate.update(sql, projectId);
	    }
	   
	   public void updateP(Project project){
		   String SQL = UpdateQUERY;
		   java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		   MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		   namedParameters.addValue("name", project.getName());
		   namedParameters.addValue("modifiedtime", date);
		   namedParameters.addValue("id", project.getId());
		   
		  // jdbcTemplate.update(SQL, name, projectId); 
		   
		   this.namedParameterJdbcTemplate.update("update project set name = :name, modifiedtime = :modifiedtime  where id = :id",
					namedParameters);
	   }

	    
	

	//read
	 public List <Project> getAlls(String name) {
			List<Project> projects = new ArrayList<>();
			try (Connection connection = DatabaseConnectionManager.getConnection();
					PreparedStatement statement = connection.prepareStatement(SearchQUERY);) {
				
								
				statement.setString(1, name+"%");
			/*	statement.setString(2, description);
				statement.setTimestamp(2, createdtime);*/
			
				
				System.out.println(statement);
				ResultSet result = statement.executeQuery();

				while (result.next()) {
					Project project = new Project();
				
					project.setName(result.getString("name"));
					project.setDescription(result.getString("description"));
					project.setCreatedTime(result.getTimestamp("createdtime"));
					project.setEmail(result.getString("email"));
					project.setModifiedTime(result.getTimestamp("modifiedtime"));
				
					projects.add(project);
					System.out.println(project);
				}

			} catch (Exception ex) {
				System.out.println("Something went wrong ");
				ex.printStackTrace();
			}
			return projects;
		}
	    
	//find by id
	    public Project editCell(int restaurantId) {
	        Project selectRecord = new Project();
	        System.out.println("editRestaurantRecordInDB() : Restaurant Id: " + restaurantId);
	 
	        try (Connection connection = DatabaseConnectionManager.getConnection();      
	            	PreparedStatement statement =connection.prepareStatement(FindQUERY);){
	        	statement.setInt(1, restaurantId);
	           ResultSet rs = statement.executeQuery();
	            if(rs != null){
	            rs.next();  
	            selectRecord.setName(rs.getString("name"));  
	            selectRecord.setDescription(rs.getString("address"));  
	            selectRecord.setCreatedTime(rs.getTimestamp("Cdate"));    
	            selectRecord.setModifiedTime(rs.getTimestamp("Mdate"));
	            }
	            
	        } catch(Exception sqlException) {
	            sqlException.printStackTrace();
	        }
			return selectRecord;
	    }
	 
	 
	 
	 public List <Project> getAll() {
			List<Project> projects = new ArrayList<>();
			try (Connection connection = DatabaseConnectionManager.getConnection();
					PreparedStatement statement = connection.prepareStatement(SelectQUERY);) {
				
				System.out.println(statement);
				ResultSet result = statement.executeQuery();

				while (result.next()) {
					Project project = new Project();
					project.setId(result.getInt("id"));
					project.setName(result.getString("name"));
					project.setDescription(result.getString("description"));
					project.setCreatedTime(result.getTimestamp("createdtime"));
					project.setEmail(result.getString("email"));
					project.setModifiedTime(result.getTimestamp("modifiedtime"));
				
					projects.add(project);
					System.out.println(project);
				}

			} catch (Exception ex) {
				System.out.println("Something went wrong ");
				ex.printStackTrace();
			}
			return projects;
		}
	    

    

    
   
//delete
    public void  delete(int projectId){
        
    	System.out.println("deleteRestaurantRecordInDB() : Restaurant Id: " + projectId);
        
    	try (Connection connection = DatabaseConnectionManager.getConnection();      
            	PreparedStatement statement =connection.prepareStatement(DeleteQUERY);){
    		statement.setInt(1, projectId);
    		statement.executeUpdate();  
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }  
    }
    

    
    //find by id
    public Project selectFromId(int restaurantId) {
        Project selectRecord = new Project();
        System.out.println("editRestaurantRecordInDB() : Restaurant Id: " + restaurantId);
 
        try (Connection connection = DatabaseConnectionManager.getConnection();      
            	PreparedStatement statement =connection.prepareStatement(FindQUERY);){
        	
        	statement.setInt(1, restaurantId);
        	
           ResultSet rs = statement.executeQuery();
            if(rs != null){
            rs.next();  
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            
            selectRecord.setCreatedTime(date);    
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
		return selectRecord;
    }

 
    //update
    public void update (Project project) {
    	 try (Connection connection = DatabaseConnectionManager.getConnection();      
             	PreparedStatement statement =connection.prepareStatement(UpdateQUERY);){
    		 java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
    		 
    		 statement.setString(1,project.getName());                
    		 statement.setTimestamp(2, date);
    		 statement.setString(3,project.getEmail());  
    		 statement.setInt(4, project.getId());
    		 statement.executeUpdate();    
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
       
    }
    
    


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
 
  
   
}

