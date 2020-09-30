package com.ikubinfo.suggestion.model;

public class Roles {
	
	private int rolesid;
	private String name;
	
	public Roles() {
		super();
		
	}
	
	public Roles(int rolesid, String name) {
		
		this.rolesid = rolesid;
		this.name = name;
	}
	public int getRolesid() {
		return rolesid;
	}
	public void setRolesid(int rolesid) {
		this.rolesid = rolesid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
