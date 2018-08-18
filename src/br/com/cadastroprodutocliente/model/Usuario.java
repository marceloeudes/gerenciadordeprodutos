package br.com.cadastroprodutocliente.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.cadastroprodutocliente.util.NivelAcessoConverter;

@Entity(name = "TB_USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_USUARIO")
	private int codigo;
	
	@Column(name = "NM_USUARIO")
	private String nome;
	
	@Column(name = "DS_EMAIL_USUARIO")
	private String email;
	
	@Column(name = "DS_SENHA_USUARIO")
	private String senha;
	
	@Convert(converter=NivelAcessoConverter.class)
	@Column(name = "IE_NIVEL_ACESSO_USUARIO")
	private NivelAcesso nivelAcesso;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}
	public void setNivelAcesso(NivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

}
