package dao.fornecedor;
// default package


import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fean.tjsc.dao.utils.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fornecedor entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see .Fornecedor
 * @author MyEclipse Persistence Tools
 */

public class FornecedorDAO implements IFornecedorDAO {
	// property constants
	public static final String NOME = "nome";
	public static final String CNPJ = "cnpj";
	public static final String FONE1 = "fone1";
	public static final String EMAIL = "email";
	public static final String FONE2 = "fone2";
	
	private static FornecedorDAO instance = new FornecedorDAO();
	private FornecedorDAO(){}
	public static FornecedorDAO getInstance(){ return instance;}

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Fornecedor entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FornecedorDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Fornecedor entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Fornecedor entity) {
		EntityManagerHelper.log("saving Fornecedor instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();getEntityManager().persist(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Fornecedor entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FornecedorDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Fornecedor entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Fornecedor entity) {
		EntityManagerHelper.log("deleting Fornecedor instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(Fornecedor.class,
					entity.getIdfornecedor());
			EntityManagerHelper.beginTransaction();getEntityManager().remove(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Fornecedor entity and return it or a copy of
	 * it to the sender. A copy of the Fornecedor entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = FornecedorDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Fornecedor entity to update
	 * @return Fornecedor the persisted Fornecedor entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Fornecedor update(Fornecedor entity) {
		EntityManagerHelper.log("updating Fornecedor instance", Level.INFO,
				null);
		try {
			EntityManagerHelper.beginTransaction();
			EntityManagerHelper.commit();
			Fornecedor result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Fornecedor findById(Integer id) {
		EntityManagerHelper.log("finding Fornecedor instance with id: " + id,
				Level.INFO, null);
		try {
			Fornecedor instance = getEntityManager().find(Fornecedor.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Fornecedor entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Fornecedor property to query
	 * @param value
	 *            the property value to match
	 * @return List<Fornecedor> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Fornecedor> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding Fornecedor instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Fornecedor model where model."
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

	public List<Fornecedor> findByNome(Object nome) {
		return findByProperty(NOME, nome);
	}

	public List<Fornecedor> findByCnpj(Object cnpj) {
		return findByProperty(CNPJ, cnpj);
	}

	public List<Fornecedor> findByFone1(Object fone1) {
		return findByProperty(FONE1, fone1);
	}

	public List<Fornecedor> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<Fornecedor> findByFone2(Object fone2) {
		return findByProperty(FONE2, fone2);
	}

	/**
	 * Find all Fornecedor entities.
	 * 
	 * @return List<Fornecedor> all Fornecedor entities
	 */
	@SuppressWarnings("unchecked")
	public List<Fornecedor> findAll() {
		EntityManagerHelper.log("finding all Fornecedor instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Fornecedor model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}
