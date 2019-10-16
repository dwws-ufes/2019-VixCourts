package br.ufes.informatica.vixcourts.core.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.informatica.vixcourts.core.domain.Quadra;
import br.ufes.informatica.vixcourts.core.domain.Quadra_;

@Stateless
public class QuadraJPADAO extends BaseJPADAO<Quadra> implements QuadraDAO {
		private static final long serialVersionUID = 1L;
		
		private static final Logger logger = Logger.getLogger(QuadraJPADAO.class.getCanonicalName());
		
		@PersistenceContext
		private EntityManager entityManager;
		
		@Override
		protected EntityManager getEntityManager() {
				return entityManager;
		}

		/** @see br.ufes.informatica.pooptime.core.persistence.UserDAO#retrieveByEmail(java.lang.String) */
		@Override
		public Quadra retrieveByName(String name) {
			logger.log(Level.FINE, "Retrieving the court whose name is \"{0}\"...", name);
			
			// Constructs the query over the Quadra class.
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Quadra> cq = cb.createQuery(Quadra.class);
			Root<Quadra> root = cq.from(Quadra.class);

			// Filters the query with the email.
			cq.where(cb.equal(root.get(Quadra_.name), name));
			logger.log(Level.INFO, "Retrieve court by the name \"{0}\" returned \"{1}\"", new Object[] { name });
			return entityManager.createQuery(cq).getSingleResult();
		}
}
