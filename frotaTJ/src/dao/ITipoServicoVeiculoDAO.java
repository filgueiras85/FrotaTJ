package dao;
// default package

import java.util.List;

/**
 * Interface for TipoServicoVeiculoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITipoServicoVeiculoDAO {
	/**
	 * Perform an initial save of a previously unsaved TipoServicoVeiculo
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITipoServicoVeiculoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServicoVeiculo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TipoServicoVeiculo entity);

	/**
	 * Delete a persistent TipoServicoVeiculo entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITipoServicoVeiculoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServicoVeiculo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TipoServicoVeiculo entity);

	/**
	 * Persist a previously saved TipoServicoVeiculo entity and return it or a
	 * copy of it to the sender. A copy of the TipoServicoVeiculo entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITipoServicoVeiculoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoServicoVeiculo entity to update
	 * @return TipoServicoVeiculo the persisted TipoServicoVeiculo entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TipoServicoVeiculo update(TipoServicoVeiculo entity);

	public TipoServicoVeiculo findById(TipoServicoVeiculoId id);

	/**
	 * Find all TipoServicoVeiculo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TipoServicoVeiculo property to query
	 * @param value
	 *            the property value to match
	 * @return List<TipoServicoVeiculo> found by query
	 */
	public List<TipoServicoVeiculo> findByProperty(String propertyName,
			Object value);

	public List<TipoServicoVeiculo> findBySituacao(Object situacao);

	/**
	 * Find all TipoServicoVeiculo entities.
	 * 
	 * @return List<TipoServicoVeiculo> all TipoServicoVeiculo entities
	 */
	public List<TipoServicoVeiculo> findAll();
}