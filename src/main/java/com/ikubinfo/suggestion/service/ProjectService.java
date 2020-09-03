package com.ikubinfo.suggestion.service;

import java.io.IOException;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikubinfo.suggestion.dao.ProjectDao;
import com.ikubinfo.suggestion.model.Project;


@Service
public class ProjectService {
	@Autowired
	private ProjectDao projectDao ;
	
	/*public ProjectService(){
		projectDao= new ProjectDao();
	}*/
	
	public List<Project> getAll( ) {
		return projectDao.getAll();
	}
	
	//search spring jdbc
	public List <Project> searchProjects(String name){
		return projectDao.searchProjects(name);
	}
	
	//trying out
	public List <Project> listProjects(){
		return projectDao.listProjects();
	}
	//insert
	public void insertP (Project project){
		projectDao.insertP(project);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("main.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteP (int projectId){
		projectDao.deleteP(projectId);
	}
	
	public void updateP (Project project){
		projectDao.updateP(project);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("main.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List<Project> getAlls(String name ) {
		return projectDao.getAlls(name);
	}
	
	public void findById (int project){
		 projectDao.selectFromId(project);
	}
	

	public void delete (int restaurantId){
		//Project prj = 	projectDao.selectFromId(restaurantId);	
		projectDao.delete(restaurantId);
			System.out.println("success");
	}
	
	
	
	public void update (Project project){
		Project prj = 	projectDao.selectFromId(project.getId());
		if(prj !=null){
			projectDao.update(project);
		}
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("main.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
