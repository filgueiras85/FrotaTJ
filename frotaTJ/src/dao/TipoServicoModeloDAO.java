package dao;
// default package


import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for
 * TipoServicoModelo entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .TipoServicoModelo
 * @author MyEclipse Persistence Tools
 */

public class TipoServicoModeloDAO implements ITipoServicoModeloDAO {
	// property constants
	public static final String KM = "km";
	public static final String TEMPO = "tempo";
	
	private static TipoServicoModeloDAO instance = new TipoServicoModeloDAO();
	private TipoServicoModeloDAO(){}
	public static TipoServicoModeloDAO getInstance(){ return instance;}

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved TipoServicoModelo entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TipoServicoModeloDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServicoModelo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TipoServicoModelo entity) {
		EntityManagerHelper.log("saving TipoServicoModelo instance",
				Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();getEntityManager().persist(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent TipoServicoModelo entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TipoServicoModeloDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServicoModelo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TipoServicoModelo entity) {
		EntityManagerHelper.log("deleting TipoServicoModelo instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(TipoServicoModelo.class,
					entity.getId());
			EntityManagerHelper.beginTransaction();getEntityManager().remove(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved TipoServicoModelo entity and return it or a
	 * copy of it to the sender. A copy of the TipoServicoModelo entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = TipoServicoModeloDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServicoModelo entity to update
	 * @return TipoServicoModelo the persisted TipoServicoModelo entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TipoServicoModelo update(TipoServicoModelo entity) {
		EntityManagerHelper.log("updating TipoServicoModelo instance",
				Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();
			TipoServicoModelo result = getEntityManager().merge(entity);
			EntityManagerHelper.commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public TipoServicoModelo findById(TipoServicoModeloId id) {
		EntityManagerHelper.log("finding TipoServicoModelo instance with id: "
				+ id, Level.INFO, null);
		try {
			TipoServicoModelo instance = getEntityManager().find(
					TipoServicoModelo.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all TipoServicoModelo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TipoServicoModelo property to query
	 * @param value
	 *            the property value to match
	 * @return List<TipoServicoModelo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<TipoServicoModelo> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding TipoServicoModelo instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from TipoServicoModelo model where model."
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

	public List<TipoServicoModelo> findByKm(Object km) {
		return findByProperty(KM, km);
	}

	public List<TipoServicoModelo> findByTempo(Object tempo) {
		return findByProperty(TEMPO, tempo);
	}

	/**
	 * Find all TipoServicoModelo entities.
	 * 
	 * @return List<TipoServicoModelo> all TipoServicoModelo entities
	 */
	@SuppressWarnings("unchecked")
	public List<TipoServicoModelo> findAll() {
		EntityManagerHelper.log("finding all TipoServicoModelo instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from TipoServicoModelo model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}
