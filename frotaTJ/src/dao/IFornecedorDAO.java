package dao;
// default package

import java.util.List;
import java.util.Set;

/**
 * Interface for FornecedorDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IFornecedorDAO {
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
	 * IFornecedorDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Fornecedor entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Fornecedor entity);

	/**
	 * Delete a persistent Fornecedor entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFornecedorDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Fornecedor entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Fornecedor entity);

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
	 * entity = IFornecedorDAO.update(entity);
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
	public Fornecedor update(Fornecedor entity);

	public Fornecedor findById(Integer id);

	/**
	 * Find all Fornecedor entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Fornecedor property to query
	 * @param value
	 *            the property value to match
	 * @return List<Fornecedor> found by query
	 */
	public List<Fornecedor> findByProperty(String propertyName, Object value);

	public List<Fornecedor> findByNome(Object nome);

	public List<Fornecedor> findByCnpj(Object cnpj);

	public List<Fornecedor> findByFone1(Object fone1);

	public List<Fornecedor> findByEmail(Object email);

	public List<Fornecedor> findByFone2(Object fone2);

	/**
	 * Find all Fornecedor entities.
	 * 
	 * @return List<Fornecedor> all Fornecedor entities
	 */
	public List<Fornecedor> findAll();
}