package dao.tiposervico;
// default package

import java.util.List;

/**
 * Interface for TipoServicoModeloDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITipoServicoModeloDAO {
	/**
	 * Perform an initial save of a previously unsaved TipoServicoModelo entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITipoServicoModeloDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServicoModelo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TipoServicoModelo entity);

	/**
	 * Delete a persistent TipoServicoModelo entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITipoServicoModeloDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServicoModelo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TipoServicoModelo entity);

	/**
	 * Persist a previously saved TipoServicoModelo entity and return it or a
	 * copy of it to the sender. A copy of the TipoServicoModelo entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITipoServicoModeloDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServicoModelo entity to update
	 * @return TipoServicoModelo the persisted TipoServicoModelo entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TipoServicoModelo update(TipoServicoModelo entity);

	public TipoServicoModelo findById(TipoServicoModeloId id);

	/**
	 * Find all TipoServicoModelo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TipoServicoModelo property to query
	 * @param value
	 *            the property value to match
	 * @return List<TipoServicoModelo> found by query
	 */
	public List<TipoServicoModelo> findByProperty(String propertyName,
			Object value);

	public List<TipoServicoModelo> findByKm(Object km);

	public List<TipoServicoModelo> findByTempo(Object tempo);

	/**
	 * Find all TipoServicoModelo entities.
	 * 
	 * @return List<TipoServicoModelo> all TipoServicoModelo entities
	 */
	public List<TipoServicoModelo> findAll();
}
