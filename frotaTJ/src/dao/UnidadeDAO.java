package dao;
// default package


import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for
 * Unidade entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see .Unidade
 * @author MyEclipse Persistence Tools
 */

public class UnidadeDAO implements IUnidadeDAO {
	// property constants
	public static final String NOME = "nome";
	
	private static UnidadeDAO instance = new UnidadeDAO();
	private UnidadeDAO(){}
	public static UnidadeDAO getInstance(){ return instance;}

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Unidade entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UnidadeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Unidade entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Unidade entity) {
		//EntityManagerHelper.log("saving Unidade instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();getEntityManager().persist(entity);EntityManagerHelper.commit();
			//EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			//EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Unidade entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UnidadeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Unidade entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Unidade entity) {
		//EntityManagerHelper.log("deleting Unidade instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Unidade.class,
					entity.getIdunidade());
			EntityManagerHelper.beginTransaction();getEntityManager().remove(entity);EntityManagerHelper.commit();
			//EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			//EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Unidade entity and return it or a copy of it
	 * to the sender. A copy of the Unidade entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = UnidadeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Unidade entity to update
	 * @return Unidade the persisted Unidade entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Unidade update(Unidade entity) {
		//EntityManagerHelper.log("updating Unidade instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();
			Unidade result = getEntityManager().merge(entity);
			EntityManagerHelper.commit();
			//EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			//EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Unidade findById(Integer id) {
		//EntityManagerHelper.log("finding Unidade instance with id: " + id,
		//		Level.INFO, null);
		try {
			Unidade instance = getEntityManager().find(Unidade.class, id);
			return instance;
		} catch (RuntimeException re) {
			//EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Unidade entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Unidade property to query
	 * @param value
	 *            the property value to match
	 * @return List<Unidade> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Unidade> findByProperty(String propertyName, final Object value) {
		//EntityManagerHelper.log("finding Unidade instance with property: "
		//		+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Unidade model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			//EntityManagerHelper.log("find by property name failed",
		//			Level.SEVERE, re);
			throw re;
		}
	}

	public List<Unidade> findByNome(Object nome) {
		return findByProperty(NOME, nome);
	}

	/**
	 * Find all Unidade entities.
	 * 
	 * @return List<Unidade> all Unidade entities
	 */
	@SuppressWarnings("unchecked")
	public List<Unidade> findAll() {
		EntityManagerHelper.log("finding all Unidade instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Unidade model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}
