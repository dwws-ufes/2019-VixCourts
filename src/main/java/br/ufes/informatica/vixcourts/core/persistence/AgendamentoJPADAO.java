package br.ufes.informatica.vixcourts.core.persistence;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.informatica.vixcourts.core.domain.Agendamento;
import br.ufes.informatica.vixcourts.core.domain.Quadra;

@Stateless
public class AgendamentoJPADAO extends BaseJPADAO<Agendamento> implements AgendamentoDAO {
		private static final long serialVersionUID = 1L;
		
		@PersistenceContext
		private EntityManager entityManager;
		
		@Override
		protected EntityManager getEntityManager() {
				return entityManager;
		}

		@Override
		public List<Agendamento> verificarDisponibilidade(Date horaInicio, Date horaFim, long quadra_id) {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			
			String iniciodateString = format.format( horaInicio   );
			String fimdateString = format.format( horaFim   );
			
			
			Query q = entityManager.createNativeQuery("SELECT * FROM vixcourts.Agendamento WHERE \n" + 
					"vixcourts.Agendamento.quadra_id = "+ quadra_id +" AND \n"+
					"(\n" + 
					"vixcourts.Agendamento.horaInicio BETWEEN '"+iniciodateString+"' AND '"+fimdateString+"' OR\n" + 
					"vixcourts.Agendamento.horaFim BETWEEN '"+iniciodateString+"' AND '"+fimdateString+"' OR\n" + 
					"(vixcourts.Agendamento.horaInicio < '"+iniciodateString+"' AND vixcourts.Agendamento.horaFim > '"+fimdateString+"')\n" +
					")");
			
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + q.getResultList().toString());
			
			List<Agendamento> agendamentos_conflitantes = q.getResultList();
			
			return agendamentos_conflitantes;
		}

}