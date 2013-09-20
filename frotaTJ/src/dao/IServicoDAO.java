package dao;
// default package

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for ServicoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IServicoDAO {
	/**
	 * Perform an initial save of a previously unsaved Servico entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IServicoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Servico entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Servico entity);

	/**
	 * Delete a persistent Servico entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IServicoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Servico entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Servico entity);

	/**
	 * Persist a previously saved Servico entity and return it or a copy of it
	 * to the sender. A copy of the Servico entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IServicoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Servico entity to update
	 * @return Servico the persisted Servico entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Servico update(Servico entity);

	public Servico findById(Integer id);

	/**
	 * Find all Servico entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Servico property to query
	 * @param value
	 *            the property value to match
	 * @return List<Servico> found by query
	 */
	public List<Servico> findByProperty(String propertyName, Object value);

	public List<Servico> findByValor(Object valor);

	public List<Servico> findByNroOrcamento(Object nroOrcamento);

	public List<Servico> findByNfTicket(Object nfTicket);

	public List<Servico> findByDescricao(Object descricao);

	public List<Servico> findByKm(Object km);

	/**
	 * Find all Servico entities.
	 * 
	 * @return List<Servico> all Servico entities
	 */
	public List<Servico> findAll();
}