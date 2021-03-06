package br.ufes.informatica.vixcourts.core.application;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.informatica.vixcourts.core.domain.Usuario;
import br.ufes.informatica.vixcourts.core.exceptions.LoginFailedException;
import br.ufes.informatica.vixcourts.core.persistence.UsuarioDAO;

/**
 * Stateful session bean implementing the session information component. See the implemented interface documentation for
 * details.
 * 
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 * @see br.org.feees.sigme.core.application.SessionInformation
 */
@SessionScoped
@Stateful
public class SessionInformationService implements SessionInformation {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(SessionInformationService.class.getCanonicalName());

	/** The DAO for Usuario objects. */
	@EJB
	private UsuarioDAO UsuarioDAO;

	/** The current Usuario logged in. */
	private Usuario currentUser;

	/** @see br.org.feees.sigme.core.application.SessionInformation#getCurrentUser() */
	@Override
	public Usuario getCurrentUser() {
		return currentUser;
	}
	
	@Override
	public void logout() {
		currentUser = null;
	}

	/** @see br.org.feees.sigme.core.application.SessionInformation#login(java.lang.String, java.lang.String) */
	@Override
	public void login(String username, String password) throws LoginFailedException {
		try {
			
			String md5pwd = TextUtils.produceMd5Hash(password);
			System.out.println(md5pwd);
			// Obtains the Usuario given the e-mail address (that serves as username).
			logger.log(Level.FINER, "Authenticating Usuario with username \"{0}\"...", username);
			Usuario Usuario = UsuarioDAO.retrieveByEmail(username);

			// Checks if the passwords match.
			String pwd = Usuario.getPassword();
			if ((pwd != null) && (pwd.equals(md5pwd))) {
				logger.log(Level.FINEST, "Passwords match for Usuario \"{0}\".", username);

				// Login successful. Registers the current Usuario in the session.
				logger.log(Level.FINE, "Usuario \"{0}\" successfully logged in.", username);
				currentUser = Usuario;
				pwd = null;

				// Registers the Usuario login.
				Date now = new Date(System.currentTimeMillis());
				logger.log(Level.FINER, "Setting last login date for Usuario with username \"{0}\" as \"{1}\"...", new Object[] { currentUser.getEmail(), now });
				currentUser.setLastLoginDate(now);
				UsuarioDAO.save(currentUser);
			}
			else {
				// Passwords don't match.
				logger.log(Level.INFO, "Usuario \"{0}\" not logged in: password didn't match.", username);
				throw new LoginFailedException(LoginFailedException.LoginFailedReason.INCORRECT_PASSWORD);
			}
		}
		catch (PersistentObjectNotFoundException e) {
			// No Usuario was found with the given username.
			logger.log(Level.INFO, "Usuario \"{0}\" not logged in: no registered user found with given username.", username);
			throw new LoginFailedException(e, LoginFailedException.LoginFailedReason.UNKNOWN_USERNAME);
		}
		catch (MultiplePersistentObjectsFoundException e) {
			// Multiple Usuarios were found with the same username.
			logger.log(Level.WARNING, "Usuario \"{0}\" not logged in: there are more than one registered user with the given username.", username);
			throw new LoginFailedException(e, LoginFailedException.LoginFailedReason.MULTIPLE_USERS);
		}
		catch (EJBTransactionRolledbackException e) {
			// Unknown original cause. Throw the EJB exception.
			logger.log(Level.WARNING, "Usuario \"" + username + "\" not logged in: unknown cause.", e);
			throw e;
		}
		catch (NoSuchAlgorithmException e) {
			// No MD5 hash generation algorithm found by the JVM.
			logger.log(Level.SEVERE, "Logging in Usuario \"" + username + "\" triggered an exception during MD5 hash generation.", e);
			throw new LoginFailedException(LoginFailedException.LoginFailedReason.MD5_ERROR);
		}
	}
}
