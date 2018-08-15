package br.com.cadastroprodutocliente.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.cadastroprodutocliente.model.Cliente;
import br.com.cadastroprodutocliente.util.JPAUtil;

public class ClienteDao {

	private EntityManager entityManager;
	
	public void incluirCliente(Cliente cliente) {
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(cliente);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public void alterarCliente(Cliente cliente) {
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(cliente);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public void deletarCliente(Cliente cliente) {
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(cliente);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public List<Cliente> listarCliente(){
		List<Cliente> clientes = null;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			String jpql = "select c from ClienteCliente c";
			TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
			clientes = typedQuery.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return clientes;
	}
}
