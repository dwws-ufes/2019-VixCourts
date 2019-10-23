package br.ufes.informatica.vixcourts.core.application;

import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.vixcourts.core.domain.Agendamento;
import br.ufes.informatica.vixcourts.core.domain.Quadra;
import br.ufes.informatica.vixcourts.core.persistence.AgendamentoDAO;
import br.ufes.informatica.vixcourts.core.persistence.QuadraDAO;

@Stateless @PermitAll
public class ManageAgendamentosServiceBean extends CrudServiceBean<Agendamento> implements ManageAgendamentosService {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AgendamentoDAO agendamentoDAO;
	
	@EJB
	private QuadraDAO quadraDAO;
	
	@Override
	public BaseDAO<Agendamento> getDAO() {
			return agendamentoDAO;
	}
	
	@Override
	public List<Quadra> listQuadras() {
		return quadraDAO.retrieveAll();
	}

	@Override
	public List<Agendamento> verificarDisponibilidade(Date horaInicio, Date horaFim, long quadra_id) {
		
		return agendamentoDAO.verificarDisponibilidade(horaInicio, horaFim, quadra_id);
		
	}

	

}
