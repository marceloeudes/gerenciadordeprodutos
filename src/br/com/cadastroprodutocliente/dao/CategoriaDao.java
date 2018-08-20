package br.com.cadastroprodutocliente.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.cadastroprodutocliente.model.Categoria;
import br.com.cadastroprodutocliente.util.JPAUtil;

public class CategoriaDao implements ICategoriaDao{

	private EntityManager entityManager;
	
	public boolean incluirCategoria(Categoria categoria) {
		boolean sucesso = true;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(categoria);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
			sucesso = false;
		} finally {
			entityManager.close();
		}
		return sucesso;
	}
	
	public boolean alterarCategoria(Categoria categoria) {
		boolean sucesso = true;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.find(Categoria.class, categoria.getCodigo());
			entityManager.merge(categoria);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
			sucesso = false;
		} finally {
			entityManager.close();
		}
		return sucesso;
	}
	
	public boolean deletarCategoria(Categoria categoria) {
		boolean sucesso = true;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.find(Categoria.class, categoria.getCodigo());
			entityManager.remove(categoria);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
			sucesso = false;
		} finally {
			entityManager.close();
		}
		return sucesso;
	}
	
	public List<Categoria> listarCategoria(){
		List<Categoria> categorias = null;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			String jpql = "select categoria from TB_CATEGORIA_PRODUTO categoria";
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
