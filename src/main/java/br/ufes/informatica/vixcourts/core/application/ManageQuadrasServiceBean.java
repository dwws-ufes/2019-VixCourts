package br.ufes.informatica.vixcourts.core.application;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.vixcourts.core.domain.Quadra;
import br.ufes.informatica.vixcourts.core.persistence.QuadraDAO;

@Stateless @PermitAll
public class ManageQuadrasServiceBean extends CrudServiceBean<Quadra> implements ManageQuadrasService {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private QuadraDAO quadraDAO;
	
	@Override
	public BaseDAO<Quadra> getDAO() {
			return quadraDAO;
	}
	

}
