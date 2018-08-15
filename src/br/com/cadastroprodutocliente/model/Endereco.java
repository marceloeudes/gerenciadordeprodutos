package br.com.cadastroprodutocliente.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity(name="enderecocliente")
public class Endereco implements Serializable{

	@Id
	@ManyToOne
	@JoinColumn(name="cdCliente")
	private Cliente cliente;
	@Id
	@Column(name="cdEndereco")
	private int cdEndereco;
	@Column(name="nuCep")
	private int cep;
	@Column(name="nuResidencia")
	private int numero;
	@Column(name="dsComplemento")
	private String complemento;
	
	public int getCdEndereco() {
		return cdEndereco;
	}
	public void setCdEndereco(int cdEndereco) {
		this.cdEndereco = cdEndereco;
	}
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
