package br.com.cadastroprodutocliente.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cadastroprodutocliente.model.Produto;
import br.com.cadastroprodutocliente.util.JPAUtil;
import br.com.cadastroprodutocliente.util.SiteUtil;

public class ProdutoDao implements IProdutoDao {

	private EntityManager entityManager;
	private Query query;

	public boolean incluirProduto(Produto produto) {
		boolean sucesso = true;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(produto);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
			sucesso = false;
		} finally {
			entityManager.close();
		}
		return sucesso;
	}

	public boolean alterarProduto(Produto produto) {
		boolean sucesso = true;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.find(Produto.class, produto.getCodigo());
			entityManager.merge(produto);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
			sucesso = false;
		} finally {
			entityManager.close();
		}
		return sucesso;
	}

	public boolean deletarProduto(Produto produto) {
		boolean sucesso = true;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			Produto produtoManaged = entityManager.find(Produto.class, produto.getCodigo());
			entityManager.remove(produtoManaged);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
			sucesso = false;
		} finally {
			entityManager.close();
		}
		return sucesso;
	}

	public List<Produto> listaDinamica(Produto filtro) {
		boolean categoria = false;
		boolean descricao = false;
		if (filtro.getCategoria().getCodigo() > 0) {
			categoria = true;
		}
		if (!SiteUtil.emptyOrNull(filtro.getDescricao())) {
			descricao = true;
		}
		if (categoria && descricao) {
			return listarProduto(filtro.getCategoria().getCodigo(), filtro.getDescricao());
		} else if (categoria) {
			return listarProduto(filtro.getCategoria().getCodigo());
		} else if (descricao) {
			return listarProduto(filtro.getDescricao());
		} else {
			return listarProduto();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listarProduto() {
		List<Produto> produtos = null;
		String jpql = "select p from TB_PRODUTO p join fetch p.categoria" + " order by p.codigo";
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			query = entityManager.createQuery(jpql);
			produtos = query.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return produtos;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listarProduto(int categoria) {
		List<Produto> produtos = null;
		String jpql = "select p from TB_PRODUTO p join fetch p.categoria " + "where p.categoria.codigo = :pCategoria"
				+ " order by p.codigo";
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			query = entityManager.createQuery(jpql);
			query.setParameter("pCategoria", categoria);
			produtos = query.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return produtos;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listarProduto(String descricao) {
		List<Produto> produtos = null;
		String jpql = "select p from TB_PRODUTO p join fetch p.categoria " + "where p.descricao like :pDescricao"
				+ " order by p.codigo";
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			query = entityManager.createQuery(jpql);
			query.setParameter("pDescricao", "%" + descricao + "%");
			produtos = query.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return produtos;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listarProduto(int categoria, String descricao) {
		List<Produto> produtos = null;
		String jpql = "select p from TB_PRODUTO p join fetch p.categoria "
				+ "where p.categoria.codigo = :pCategoria " + "and p.descricao like :pDescricao"
				+ " order by p.codigo";
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			query = entityManager.createQuery(jpql);
			query.setParameter("pCategoria", categoria);
			query.setParameter("pDescricao", "%" + descricao + "%");
			produtos = query.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return produtos;
	}

}
