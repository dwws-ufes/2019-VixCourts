package br.ufes.informatica.vixcourts.core.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.informatica.vixcourts.core.application.ManageQuadrasService;
import br.ufes.informatica.vixcourts.core.domain.Quadra;

@Named @SessionScoped
public class ManageQuadrasController extends CrudController<Quadra>{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private ManageQuadrasService manageQuadrasService;
	
	@Override
	protected CrudService<Quadra> getCrudService() {
		return manageQuadrasService;
	}
	
	private MapModel emptyModel;
	
	private double lat;
	
	private double lng;
	
	public double getLat() {
        return lat;
    }
  
    public void setLat(double lat) {
        this.lat = lat;
    }
  
    public double getLng() {
        return lng;
    }
  
    public void setLng(double lng) {
        this.lng = lng;
    }
	
	@PostConstruct
    public void init() {
		emptyModel = new DefaultMapModel();
    }
	
	public MapModel getEmptyModel() {
        return emptyModel;
    }
	
	public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
        
        this.selectedEntity.setLat(latlng.getLat());
        this.selectedEntity.setLng(latlng.getLng());
        
        Marker marker = new Marker(new LatLng(lat, lng));
        emptyModel.addOverlay(marker);
          
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
    }
      
    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	@Override
	protected void initFilters() { }
}
