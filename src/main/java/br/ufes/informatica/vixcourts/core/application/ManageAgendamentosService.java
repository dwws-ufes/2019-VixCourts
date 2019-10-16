package br.ufes.informatica.vixcourts.core.application;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.informatica.vixcourts.core.domain.Agendamento;
import br.ufes.informatica.vixcourts.core.domain.Quadra;

@Local
public interface ManageAgendamentosService extends CrudService<Agendamento> {
	
	List<Quadra> listQuadras();

}

