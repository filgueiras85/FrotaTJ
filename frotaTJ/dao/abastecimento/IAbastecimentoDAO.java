package dao.abastecimento;
// default package

import java.util.Date;
import java.util.List;

/**
 * Interface for AbastecimentoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAbastecimentoDAO {
	/**
	 * Perform an initial save of a previously unsaved Abastecimento entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAbastecimentoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Abastecimento entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Abastecimento entity);

	/**
	 * Delete a persistent Abastecimento entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAbastecimentoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Abastecimento entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Abastecimento entity);

	/**
	 * Persist a previously saved Abastecimento entity and return it or a copy
	 * of it to the sender. A copy of the Abastecimento entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAbastecimentoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Abastecimento entity to update
	 * @return Abastecimento the persisted Abastecimento entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Abastecimento update(Abastecimento entity);

	public Abastecimento findById(Integer id);

	/**
	 * Find all Abastecimento entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Abastecimento property to query
	 * @param value
	 *            the property value to match
	 * @return List<Abastecimento> found by query
	 */
	public List<Abastecimento> findByProperty(String propertyName, Object value);

	public List<Abastecimento> findByKmOdometro(Object kmOdometro);

	/**
	 * Find all Abastecimento entities.
	 * 
	 * @return List<Abastecimento> all Abastecimento entities
	 */
	public List<Abastecimento> findAll();
}
