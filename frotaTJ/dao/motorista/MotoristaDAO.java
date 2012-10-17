package dao.motorista;
// default package


import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fean.tjsc.dao.utils.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for
 * Motorista entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see .Motorista
 * @author MyEclipse Persistence Tools
 */

public class MotoristaDAO implements IMotoristaDAO {
	// property constants
	public static final String MATRICULA = "matricula";
	public static final String NOME = "nome";
	
	private static MotoristaDAO instance = new MotoristaDAO();
	private MotoristaDAO(){}
	public static MotoristaDAO getInstance(){ return instance;}

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Motorista entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * MotoristaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Motorista entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Motorista entity) {
		EntityManagerHelper.log("saving Motorista instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();getEntityManager().persist(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Motorista entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * MotoristaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Motorista entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Motorista entity) {
		EntityManagerHelper
				.log("deleting Motorista instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Motorista.class,
					entity.getIdmotorista());
			EntityManagerHelper.beginTransaction();getEntityManager().remove(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Motorista entity and return it or a copy of it
	 * to the sender. A copy of the Motorista entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = MotoristaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Motorista entity to update
	 * @return Motorista the persisted Motorista entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Motorista update(Motorista entity) {
		EntityManagerHelper
				.log("updating Motorista instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();
			Motorista result = getEntityManager().merge(entity);
			EntityManagerHelper.commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Motorista findById(Integer id) {
		EntityManagerHelper.log("finding Motorista instance with id: " + id,
				Level.INFO, null);
		try {
			Motorista instance = getEntityManager().find(Motorista.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Motorista entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Motorista property to query
	 * @param value
	 *            the property value to match
	 * @return List<Motorista> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Motorista> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding Motorista instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Motorista model where model."
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

	public List<Motorista> findByMatricula(Object matricula) {
		return findByProperty(MATRICULA, matricula);
	}

	public List<Motorista> findByNome(Object nome) {
		return findByProperty(NOME, nome);
	}

	/**
	 * Find all Motorista entities.
	 * 
	 * @return List<Motorista> all Motorista entities
	 */
	@SuppressWarnings("unchecked")
	public List<Motorista> findAll() {
		EntityManagerHelper.log("finding all Motorista instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Motorista model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}
