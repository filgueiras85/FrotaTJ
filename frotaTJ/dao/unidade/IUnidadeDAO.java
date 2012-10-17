package dao.unidade;
// default package

import java.util.List;
import java.util.Set;

/**
 * Interface for UnidadeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUnidadeDAO {
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
	 * IUnidadeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Unidade entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Unidade entity);

	/**
	 * Delete a persistent Unidade entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUnidadeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Unidade entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Unidade entity);

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
	 * entity = IUnidadeDAO.update(entity);
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
	public Unidade update(Unidade entity);

	public Unidade findById(Integer id);

	/**
	 * Find all Unidade entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Unidade property to query
	 * @param value
	 *            the property value to match
	 * @return List<Unidade> found by query
	 */
	public List<Unidade> findByProperty(String propertyName, Object value);

	public List<Unidade> findByNome(Object nome);

	/**
	 * Find all Unidade entities.
	 * 
	 * @return List<Unidade> all Unidade entities
	 */
	public List<Unidade> findAll();
}
