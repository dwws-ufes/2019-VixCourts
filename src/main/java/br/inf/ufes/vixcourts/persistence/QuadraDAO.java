package br.inf.ufes.vixcourts.persistence;

import javax.ejb.Local;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface QuadraDAO extends BaseDAO<Quadra> {
	
	/** TODO: generated by FrameWeb. Should be documented. */
	public void buscarPorDisponibilidade();
	
}