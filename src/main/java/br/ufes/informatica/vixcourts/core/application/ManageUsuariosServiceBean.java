package br.ufes.informatica.vixcourts.core.application;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.vixcourts.core.domain.Usuario;
import br.ufes.informatica.vixcourts.core.persistence.UsuarioDAO;

@Stateless @PermitAll
public class ManageUsuariosServiceBean extends CrudServiceBean<Usuario> implements ManageUsuariosService {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
	@Override
	public BaseDAO<Usuario> getDAO() {
			return usuarioDAO;
	}
	

}

