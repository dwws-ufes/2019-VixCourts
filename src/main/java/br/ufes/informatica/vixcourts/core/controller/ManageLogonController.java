package br.ufes.informatica.vixcourts.core.controller;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import com.github.adminfaces.template.session.AdminSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
@Specializes
public class ManageLogonController extends AdminSession implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String currentUser;
	private String email;
    private String password;
    private boolean remember;
    
    public void login() throws IOException {
    	System.out.println("Logou?");
        currentUser = email;
        addDetailMessage("Logged in successfully as <b>" + email + "</b>");
        Faces.getExternalContext().getFlash().setKeepMessages(true);
        Faces.redirect("index.xhtml");
    }
    
    public static void addDetailMessage(String message) {
        addDetailMessage(message, null);
    }

    public static void addDetailMessage(String message, FacesMessage.Severity severity) {

        FacesMessage facesMessage = Messages.create("").detail(message).get();
        if (severity != null && severity != FacesMessage.SEVERITY_INFO) {
            facesMessage.setSeverity(severity);
        }
        Messages.add(null, facesMessage);
    }

    @Override
    public boolean isLoggedIn() {

        return currentUser != null;
    }
    
    public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
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
