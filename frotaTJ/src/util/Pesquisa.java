package util;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;

import dao.EntityManagerHelper;

public class Pesquisa<E> {
	
	private E classe;
	
	public Pesquisa(E classe) {
		super();
		this.classe = classe;
	}


	@SuppressWarnings("unchecked")
	public List<E> findParametrizado(String param1, String param2, String param3, String param4) {
		EntityManagerHelper.log("finding all Abastecimento instances",
				Level.INFO, null);
		try {
			String queryString = "select * from tabela ";
			boolean temWhere=false;
			if (param1.length()>0){
				queryString += "where param1="+param1;
				temWhere=true;
			}
			if (param2.length()>0){
				if (!temWhere){
					queryString += " where ";
				}
				queryString += " param2="+param2;
				temWhere=true;
			}
			if (param3.length()>0){
				if (!temWhere){
					queryString += " where ";
				}
				queryString += " param3="+param3;
				temWhere=true;
			}
			if (param4.length()>0){
				if (!temWhere){
					queryString += " where ";
				}
				queryString += " param4="+param4;
				temWhere=true;
			}			
			
			Query query = EntityManagerHelper.getEntityManager().createNativeQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}
