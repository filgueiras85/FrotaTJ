package dao.tiposervico;
// default package

import java.util.List;
import java.util.Set;

/**
 * Interface for TipoServicoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITipoServicoDAO {
	/**
	 * Perform an initial save of a previously unsaved TipoServico entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITipoServicoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServico entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TipoServico entity);

	/**
	 * Delete a persistent TipoServico entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITipoServicoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServico entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TipoServico entity);

	/**
	 * Persist a previously saved TipoServico entity and return it or a copy of
	 * it to the sender. A copy of the TipoServico entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITipoServicoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServico entity to update
	 * @return TipoServico the persisted TipoServico entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TipoServico update(TipoServico entity);

	public TipoServico findById(Integer id);

	/**
	 * Find all TipoServico entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TipoServico property to query
	 * @param value
	 *            the property value to match
	 * @return List<TipoServico> found by query
	 */
	public List<TipoServico> findByProperty(String propertyName, Object value);

	public List<TipoServico> findByNome(Object nome);

	/**
	 * Find all TipoServico entities.
	 * 
	 * @return List<TipoServico> all TipoServico entities
	 */
	public List<TipoServico> findAll();
}
