package br.ufes.informatica.vixcourts.core.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.informatica.vixcourts.core.application.ManageAgendamentosService;
import br.ufes.informatica.vixcourts.core.application.ManageQuadrasService;
import br.ufes.informatica.vixcourts.core.domain.Agendamento;
import br.ufes.informatica.vixcourts.core.domain.Quadra;

@Named
@SessionScoped
public class ManageAgendamentosController extends CrudController<Agendamento> {

	private static final long serialVersionUID = 1L;

	private MapModel mapModel;

	private List<Quadra> quadrasList;

	@EJB
	private ManageAgendamentosService manageAgendamentosService;

	@EJB
	private ManageQuadrasService manageQuadrasService;

	@Override
	protected CrudService<Agendamento> getCrudService() {
		return manageAgendamentosService;
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}

	public List<Quadra> getQuadrasList() {
		return quadrasList;
	}

	public void setQuadrasList(List<Quadra> quadrasList) {
		this.quadrasList = quadrasList;
	}

	public ManageAgendamentosService getManageAgendamentosService() {
		return manageAgendamentosService;
	}

	public void setManageAgendamentosService(ManageAgendamentosService manageAgendamentosService) {
		this.manageAgendamentosService = manageAgendamentosService;
	}

	public ManageQuadrasService getManageQuadrasService() {
		return manageQuadrasService;
	}

	public void setManageQuadrasService(ManageQuadrasService manageQuadrasService) {
		this.manageQuadrasService = manageQuadrasService;
	}

	public List<Quadra> getQuadras() {
		return manageAgendamentosService.listQuadras();
	}
	
	@PostConstruct
	public void init() {
		
//		PrimeFaces.current().executeScript("alert('This onload script is added from backing bean.')");
		
		mapModel = new DefaultMapModel();
		quadrasList = manageQuadrasService.getDAO().retrieveAll();

		for (Quadra q : quadrasList) {
			LatLng coordinate = new LatLng(q.getLat(), q.getLng());
//			mapModel.addOverlay(new Marker(coordinate, q.getName(), mapModel, selectedMarker.toString()));
			mapModel.addOverlay(new Marker(coordinate, q.getName(), q, "http://maps.google.com/mapfiles/ms/icons/red-dot.png"));
			
//			   PrimeFaces.current().executeScript(
//		        		"var marker = new google.maps.Marker({\n" + 
//		        		"          position: {lat: "+ q.getLat() + ", lng:" + q.getLng() +"},\n" + 
//		        		"          icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'\n" + 
//		        		"        });\n" + 
//		        		"        markers.push(marker);\n" +
//		        		"console.log(marker);"
//		        		);
			
		}
		
		
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		
        Marker marker = (Marker) event.getOverlay();
        
        Quadra q_selecionada = (Quadra) marker.getData();
        
        marker.setIcon("http://maps.google.com/mapfiles/ms/icons/yellow-dot.png");
        
        
     
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", q_selecionada.getName()));
    }

	@Override
	protected void initFilters() {
	}
}
