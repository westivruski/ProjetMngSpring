package com.ikubinfo.suggestion.service;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.ikubinfo.suggestion.dao.ClientDao;
import com.ikubinfo.suggestion.model.Client;
import com.ikubinfo.suggestion.model.Ride;

@Service
public class ClientService {

	@Autowired
	private ClientDao clientDao;
	
	// Client methods 
		 public boolean login(String username, String password){
			 
			return clientDao.login(username, password);
		}
		 
		public void register(Client client) throws IOException {
		
			try {
				clientDao.registerClient(client);
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
				FacesContext.getCurrentInstance().addMessage( "message-test", new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration succesful", "Welcome! "));
			} catch (DuplicateKeyException e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration erorr", "Chosen username is alredy taken! "));
			}
		}
			
		public int selectClientId(String name){
			return clientDao.selectClientId(name);
		}
		 
		public List<Ride> searchRideById(int client_id) {
			return clientDao.searchRideById(client_id);
		}
		 
		public void orderRide(Ride ride ){
			clientDao.orderRide( ride);
		}
		
		public List<Ride> getAllRides(int client_id) {
			return clientDao.getAllRides(client_id);
		}


		public void deleteRide(int ride_id){
			clientDao.deleteRide(ride_id);
			FacesContext.getCurrentInstance().addMessage( "ride:message-marker", new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration erorr", "Chosen username is alredy taken! "));
		}
		
		

		public ClientDao getClientDao() {
			return clientDao;
		}

		public void setClientDao(ClientDao clientDao) {
			this.clientDao = clientDao;
		}

		public void validate(Client userModel) throws IOException{
			clientDao.validateUser(userModel);
		}

		 public void loginButton() throws IOException   {
			 FacesContext.getCurrentInstance().getExternalContext().redirect("/TheAPP/client.xhtml");
	    }
		  
		 public void registerButton() throws IOException {  
			 FacesContext.getCurrentInstance().getExternalContext().redirect("register.xhtml");
	    }
		
}
