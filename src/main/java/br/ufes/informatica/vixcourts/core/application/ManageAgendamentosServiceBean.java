package br.ufes.informatica.vixcourts.core.application;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.vixcourts.core.domain.Agendamento;
import br.ufes.informatica.vixcourts.core.persistence.AgendamentoDAO;

@Stateless @PermitAll
public class ManageAgendamentosServiceBean extends CrudServiceBean<Agendamento> implements ManageAgendamentosService {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AgendamentoDAO agendamentoDAO;
	
	@Override
	public BaseDAO<Agendamento> getDAO() {
			return agendamentoDAO;
	}
	

}
