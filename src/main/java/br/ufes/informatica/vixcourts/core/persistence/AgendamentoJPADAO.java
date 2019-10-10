package br.ufes.informatica.vixcourts.core.persistence;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.informatica.vixcourts.core.domain.Agendamento;

@Stateless
public class AgendamentoJPADAO extends BaseJPADAO<Agendamento> implements AgendamentoDAO {
		private static final long serialVersionUID = 1L;
		
		@PersistenceContext
		private EntityManager entityManager;
		
		@Override
		protected EntityManager getEntityManager() {
				return entityManager;
		}

}