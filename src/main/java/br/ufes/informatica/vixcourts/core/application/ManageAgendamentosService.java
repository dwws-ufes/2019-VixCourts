package br.ufes.informatica.vixcourts.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.informatica.vixcourts.core.domain.Agendamento;

@Local
public interface ManageAgendamentosService extends CrudService<Agendamento> {

}

