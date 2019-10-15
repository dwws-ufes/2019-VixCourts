package br.ufes.informatica.vixcourts.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;


import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.informatica.vixcourts.core.application.ManageUsuariosService;
import br.ufes.informatica.vixcourts.core.domain.Usuario;

@Named @SessionScoped
public class ManageUsuariosController extends CrudController<Usuario>{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private ManageUsuariosService manageUsuariosService;
	
	private String passwordConfirmation, entityPassword;
	
	public String getEntityPassword() {
		return entityPassword;
	}

	public void setEntityPassword(String entityPassword) {
		this.entityPassword = entityPassword;
	}
	
	public String getPasswordConfirmation() {
		return this.passwordConfirmation;
	}
	
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	@Override
	protected CrudService<Usuario> getCrudService() {
		return manageUsuariosService;
	}
	
	/**
	 * Checks if both password fields have the same value.
	 * 
	 * This method is intended to be used with AJAX.
	 */
	public void ajaxCheckPasswords() {
		System.out.println(this.passwordConfirmation  + "===" + this.entityPassword);
		checkPasswords();
	}

	/**
	 * Checks if the contents of the password fields match.
	 * 
	 * @return <code>true</code> if the passwords match, <code>false</code> otherwise.
	 */
	private boolean checkPasswords() {
		if (((passwordConfirmation != null) && (!passwordConfirmation.equals(entityPassword))) || ((passwordConfirmation == null) && (entityPassword != null))) {
			addGlobalI18nMessage("msgsCore", FacesMessage.SEVERITY_WARN, "changePassword.error.passwordsDontMatch.summary", "changePassword.error.passwordsDontMatch.detail");
			return false;
		}
		this.selectedEntity.setPassword(entityPassword);
		return true;
	}
	
	@Override
	protected void initFilters() { }

}

