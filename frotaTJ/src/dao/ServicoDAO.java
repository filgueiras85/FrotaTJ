package dao;
// default package


import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for
 * Servico entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see .Servico
 * @author MyEclipse Persistence Tools
 */

public class ServicoDAO implements IServicoDAO {
	// property constants
	public static final String VALOR = "valor";
	public static final String NRO_ORCAMENTO = "nroOrcamento";
	public static final String NF_TICKET = "nfTicket";
	public static final String DESCRICAO = "descricao";
	public static final String KM = "km";
	
	private static ServicoDAO instance = new ServicoDAO();
	private ServicoDAO(){}
	public static ServicoDAO getInstance(){ return instance;}

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Servico entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ServicoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Servico entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Servico entity) {
		EntityManagerHelper.log("saving Servico instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();getEntityManager().persist(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Servico entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ServicoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Servico entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Servico entity) {
		EntityManagerHelper.log("deleting Servico instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Servico.class,
					entity.getIdServico());
			EntityManagerHelper.beginTransaction();getEntityManager().remove(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Servico entity and return it or a copy of it
	 * to the sender. A copy of the Servico entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ServicoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Servico entity to update
	 * @return Servico the persisted Servico entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Servico update(Servico entity) {
		EntityManagerHelper.log("updating Servico instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();
			Servico result = getEntityManager().merge(entity);
			EntityManagerHelper.commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Servico findById(Integer id) {
		EntityManagerHelper.log("finding Servico instance with id: " + id,
				Level.INFO, null);
		try {
			Servico instance = getEntityManager().find(Servico.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Servico entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Servico property to query
	 * @param value
	 *            the property value to match
	 * @return List<Servico> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Servico> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Servico instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Servico model where model."
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

	public List<Servico> findByValor(Object valor) {
		return findByProperty(VALOR, valor);
	}

	public List<Servico> findByNroOrcamento(Object nroOrcamento) {
		return findByProperty(NRO_ORCAMENTO, nroOrcamento);
	}

	public List<Servico> findByNfTicket(Object nfTicket) {
		return findByProperty(NF_TICKET, nfTicket);
	}

	public List<Servico> findByDescricao(Object descricao) {
		return findByProperty(DESCRICAO, descricao);
	}

	public List<Servico> findByKm(Object km) {
		return findByProperty(KM, km);
	}

	/**
	 * Find all Servico entities.
	 * 
	 * @return List<Servico> all Servico entities
	 */
	@SuppressWarnings("unchecked")
	public List<Servico> findAll() {
		EntityManagerHelper.log("finding all Servico instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Servico model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}
