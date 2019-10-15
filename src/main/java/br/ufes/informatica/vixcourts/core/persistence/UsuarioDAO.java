package br.ufes.informatica.vixcourts.core.persistence;

import java.util.List;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.informatica.vixcourts.core.domain.Usuario;

public interface UsuarioDAO extends BaseDAO<Usuario> {

	Usuario retrieveByEmail(String email)
			throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;

	List<Usuario> retrieveByEmailToList(String email);

}
