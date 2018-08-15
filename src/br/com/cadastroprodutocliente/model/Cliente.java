package br.com.cadastroprodutocliente.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "TB_CLIENTE")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_CLIENTE")
	private int codigo;
	
	@Column(name = "NM_CLIENTE")
	private String nome;
	
	@Column(name="NR_CPF_CLIENTE") 
	private String cpf;
	
	@Column(name = "DS_EMAIL_CLIENTE")
	private String email;
	
	@Column(name = "HR_INCL_CLIENTE")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHoraInclusao;
	
	@Column(name = "HR_MANUT_CLIENTE")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHoraManutencao;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getDataHoraInclusao() {
		return dataHoraInclusao;
	}

	public void setDataHoraInclusao(Calendar dataHoraInclusao) {
		this.dataHoraInclusao = dataHoraInclusao;
	}

	public Calendar getDataHoraManutencao() {
		return dataHoraManutencao;
	}

	public void setDataHoraManutencao(Calendar dataHoraManutencao) {
		this.dataHoraManutencao = dataHoraManutencao;
	}
}
