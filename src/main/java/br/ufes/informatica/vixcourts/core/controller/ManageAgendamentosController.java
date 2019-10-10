package br.ufes.informatica.vixcourts.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.informatica.vixcourts.core.application.ManageAgendamentosService;
import br.ufes.informatica.vixcourts.core.domain.Agendamento;

@Named @SessionScoped
public class ManageAgendamentosController extends CrudController<Agendamento>{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private ManageAgendamentosService manageAgendamentosService;
	
	@Override
	protected CrudService<Agendamento> getCrudService() {
		return manageAgendamentosService;
	}

	@Override
	protected void initFilters() { }
}
