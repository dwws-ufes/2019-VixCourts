package br.inf.ufes.vixcourts.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class quadraServiceImpl implements quadraServiceImpl {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;
	
	
	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private QuadraDAO quadraDAO;
	
	
	

	
	
	
	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public void buscarQuadrasDisponiveis(DateTime HoraInicio, DateTime HoraFim, Endereco Local, TipoQuadra TipoQuadra) {
		// FIXME: auto-generated method stub
		return;
	}
	
}