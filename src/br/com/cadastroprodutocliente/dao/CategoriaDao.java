package br.com.cadastroprodutocliente.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.cadastroprodutocliente.model.Categoria;
import br.com.cadastroprodutocliente.util.JPAUtil;

public class CategoriaDao implements ICategoriaDao{

	private EntityManager entityManager;
	
	public void incluirCategoria(Categoria categoria) {
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(categoria);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public void alterarCategoria(Categoria categoria) {
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(categoria);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public void deletarCategoria(Categoria categoria) {
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(categoria);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public List<Categoria> listarCategoria(){
		List<Categoria> categorias = null;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			String jpql = "select c from categoriaproduto c";
			TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql, Categoria.class);
			categorias = typedQuery.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return categorias;
	}
}
