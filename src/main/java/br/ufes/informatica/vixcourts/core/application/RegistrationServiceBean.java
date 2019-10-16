package br.ufes.informatica.vixcourts.core.application;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.informatica.vixcourts.core.domain.Usuario;
import br.ufes.informatica.vixcourts.core.persistence.UsuarioDAO;

@Stateless
public class RegistrationServiceBean implements RegistrationService {
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;/** The DAO for User objects. */
	
	@EJB
	private UsuarioDAO userDAO;

	@Override
	public void registerUser(Usuario user) {
		try {
			// Encodes the user's password.
			user.setPassword(TextUtils.produceMd5Hash(user.getPassword()));

			// Register the last update date / creation date.
			Date now = new Date(System.currentTimeMillis());
			user.setLastUpdateDate(now);
			user.setCreationDate(now);
			System.out.println("user's last update date have been set as: {0}" + new Object[] { now });

			// Saves the user.
			System.out.println("Persisting user data...\n\t- Short name = {0}\n\t- Last update date = {1}" + new Object[] { user.getNome(), user.getLastUpdateDate() });
			userDAO.save(user);
			System.out.println("The useristrator has been saved: {0} ({1})" + new Object[] { user.getNome(), user.getEmail() });

		}
		catch (NoSuchAlgorithmException e) {
			// Logs and rethrows the exception for the controller to display the error to the user.
			System.out.println("Could not find MD5 algorithm for password encription!" + e);
		}
		catch (Exception e) {
			// Logs and rethrows the exception for the controller to display the error to the user.
			System.out.println("Exception during system installation!");
		}
		
	}
	
	

}
