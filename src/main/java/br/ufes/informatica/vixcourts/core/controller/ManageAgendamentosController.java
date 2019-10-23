package br.ufes.informatica.vixcourts.core.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudValidationError;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.informatica.vixcourts.core.application.ManageAgendamentosService;
import br.ufes.informatica.vixcourts.core.application.ManageQuadrasService;
import br.ufes.informatica.vixcourts.core.application.SessionInformation;
import br.ufes.informatica.vixcourts.core.domain.Agendamento;
import br.ufes.informatica.vixcourts.core.domain.Quadra;
import br.ufes.informatica.vixcourts.core.domain.Usuario;


@Named
@SessionScoped
public class ManageAgendamentosController extends CrudController<Agendamento> {

	private static final long serialVersionUID = 1L;

	private MapModel mapModel;

	private List<Quadra> quadrasList;
	
	private String quadra;
	
	private Date horaInicio;
	private Date horaFim;
	
	private Date dataInicio;
	private Date dataFim;

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public String getQuadra() {
		return quadra;
	}

	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	
	@EJB
	private SessionInformation sessionInformation;

	@EJB
	private ManageAgendamentosService manageAgendamentosService;

	@EJB
	private ManageQuadrasService manageQuadrasService;

	@Override
	protected CrudService<Agendamento> getCrudService() {
		return manageAgendamentosService;
	}

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageAgendamentosController.class.getCanonicalName());
	
	@Override
	public String save() {
		logger.log(Level.INFO, "Saving entity...");
		
		this.dataInicio.setHours(this.horaInicio.getHours());
		this.dataInicio.setMinutes(this.horaInicio.getMinutes());
		
		this.dataFim.setHours(this.horaFim.getHours());
		this.dataFim.setMinutes(this.horaFim.getMinutes());
		
		this.selectedEntity.setHoraInicio(this.dataInicio);
		this.selectedEntity.setHoraFim(this.dataFim);
		
		List<Agendamento> agendamentos_conflitantes = manageAgendamentosService.verificarDisponibilidade(this.selectedEntity.getHoraInicio(), this.selectedEntity.getHoraFim(), this.selectedEntity.getQuadra().getId());
		
		logger.log(Level.INFO, "", agendamentos_conflitantes.size());
		
		if(agendamentos_conflitantes.size() > 0) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sorry, the selected time is unavailable :(", ""));
			return null;
			
		}
		
		
		

		// Prepare the entity for saving.
		prepEntity();
		
		// Checks if we want to create or update the entity. Validates the operation first and stops in case of errors.
		try {
			if (selectedEntity.getId() == null) {
				getCrudService().validateCreate(selectedEntity);
				getCrudService().create(selectedEntity);
				addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".text.createSucceeded", summarizeSelectedEntity());
			}
			else {
				getCrudService().validateUpdate(selectedEntity);
				getCrudService().update(selectedEntity);
				addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".text.updateSucceeded", summarizeSelectedEntity());
			}
		}
		catch (CrudException crudException) {
			// Adds an error message to each validation error included in the exception.
			for (CrudValidationError error : crudException) {
				logger.log(Level.WARNING, "Exception while saving " + selectedEntity, crudException.getMessage());

				// Checks if the field name was specified. If it was, attach the message to the form field.
				if (error.getFieldName() != null) addFieldI18nMessage(getFieldName(error.getFieldName()), getBundleName(), FacesMessage.SEVERITY_ERROR, error.getMessageKey(), error.getMessageParams());
				else addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_ERROR, error.getMessageKey(), error.getMessageParams());
			}

			// Goes back to the same page, i.e., the form.
			return null;
		}

		// Goes back to the listing.
		return list();
	}
	
	public void buttonAction() {
        addMessage("Search Finished!");
        
        for (Quadra q : quadrasList) {
			LatLng coordinate = new LatLng(q.getLat(), q.getLng());
//			mapModel.addOverlay(new Marker(coordinate, q.getName(), mapModel, selectedMarker.toString()));
			mapModel.addOverlay(new Marker(coordinate, q.getName(), q, "http://maps.google.com/mapfiles/ms/icons/red-dot.png"));
			
//			   PrimeFaces.current().executeScript(
//		        		"var marker = new google.maps.Marker({\n" + 
//		        		"          position: {lat: "+ q.getLat() + ", lng:" + q.getLng() +"},\n" + 
//		        		"          icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png'\n" + 
//		        		"        });\n" + 
//		        		"        markers.push(marker);\n" +
//		        		"console.log('Adding marker');\n" +
//		        		"console.log(marker);\n" +
//		        		"console.log('Lista ficou assim: ');\n" +
//		        		"console.log(markers);\n" 
//		        		);
			
		}
        
//        PrimeFaces.current().executeScript(
//        		
//        "console.log('Click no search');\n"+
//  		"console.log(markers);\n"+
//  		
//  		"var gmap = PF('map').getMap();   \n"+   
//  		"console.log(gmap);\n"+
//
//  		"for (var i = 0; i < markers.length; i++) {\n"+
//  		"	markers[i].setMap(gmap);\n"+
//  		"markers[i].addListener('click', function() { \n" +
//  		   "console.log(this);\n" +
//  		   "console.log(document.getElementById('entitiesForm:quadraField_input'));\n" + 
//  		   "document.getElementById('entitiesForm:quadraField_input').selectedIndex = 1;\n"+
//  		   "this.setIcon('http://maps.google.com/mapfiles/ms/icons/yellow-dot.png')\n;" +
//  		"});\n"+
//  		"};\n"
//        );
    }
 
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
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
		
//		PrimeFaces.current().executeScript();
		
		mapModel = new DefaultMapModel();
		quadrasList = manageQuadrasService.getDAO().retrieveAll();

		for (Quadra q : quadrasList) {
			LatLng coordinate = new LatLng(q.getLat(), q.getLng());
//			mapModel.addOverlay(new Marker(coordinate, q.getName(), mapModel, selectedMarker.toString()));
			mapModel.addOverlay(new Marker(coordinate, q.getName(), q, "http://maps.google.com/mapfiles/ms/icons/red-dot.png"));
			
//			   PrimeFaces.current().executeScript(
//		        		"var marker = new google.maps.Marker({\n" + 
//		        		"          position: {lat: "+ q.getLat() + ", lng:" + q.getLng() +"},\n" + 
//		        		"          icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png'\n" + 
//		        		"        });\n" + 
//		        		"        markers.push(marker);\n" +
//		        		"console.log('Adding marker');\n" +
//		        		"console.log(marker);\n" +
//		        		"console.log('Lista ficou assim: ');\n" +
//		        		"console.log(markers);\n" 
//		        		);
			
		}
		
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		
        Marker marker = (Marker) event.getOverlay();
        
        Quadra q_selecionada = (Quadra) marker.getData();
//        
//        PrimeFaces.current().executeScript(
//        		"console.log('Printando a lista: ');\n" +
//        		"console.log(markers); \n"
//        		);
        
        this.quadra = q_selecionada.getName();
        
        marker.setIcon("http://maps.google.com/mapfiles/ms/icons/yellow-dot.png");
        
        this.selectedEntity.setQuadra(q_selecionada); 
     
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", q_selecionada.getName()));
        try {
			Faces.redirect("/vixcourts/core/manageAgendamentos/form.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	protected void initFilters() {
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
}
