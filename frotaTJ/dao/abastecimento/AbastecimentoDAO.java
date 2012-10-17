package dao.abastecimento;
// default package


import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fean.tjsc.dao.utils.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for
 * Abastecimento entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .Abastecimento
 * @author MyEclipse Persistence Tools
 */

public class AbastecimentoDAO implements IAbastecimentoDAO {
	// property constants
	public static final String KM_ODOMETRO = "kmOdometro";
	
	private static AbastecimentoDAO instance = new AbastecimentoDAO();
	private AbastecimentoDAO(){}
	public static AbastecimentoDAO getInstance(){ return instance;}
	

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Abastecimento entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AbastecimentoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Abastecimento entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Abastecimento entity) {
		EntityManagerHelper.log("saving Abastecimento instance", Level.INFO,
				null);
		try {
			EntityManagerHelper.beginTransaction();getEntityManager().persist(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Abastecimento entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AbastecimentoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Abastecimento entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Abastecimento entity) {
		EntityManagerHelper.log("deleting Abastecimento instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(Abastecimento.class,
					entity.getIdabastecimento());
			EntityManagerHelper.beginTransaction();getEntityManager().remove(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Abastecimento entity and return it or a copy
	 * of it to the sender. A copy of the Abastecimento entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AbastecimentoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Abastecimento entity to update
	 * @return Abastecimento the persisted Abastecimento entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Abastecimento update(Abastecimento entity) {
		EntityManagerHelper.log("updating Abastecimento instance", Level.INFO,
				null);
		try {
			EntityManagerHelper.beginTransaction();
			Abastecimento result = getEntityManager().merge(entity);
			EntityManagerHelper.commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Abastecimento findById(Integer id) {
		EntityManagerHelper.log(
				"finding Abastecimento instance with id: " + id, Level.INFO,
				null);
		try {
			Abastecimento instance = getEntityManager().find(
					Abastecimento.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Abastecimento entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Abastecimento property to query
	 * @param value
	 *            the property value to match
	 * @return List<Abastecimento> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Abastecimento> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding Abastecimento instance with property: " + propertyName
						+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Abastecimento model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Abastecimento> findByKmOdometro(Object kmOdometro) {
		return findByProperty(KM_ODOMETRO, kmOdometro);
	}

	/**
	 * Find all Abastecimento entities.
	 * 
	 * @return List<Abastecimento> all Abastecimento entities
	 */
	@SuppressWarnings("unchecked")
	public List<Abastecimento> findAll() {
		EntityManagerHelper.log("finding all Abastecimento instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from Abastecimento model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}