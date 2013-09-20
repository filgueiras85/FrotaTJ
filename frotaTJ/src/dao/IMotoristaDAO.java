package dao;
// default package

import java.util.List;
import java.util.Set;

/**
 * Interface for MotoristaDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IMotoristaDAO {
	/**
	 * Perform an initial save of a previously unsaved Motorista entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMotoristaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Motorista entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Motorista entity);

	/**
	 * Delete a persistent Motorista entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMotoristaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Motorista entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Motorista entity);

	/**
	 * Persist a previously saved Motorista entity and return it or a copy of it
	 * to the sender. A copy of the Motorista entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IMotoristaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Motorista entity to update
	 * @return Motorista the persisted Motorista entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Motorista update(Motorista entity);

	public Motorista findById(Integer id);

	/**
	 * Find all Motorista entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Motorista property to query
	 * @param value
	 *            the property value to match
	 * @return List<Motorista> found by query
	 */
	public List<Motorista> findByProperty(String propertyName, Object value);

	public List<Motorista> findByMatricula(Object matricula);

	public List<Motorista> findByNome(Object nome);

	/**
	 * Find all Motorista entities.
	 * 
	 * @return List<Motorista> all Motorista entities
	 */
	public List<Motorista> findAll();
}