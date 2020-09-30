package com.ikubinfo.suggestion.managedbeans;

import java.io.IOException;
import java.io.Serializable;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DuplicateKeyException;

import com.ikubinfo.suggestion.model.Client;
import com.ikubinfo.suggestion.service.ClientService;
import com.ikubinfo.suggestion.utils.SessionUtils;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -2541864936808436426L;

	@ManagedProperty(value = "#{clientService}")
	private ClientService clientService;
	
	private Client client;


	
	@PostConstruct
	public void notInit() {
		client = new Client();
	}
	
	public void login() throws IOException{
		boolean valid = clientService.login(client.getUsername(),client.getPassword());
		
		if (valid) {
			
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", client.getUsername());
			client.setClient_id(clientService.selectClientId(client.getUsername()));
			clientService.loginButton();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Passowrd",
					"Please enter correct username and Password"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return;
		}
	}
	

	public void register() throws  IOException {
		clientService.register(client);
	}
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "/home.xhtml";
	}
	
	public void registerButton() throws IOException  {
		 clientService.registerButton();
	}


	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}



	
}
