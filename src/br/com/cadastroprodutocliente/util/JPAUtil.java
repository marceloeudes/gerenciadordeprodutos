package br.com.cadastroprodutocliente.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public final class JPAUtil{

	public static EntityManager getEntityManager() throws Exception{
		return Persistence.createEntityManagerFactory("gerenciadorprodutos").createEntityManager();
	}
	
}
