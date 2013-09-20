package dao;
// default package


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for
 * Modelo entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see .Modelo
 * @author MyEclipse Persistence Tools
 */

public class ModeloDAO implements IModeloDAO {
	// property constants
	public static final String NOME = "nome";

	private static ModeloDAO instance = new ModeloDAO();
	private ModeloDAO(){

	}
	public static ModeloDAO getInstance(){ return instance;}

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * ModeloDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Modelo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Modelo entity) {
		EntityManagerHelper.log("saving Modelo instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();getEntityManager().persist(entity);EntityManagerHelper.commit();
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Modelo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ModeloDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Modelo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Modelo entity) {
		EntityManagerHelper.log("deleting Modelo instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Modelo.class, entity.getIdmodelo());
			EntityManagerHelper.beginTransaction();
			getEntityManager().remove(entity);
			EntityManagerHelper.commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = ModeloDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Modelo entity to update
	 * @return Modelo the persisted Modelo entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Modelo update(Modelo entity) {
		EntityManagerHelper.log("updating Modelo instance", Level.INFO, null);
		try {
			EntityManagerHelper.beginTransaction();
			EntityManagerHelper.commit();
			Modelo result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Modelo findById(Integer id) {
		EntityManagerHelper.log("finding Modelo instance with id: " + id,
				Level.INFO, null);
		try {
			Modelo instance = getEntityManager().find(Modelo.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Modelo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Modelo property to query
	 * @param value
	 *            the property value to match
	 * @return List<Modelo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Modelo> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Modelo instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Modelo model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Modelo> findByNome(Object nome) {
		return findByProperty(NOME, nome);
	}

	/**
	 * Find all Modelo entities.
	 * 
	 * @return List<Modelo> all Modelo entities
	 */
	@SuppressWarnings("unchecked")
	public List<Modelo> findAll() {
		EntityManagerHelper.log("finding all Modelo instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Modelo model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}
	public List<Modelo> ModeloServicoUnidade(final Object idUnidade, final Object idTipoServico, final Object idMarca) {
		EntityManagerHelper.log("finding Servico instance with property: ", Level.INFO, null);
		try {
			String queryString;
			if (idTipoServico.equals(0)) {
				queryString = "select modelo.nome from servico, veiculo, modelo " +
						"where servico.veiculo_idveiculo = veiculo.idveiculo and veiculo.modelo_idmodelo = modelo.idmodelo " +
						"and veiculo.unidade_idunidade =" + idUnidade+ " and modelo.marca_idmarca =" +idMarca+ " group by modelo.nome";
			}else{
				queryString = "select modelo.nome from servico, veiculo, modelo " +
						"where servico.veiculo_idveiculo = veiculo.idveiculo and veiculo.modelo_idmodelo = modelo.idmodelo " +
						"and veiculo.unidade_idunidade ="+ idUnidade +" and servico.tipo_servico_idtipo_servico =" + idTipoServico;

			}

			Query query = getEntityManager().createNativeQuery(queryString);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}	
	public List<Modelo> ModeloMarca(Object marca) {

		List<Modelo> modelo = new ArrayList<>();		
		try {
			Query query = getEntityManager().createNamedQuery("ModeloMarca");
			query.setParameter("marca", marca);
			return modelo = query.getResultList();
		} catch (RuntimeException re) {
			
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			return null;
			//throw re;
		}
	}
	
}
