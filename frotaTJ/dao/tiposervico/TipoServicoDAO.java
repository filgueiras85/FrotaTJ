package com.fean.tjsc.dao.tiposervico;
// default package


import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fean.tjsc.dao.utils.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for
 * TipoServico entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .TipoServico
 * @author MyEclipse Persistence Tools
 */

public class TipoServicoDAO implements ITipoServicoDAO {
	// property constants
	public static final String NOME = "nome";
	
	private static TipoServicoDAO instance = new TipoServicoDAO();
	private TipoServicoDAO(){}
	public static TipoServicoDAO getInstance(){ return instance;}

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved TipoServico entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TipoServicoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServico entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TipoServico entity) {
		EntityManagerHelper
				.log("saving TipoServico instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();getEntityManager().persist(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent TipoServico entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TipoServicoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServico entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TipoServico entity) {
		EntityManagerHelper.log("deleting TipoServico instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(TipoServico.class,
					entity.getIdtipoServico());
			EntityManagerHelper.beginTransaction();getEntityManager().remove(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved TipoServico entity and return it or a copy of
	 * it to the sender. A copy of the TipoServico entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = TipoServicoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServico entity to update
	 * @return TipoServico the persisted TipoServico entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TipoServico update(TipoServico entity) {
		EntityManagerHelper.log("updating TipoServico instance", Level.INFO,
				null);
		try {
			EntityManagerHelper.beginTransaction();
			TipoServico result = getEntityManager().merge(entity);
			EntityManagerHelper.commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public TipoServico findById(Integer id) {
		EntityManagerHelper.log("finding TipoServico instance with id: " + id,
				Level.INFO, null);
		try {
			TipoServico instance = getEntityManager().find(TipoServico.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all TipoServico entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TipoServico property to query
	 * @param value
	 *            the property value to match
	 * @return List<TipoServico> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<TipoServico> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding TipoServico instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from TipoServico model where model."
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

	public List<TipoServico> findByNome(Object nome) {
		return findByProperty(NOME, nome);
	}

	/**
	 * Find all TipoServico entities.
	 * 
	 * @return List<TipoServico> all TipoServico entities
	 */
	@SuppressWarnings("unchecked")
	public List<TipoServico> findAll() {
		EntityManagerHelper.log("finding all TipoServico instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from TipoServico model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}