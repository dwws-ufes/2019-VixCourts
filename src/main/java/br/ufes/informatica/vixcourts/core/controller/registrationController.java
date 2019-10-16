package br.ufes.informatica.vixcourts.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.vixcourts.core.application.ManageUsuariosService;
import br.ufes.informatica.vixcourts.core.application.RegistrationService;
import br.ufes.informatica.vixcourts.core.domain.Usuario;

@Named
@ConversationScoped
public class registrationController extends JSFController {
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	

	/** Input: the administrator being registered during the installation. */
	private Usuario user = new Usuario();
	
	/** Input: the repeated password for the admininstrator registration. */
	private String repeatPassword;
	
	@EJB
	private RegistrationService registrationService;
	
	/** Getter for repeatPassword. */
	public String getRepeatPassword() {
		return repeatPassword;
	}

	/** Setter for repeatPassword. */
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	/** Getter for admin. */
	public Usuario getUser() {
		return user;
	}
	
	/**
	 * Checks if both password fields have the same value.
	 * 
	 * This method is intended to be used with AJAX.
	 */
	public void ajaxCheckPasswords() {
		checkPasswords();
	}
	
	/**
	 * Checks if the contents of the password fields match.
	 * 
	 * @return <code>true</code> if the passwords match, <code>false</code> otherwise.
	 */
	private boolean checkPasswords() {
		if (((repeatPassword != null) && (!repeatPassword.equals(user.getPassword()))) || ((repeatPassword == null) && (user.getPassword() != null))) {
			System.out.println("Password and repeated password are not the same");
			addGlobalI18nMessage("msgsCore", FacesMessage.SEVERITY_WARN, "installSystem.error.passwordsDontMatch.summary", "installSystem.error.passwordsDontMatch.detail");
			return false;
		}
		return true;
	}
	
	/**
	 * Registers the administrator as one of the steps of system installation and moves to the next step.
	 * 
	 * @return The path to the web page that shows the next step in the installation process.
	 */
	public void registerUser() {
		System.out.println("Received input data:\n\t- admin.name = {0}\n\t- admin.email = {1}" + new Object[] { user.getNome(), user.getEmail() });

		// Check if passwords don't match. Add an error in that case.
		if (!checkPasswords()) return;
		
		// Create User
		try {
			registrationService.registerUser(user);
			Faces.getExternalContext().getFlash().setKeepMessages(true);
	        Faces.redirect("index.xhtml");
		}
		catch (Exception e) {
			System.out.println("System installation threw exception" +  e);
			addGlobalI18nMessage("msgsCore", FacesMessage.SEVERITY_FATAL, "installSystem.error.installFailed.summary", "installSystem.error.installFailed.detail");
			return;
		}
	}
	
	
	
	

}

