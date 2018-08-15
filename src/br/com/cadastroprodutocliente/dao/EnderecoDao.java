package br.com.cadastroprodutocliente.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.cadastroprodutocliente.model.Endereco;
import br.com.cadastroprodutocliente.util.JPAUtil;

public class EnderecoDao {

	private EntityManager entityManager;
	
	public void incluirEndereco(Endereco endereco) {
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(endereco);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public void alterarEndereco(Endereco endereco) {
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(endereco);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public void deletarEndereco(Endereco endereco) {
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(endereco);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public List<Endereco> listarEndereco(){
		List<Endereco> enderecos = null;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			String jpql = "select c from EnderecoEndereco c";
			TypedQuery<Endereco> typedQuery = entityManager.createQuery(jpql, Endereco.class);
			enderecos = typedQuery.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return enderecos;
	}
}
