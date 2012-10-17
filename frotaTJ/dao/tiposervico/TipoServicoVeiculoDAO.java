package com.fean.tjsc.dao.tiposervico;
// default package


import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fean.tjsc.dao.utils.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for
 * TipoServicoVeiculo entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .TipoServicoVeiculo
 * @author MyEclipse Persistence Tools
 */

public class TipoServicoVeiculoDAO implements ITipoServicoVeiculoDAO {
	// property constants
	public static final String SITUACAO = "situacao";
	
	private static TipoServicoVeiculoDAO instance = new TipoServicoVeiculoDAO();
	private TipoServicoVeiculoDAO(){}
	public static TipoServicoVeiculoDAO getInstance(){ return instance;}

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved TipoServicoVeiculo
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TipoServicoVeiculoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServicoVeiculo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TipoServicoVeiculo entity) {
		EntityManagerHelper.log("saving TipoServicoVeiculo instance",
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
	 * Delete a persistent TipoServicoVeiculo entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TipoServicoVeiculoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServicoVeiculo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TipoServicoVeiculo entity) {
		EntityManagerHelper.log("deleting TipoServicoVeiculo instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(TipoServicoVeiculo.class,
					entity.getId());
			EntityManagerHelper.beginTransaction();getEntityManager().remove(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved TipoServicoVeiculo entity and return it or a
	 * copy of it to the sender. A copy of the TipoServicoVeiculo entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = TipoServicoVeiculoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServicoVeiculo entity to update
	 * @return TipoServicoVeiculo the persisted TipoServicoVeiculo entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TipoServicoVeiculo update(TipoServicoVeiculo entity) {
		EntityManagerHelper.log("updating TipoServicoVeiculo instance",
				Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();
			TipoServicoVeiculo result = getEntityManager().merge(entity);
			EntityManagerHelper.commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public TipoServicoVeiculo findById(TipoServicoVeiculoId id) {
		EntityManagerHelper.log("finding TipoServicoVeiculo instance with id: "
				+ id, Level.INFO, null);
		try {
			TipoServicoVeiculo instance = getEntityManager().find(
					TipoServicoVeiculo.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all TipoServicoVeiculo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TipoServicoVeiculo property to query
	 * @param value
	 *            the property value to match
	 * @return List<TipoServicoVeiculo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<TipoServicoVeiculo> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding TipoServicoVeiculo instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from TipoServicoVeiculo model where model."
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

	public List<TipoServicoVeiculo> findBySituacao(Object situacao) {
		return findByProperty(SITUACAO, situacao);
	}

	/**
	 * Find all TipoServicoVeiculo entities.
	 * 
	 * @return List<TipoServicoVeiculo> all TipoServicoVeiculo entities
	 */
	@SuppressWarnings("unchecked")
	public List<TipoServicoVeiculo> findAll() {
		EntityManagerHelper.log("finding all TipoServicoVeiculo instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from TipoServicoVeiculo model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}