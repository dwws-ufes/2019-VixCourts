package br.ufes.informatica.vixcourts.core.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.informatica.vixcourts.core.domain.Usuario;

@Local
public interface RegistrationService extends Serializable {
	
	/**
	 * TODO: document this method.
	 * 
	 * @param config
	 * @param admin
	 * @throws SystemInstallFailedException
	 */
	void registerUser(Usuario user);

}
