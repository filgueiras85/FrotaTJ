package dao;
// default package

import java.util.List;

/**
 * Interface for UsuarioDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUsuarioDAO {
	/**
	 * Perform an initial save of a previously unsaved Usuario entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUsuarioDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Usuario entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Usuario entity);

	/**
	 * Delete a persistent Usuario entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUsuarioDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Usuario entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Usuario entity);

	/**
	 * Persist a previously saved Usuario entity and return it or a copy of it
	 * to the sender. A copy of the Usuario entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUsuarioDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Usuario entity to update
	 * @return Usuario the persisted Usuario entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Usuario update(Usuario entity);

	public Usuario findById(Integer id);

	/**
	 * Find all Usuario entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Usuario property to query
	 * @param value
	 *            the property value to match
	 * @return List<Usuario> found by query
	 */
	public List<Usuario> findByProperty(String propertyName, Object value);

	public List<Usuario> findByNome(Object nome);

	public List<Usuario> findByMatricula(Object matricula);

	public List<Usuario> findBySenha(Object senha);

	public List<Usuario> findByEmail(Object email);

	public List<Usuario> findByAdministrador(Object administrador);

	/**
	 * Find all Usuario entities.
	 * 
	 * @return List<Usuario> all Usuario entities
	 */
	public List<Usuario> findAll();
}