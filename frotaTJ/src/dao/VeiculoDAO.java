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
 * Veiculo entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see .Veiculo
 * @author MyEclipse Persistence Tools
 */

public class VeiculoDAO implements IVeiculoDAO {
	// property constants
	public static final String PLACA = "placa";
	public static final String REVAVAN = "revavan";
	public static final String CHASSI = "chassi";
	public static final String ODOMETRO = "odometro";
	public static final String SITUACAO = "situacao";
	public static final String IDUNIDADE ="unidade_idunidade";
	
	private static VeiculoDAO instance = new VeiculoDAO();
	private VeiculoDAO(){}
	public static VeiculoDAO getInstance(){ return instance;}
	
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Veiculo entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * VeiculoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Veiculo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Veiculo entity) {
		EntityManagerHelper.log("saving Veiculo instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();getEntityManager().persist(entity);
			EntityManagerHelper.commit();
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Veiculo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * VeiculoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Veiculo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Veiculo entity) {
		EntityManagerHelper.log("deleting Veiculo instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Veiculo.class,
					entity.getIdveiculo());
			EntityManagerHelper.beginTransaction();getEntityManager().remove(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Veiculo entity and return it or a copy of it
	 * to the sender. A copy of the Veiculo entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = VeiculoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Veiculo entity to update
	 * @return Veiculo the persisted Veiculo entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Veiculo update(Veiculo entity) {
		EntityManagerHelper.log("updating Veiculo instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();
			Veiculo result = getEntityManager().merge(entity);
			EntityManagerHelper.commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Veiculo findById(Integer id) {
		EntityManagerHelper.log("finding Veiculo instance with id: " + id,
				Level.INFO, null);
		try {
			Veiculo instance = getEntityManager().find(Veiculo.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Veiculo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Veiculo property to query
	 * @param value
	 *            the property value to match
	 * @return List<Veiculo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Veiculo> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Veiculo instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Veiculo model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			//query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Veiculo> findByPlaca(Object placa) {
		return findByProperty(PLACA, placa);
	}

	public List<Veiculo> findByRevavan(Object revavan) {
		return findByProperty(REVAVAN, revavan);
	}

	public List<Veiculo> findByChassi(Object chassi) {
		return findByProperty(CHASSI, chassi);
	}

	public List<Veiculo> findByOdometro(Object odometro) {
		return findByProperty(ODOMETRO, odometro);
	}

	public List<Veiculo> findBySituacao(Object situacao) {
		return findByProperty(SITUACAO, situacao);
	}
	public List<Veiculo> findByUnidade(Object unidade){
		return findByProperty(IDUNIDADE, unidade);
	}

	/**
	 * Find all Veiculo entities.
	 * 
	 * @return List<Veiculo> all Veiculo entities
	 */
	@SuppressWarnings("unchecked")
	public List<Veiculo> findAll() {
		EntityManagerHelper.log("finding all Veiculo instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Veiculo model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	public List<Veiculo> VeiculoUnidade(Unidade unidade) {
		EntityManagerHelper.log("finding all veiculo by unidade", Level.INFO,
				null);
		List<Veiculo> veiculo = null;		

		try {
			Query query = getEntityManager().createNamedQuery("VeiculoUnidade");
			query.setParameter("unidade", unidade);

			return veiculo = query.getResultList();
		} catch (RuntimeException re) {

			
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			return null;
			//throw re;
		}
	}

}
