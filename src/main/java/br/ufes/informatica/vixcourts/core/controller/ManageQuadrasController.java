package br.ufes.informatica.vixcourts.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

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

	@Override
	protected void initFilters() { }
}
