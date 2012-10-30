package dao;
// default package

import java.util.List;
import java.util.Set;

/**
 * Interface for MarcaDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IMarcaDAO {
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
	 * IMarcaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Marca entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Marca entity);

	/**
	 * Delete a persistent Marca entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMarcaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Marca entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Marca entity);

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
	 * entity = IMarcaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Marca entity to update
	 * @return Marca the persisted Marca entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Marca update(Marca entity);

	public Marca findById(Integer id);

	/**
	 * Find all Marca entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Marca property to query
	 * @param value
	 *            the property value to match
	 * @return List<Marca> found by query
	 */
	public List<Marca> findByProperty(String propertyName, Object value);

	public List<Marca> findByNome(Object nome);

	/**
	 * Find all Marca entities.
	 * 
	 * @return List<Marca> all Marca entities
	 */
	public List<Marca> findAll();
}