package com.ikubinfo.suggestion.managedbeans;


import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;

import com.ikubinfo.suggestion.model.Client;
import com.ikubinfo.suggestion.model.Ride;
import com.ikubinfo.suggestion.service.ClientService;
import com.ikubinfo.suggestion.utils.SessionUtils;

@ManagedBean(name = "clientBean")
@SessionScoped
public class ClientBean implements Serializable {

	private static final long serialVersionUID = 5448895347701230045L;
	
	@ManagedProperty(value = "#{clientService}")
	private ClientService clientService;
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	
	private List<Ride> rideList;
	private Client client;
	private Ride ride;


	@PostConstruct
	public void notInit() {
		client = loginBean.getClient();

		search();
	}
	
	
	public void search() {
		HttpSession session = SessionUtils.getSession();
		session.getAttribute("username");
		
	
		
		ride = new Ride();
		this.ride.setClient_id(client.getClient_id());
		
		rideList = clientService.getAllRides(ride.getClient_id());
		System.out.println(rideList);
	}
	
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "/home.xhtml";
	}
	

	
    public void onDateSelect(SelectEvent<Date> event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
	
	public void orderRide(){
		clientService.orderRide(ride);
		notInit();
	}
	
	public void deleteRide(int ride_id){
		clientService.deleteRide(ride_id);
		notInit();

		
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


	public Ride getRide() {
		return ride;
	}


	public void setRide(Ride ride) {
		this.ride = ride;
	}


	public List<Ride> getRideList() {
		return rideList;
	}


	public void setRideList(List<Ride> rideList) {
		this.rideList = rideList;
	}


	public LoginBean getLoginBean() {
		return loginBean;
	}


	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	

}
