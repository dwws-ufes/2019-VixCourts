package br.inf.ufes.vixcourts.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class AgendamentoDAOJPA extends BaseJPADAO<AgendamentoDAOJPA> implements AgendamentoDAO{
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
    @PersistenceContext
    private EntityManager entityManager;

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
    
    
	/** TODO: generated by FrameWeb. Should be documented. */
    @Override
	public List<Agendamento> buscarPorModalidade(Modalidade modalidade) {
		// FIXME: auto-generated method stub
		return null;
	}
	
	/** TODO: generated by FrameWeb. Should be documented. */
    @Override
	public List<Agendamento> buscarPorEndereco(Endereco endereco) {
		// FIXME: auto-generated method stub
		return null;
	}
	
	/** TODO: generated by FrameWeb. Should be documented. */
    @Override
	public List<Agendamento> buscarPorModalidadeEndereco(Modalidade modalidade, Endereco endereco) {
		// FIXME: auto-generated method stub
		return null;
	}
	

}