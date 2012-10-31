package dao;
// default package


import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for Marca
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see .Marca
 * @author MyEclipse Persistence Tools
 */

public class MarcaDAO implements IMarcaDAO {
	// property constants
	public static final String NOME = "nome";
	
	private static MarcaDAO instance = new MarcaDAO();
	private MarcaDAO(){}
	public static MarcaDAO getInstance(){ return instance;}

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Marca entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * MarcaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Marca entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Marca entity) {
		EntityManagerHelper.log("saving Marca instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();getEntityManager().persist(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Marca entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * MarcaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Marca entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Marca entity) {
		EntityManagerHelper.log("deleting Marca instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Marca.class,
					entity.getIdmarca());
			EntityManagerHelper.beginTransaction();getEntityManager().remove(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Marca entity and return it or a copy of it to
	 * the sender. A copy of the Marca entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = MarcaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Marca entity to update
	 * @return Marca the persisted Marca entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Marca update(Marca entity) {
		EntityManagerHelper.log("updating Marca instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();
			EntityManagerHelper.commit();
			Marca result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Marca findById(Integer id) {
		EntityManagerHelper.log("finding Marca instance with id: " + id,
				Level.INFO, null);
		try {
			Marca instance = getEntityManager().find(Marca.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Marca entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Marca property to query
	 * @param value
	 *            the property value to match
	 * @return List<Marca> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Marca> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Marca instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Marca model where model."
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

	public List<Marca> findByNome(Object nome) {
		return findByProperty(NOME, nome);
	}

	/**
	 * Find all Marca entities.
	 * 
	 * @return List<Marca> all Marca entities
	 */
	@SuppressWarnings("unchecked")
	public List<Marca> findAll() {
		EntityManagerHelper.log("finding all Marca instances", Level.INFO, null);
		try {
			final String queryString = "select model from Marca model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}
