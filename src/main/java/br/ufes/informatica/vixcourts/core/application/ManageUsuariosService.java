package br.ufes.informatica.vixcourts.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.informatica.vixcourts.core.domain.Usuario;

@Local
public interface ManageUsuariosService extends CrudService<Usuario> {

}

