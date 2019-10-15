package br.ufes.informatica.vixcourts.core.persistence;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.informatica.vixcourts.core.domain.Usuario;
import br.ufes.informatica.vixcourts.core.domain.Usuario_;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class UsuarioJPADAO extends BaseJPADAO<Usuario> implements UsuarioDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(UsuarioJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/** @see br.ufes.informatica.pooptime.core.persistence.UsuarioDAO#retrieveByEmail(java.lang.String) */
	@Override
	public Usuario retrieveByEmail(String email) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Retrieving the Usuario whose e-mail is \"{0}\"...", email);

		// Constructs the query over the Usuario class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		Root<Usuario> root = cq.from(Usuario.class);

		// Filters the query with the email.
		cq.where(cb.equal(root.get(Usuario_.email), email));
		Usuario result = executeSingleResultQuery(cq, email);
		logger.log(Level.INFO, "Retrieve Usuario by the email \"{0}\" returned \"{1}\"", new Object[] { email, result });
		return result;
	}

	@Override
	public List<Usuario> retrieveByEmailToList(String email) {
		
		logger.log(Level.FINE, "Retrieving the Usuario whose e-mail is \"{0}\"...", email);
		
		// Constructs the query over the Usuario class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		Root<Usuario> root = cq.from(Usuario.class);

		// Filters the query with the email.
		cq.where(cb.like(cb.lower(root.get(Usuario_.email)), "%" + email.toLowerCase() + "%"));
		List<Usuario> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Retrieve Usuario by the email \"{0}\" returned \"{1}\"", new Object[] { email, result });
		return entityManager.createQuery(cq).getResultList();
	}
}
