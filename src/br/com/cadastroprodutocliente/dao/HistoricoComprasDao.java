package br.com.cadastroprodutocliente.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.cadastroprodutocliente.model.Compra;
import br.com.cadastroprodutocliente.util.JPAUtil;

public class HistoricoComprasDao {

	private EntityManager entityManager;
	
	public void incluirHistoricoCompras(Compra historicoCompras) {
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(historicoCompras);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public List<Compra> listarHistoricoCompras(){
		List<Compra> compras = null;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			String jpql = "select c from HistoricoComprasHistoricoCompras c";
			TypedQuery<Compra> typedQuery = entityManager.createQuery(jpql, Compra.class);
			compras = typedQuery.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return compras;
	}
}
