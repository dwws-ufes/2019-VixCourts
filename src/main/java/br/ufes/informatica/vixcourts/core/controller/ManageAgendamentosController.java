package br.ufes.informatica.vixcourts.core.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.informatica.vixcourts.core.application.ManageAgendamentosService;
import br.ufes.informatica.vixcourts.core.domain.Agendamento;
import br.ufes.informatica.vixcourts.core.domain.Quadra;

@Named @SessionScoped
public class ManageAgendamentosController extends CrudController<Agendamento>{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private ManageAgendamentosService manageAgendamentosService;

	@Override
	protected CrudService<Agendamento> getCrudService() {
		return manageAgendamentosService;
	}
	
	public List<Quadra> getQuadras() {
		return manageAgendamentosService.listQuadras();
	}

	@Override
	protected void initFilters() { }
}
