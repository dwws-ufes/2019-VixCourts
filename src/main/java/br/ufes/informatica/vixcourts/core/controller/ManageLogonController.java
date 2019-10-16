package br.ufes.informatica.vixcourts.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import com.github.adminfaces.template.session.AdminSession;

import br.ufes.informatica.vixcourts.core.application.SessionInformation;
import br.ufes.informatica.vixcourts.core.domain.Usuario;
import br.ufes.informatica.vixcourts.core.exceptions.LoginFailedException;

import org.omnifaces.util.Faces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
@Specializes
public class ManageLogonController extends AdminSession implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageLogonController.class.getCanonicalName());
	
	@EJB
	private SessionInformation sessionInformation;
	
	private String email;
    private String password;
    private boolean remember;
    
    public void login() throws IOException {
    	try {
			// Uses the Login service to authenticate the user.
			logger.log(Level.FINEST, "User attempting login with email \"{0}\"...", email);
			sessionInformation.login(email, password);
		}
		catch (LoginFailedException e) {
			// System failure exception. Report a fatal error and ask the user to contact the administrators.
			logger.log(Level.INFO, "System failure during login. Email: \"" + email + "\"; reason: \"" + e.getReason() + "\"", e);							
		}
		// If everything is OK, redirect back to the home screen.
    	Faces.redirect("index.xhtml");

        
    }
    
    public void logout() throws IOException {
    	try {
    		// Uses the Login service to authenticate the user.
			logger.log(Level.FINEST, "User attempting login with email \"{0}\"...", email);
			sessionInformation.logout();
    	}
    	catch (Exception e) {
    		// System failure exception. Report a fatal error and ask the user to contact the administrators.
			logger.log(Level.INFO, "System failure during logout.", e);							
		}
    	Faces.redirect("login.xhtml");
    }
     
    @Override
    public boolean isLoggedIn() {

        return sessionInformation.getCurrentUser() != null;
    }
    
    /**
	 * Provides the current authenticated user.
	 * 
	 * @return The Academic object that represents the user that has been authenticated in this session.
	 */
	public Usuario getCurrentUser() {
		return sessionInformation.getCurrentUser();
	}

    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}
}
