package com.ikubinfo.suggestion.managedbeans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.ikubinfo.suggestion.model.Project;
import com.ikubinfo.suggestion.service.ProjectService;




@ManagedBean(name = "projectBean")
@ViewScoped
public class ProjectBean implements Serializable {

	private static final long serialVersionUID = 8841400873178515754L;

	private String name;
	private String description;
	private Timestamp createdtime;
	
	private List<Project> projects;
	

	
	@ManagedProperty(value = "#{projectService}")
	private ProjectService projectService;
	
	private Project project ;
	
	 

	@PostConstruct
	public void notInit() {
		project = new Project();
		//projects = projectService.getAll();
		//listprojects() errorr
		projects = projectService.listProjects();
	}

	
	//spring JDBC read all 
	public void searchAll() {
		projects = projectService.listProjects();
	}
	//spring JDBC insert a record 
	public void insertP(){
		projectService.insertP(project);
		notInit();
	}
	//spring JDBC delete a record 
	public void deleteP(int id){
		projectService.deleteP(id);
		notInit();
	}
	//spring JDBC update record name by id   , not really working
	public void updateP(Project project){
		projectService.updateP(project);
		notInit();
	}

	public void search(){
		projects = projectService.searchProjects(name); 
	}

	public List<Project> getAllProjects() {
		return projects;
	}
	

	
	public void find (int id){
		projectService.findById(id);
		notInit();
	}
	
	public void delete (int id){
		try{
			projectService.delete(id);
			notInit();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succesfully Deleted!", "Table Updated"));
		}catch (RuntimeException e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
	}
		
		
	}
	public void update (Project project){
		projectService.updateP(project);
		notInit();
	}
	
	
	 public void onRowEdit(RowEditEvent event) {
		 
		    Project us= (Project) event.getObject();
		    
		   
		    System.out.println("Project edit"+us);
		    projectService.updateP(us);
		 
	        FacesMessage msg = new FacesMessage("Project edited successfully!");
	        FacesContext.getCurrentInstance().addMessage(null, msg);

	    }
	     
	    public void onRowCancel(RowEditEvent event) {
	        FacesMessage msg = new FacesMessage("Row edit canceled");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	
	
	
	   
	
	
	
	
	
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public ProjectService getProjectService() {
		return projectService;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(Timestamp createdTime) {
		this.createdtime = createdTime;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	

	
}
