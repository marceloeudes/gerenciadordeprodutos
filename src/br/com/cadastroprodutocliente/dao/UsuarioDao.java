package br.com.cadastroprodutocliente.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cadastroprodutocliente.model.NivelAcesso;
import br.com.cadastroprodutocliente.model.Usuario;
import br.com.cadastroprodutocliente.util.JPAUtil;
import br.com.cadastroprodutocliente.util.SiteUtil;

public class UsuarioDao implements IUsuarioDao {

	private EntityManager entityManager;
	private Query query;

	@Override
	public boolean incluirUsuario(Usuario usuario) {
		boolean sucesso = true;
		usuario.setSenha("1234");
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(usuario);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
			sucesso = false;
		} finally {
			entityManager.close();
		}
		return sucesso;
	}

	@Override
	public boolean resetarSenha(Usuario usuario) {
		boolean sucesso = true;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			usuario = entityManager.find(Usuario.class, usuario.getCodigo());
			usuario.setSenha("senharesetada");
			entityManager.merge(usuario);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
			sucesso = false;
		} finally {
			entityManager.close();
		}
		return sucesso;
	}
	
	@Override
	public boolean alterarSenha(Usuario usuario) {
		boolean sucesso = true;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			Usuario usuarioManaged = entityManager.find(Usuario.class, usuario.getCodigo());
			usuarioManaged.setSenha(usuario.getSenha());
			entityManager.merge(usuario);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
			sucesso = false;
		} finally {
			entityManager.close();
		}
		return sucesso;
	}
	
	@Override
	public Usuario consultarUsuario(Usuario usuario) {
		try {
			entityManager = JPAUtil.getEntityManager();
			usuario = entityManager.find(Usuario.class, usuario.getCodigo());
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return usuario;
	}

	@Override
	public boolean deletarUsuario(Usuario usuario) {
		boolean sucesso = true;
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			usuario = entityManager.find(Usuario.class, usuario.getCodigo());
			entityManager.remove(usuario);
			entityManager.getTransaction().commit();
		} catch (Throwable e) {
			e.printStackTrace();
			sucesso = false;
		} finally {
			entityManager.close();
		}
		return sucesso;
	}

	@Override
	public Usuario validarUsuario(Usuario credenciais) {
		Usuario usuario = null;
		String jpql = "select user from TB_USUARIO user where user.email = :pEmail and user.senha = :pSenha";
		try {
			entityManager = JPAUtil.getEntityManager();
			query = entityManager.createQuery(jpql);
			query.setParameter("pEmail", credenciais.getEmail());
			query.setParameter("pSenha", credenciais.getSenha());
			if (!query.getResultList().isEmpty()) {
				usuario = (Usuario) query.getResultList().get(0);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return usuario;
	}

	public List<Usuario> listaDinamica(Usuario filtro){
		boolean nome = false;
		boolean nivel = false;
		if (!SiteUtil.emptyOrNull(filtro.getNome())) {
			nome = true;
		}
		if (!SiteUtil.emptyOrNull(filtro.getNivelAcesso())) {
			nivel = true;
		}
		if (nome && nivel) {
			return listarUsuario(filtro.getNome(), filtro.getNivelAcesso().getId());
		} else if (nome) {
			return listarUsuario(filtro.getNome());
		} else if (nivel) {
			return listarUsuario(filtro.getNivelAcesso());
		} else {
			return listarUsuario();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarUsuario() {
		List<Usuario> usuarios = null;
		String jpql = "select u from TB_USUARIO u " + "order by nome";
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			query = entityManager.createQuery(jpql);
			usuarios = query.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return usuarios;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuario(String nome) {
		List<Usuario> usuarios = null;
		String jpql = "select u from TB_USUARIO u" + " where u.nome like :pNome" + " order by codigo";
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			query = entityManager.createQuery(jpql);
			query.setParameter("pNome", "%" + nome + "%");
			usuarios = query.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return usuarios;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuario(NivelAcesso nivel) {
		List<Usuario> usuarios = null;
		String jpql = "select u from TB_USUARIO u" + " where u.nivelAcesso = :pNivel" + " order by codigo";
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			query = entityManager.createQuery(jpql);
			query.setParameter("pNivel", nivel);
			usuarios = query.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return usuarios;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuario(String nome, int nivel) {
		List<Usuario> usuarios = null;
		String jpql = "select u from TB_USUARIO u" + " where u.nivelAcesso = :pNivel" +
		" and u.nome like :pNome" + " order by codigo";
		try {
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			query = entityManager.createQuery(jpql);
			query.setParameter("pNivel", nivel);
			query.setParameter("pNome", "%" + nome + "%");
			usuarios = query.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return usuarios;
	}

}
