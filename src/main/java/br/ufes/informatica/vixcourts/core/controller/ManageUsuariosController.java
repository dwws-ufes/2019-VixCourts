package br.ufes.informatica.vixcourts.core.controller;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.informatica.vixcourts.core.application.ManageUsuariosService;
import br.ufes.informatica.vixcourts.core.domain.Usuario;
import br.ufes.informatica.vixcourts.core.exceptions.LoginFailedException;

@Named @SessionScoped
public class ManageUsuariosController extends CrudController<Usuario>{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private ManageUsuariosService manageUsuariosService;
	
	
	@Override
	protected CrudService<Usuario> getCrudService() {
		return manageUsuariosService;
	}
	
	@Override
	protected void initFilters() { }

}

