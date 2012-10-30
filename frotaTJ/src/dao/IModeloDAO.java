package dao;
// default package

import java.util.List;
import java.util.Set;

/**
 * Interface for ModeloDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IModeloDAO {
	/**
	 * Perform an initial save of a previously unsaved Modelo entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IModeloDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Modelo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Modelo entity);

	/**
	 * Delete a persistent Modelo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IModeloDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Modelo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Modelo entity);

	/**
	 * Persist a previously saved Modelo entity and return it or a copy of it to
	 * the sender. A copy of the Modelo entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IModeloDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Modelo entity to update
	 * @return Modelo the persisted Modelo entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Modelo update(Modelo entity);

	public Modelo findById(Integer id);

	/**
	 * Find all Modelo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Modelo property to query
	 * @param value
	 *            the property value to match
	 * @return List<Modelo> found by query
	 */
	public List<Modelo> findByProperty(String propertyName, Object value);

	public List<Modelo> findByNome(Object nome);

	/**
	 * Find all Modelo entities.
	 * 
	 * @return List<Modelo> all Modelo entities
	 */
	public List<Modelo> findAll();
}