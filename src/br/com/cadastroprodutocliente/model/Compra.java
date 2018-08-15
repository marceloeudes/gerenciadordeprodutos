package br.com.cadastroprodutocliente.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="TB_COMPRA")
public class Compra implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "CD_CLIENTE")
	private Cliente cliente;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "CD_PRODUTO")
	private Produto produto;
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="HR_COMPRA")
	private Calendar dataHoraCompra;
	
	@Column(name="NR_QTD_PRODUTO")
	private int quantidadeProduto;
	
	@Column(name="VL_PRODUTO")
	private BigDecimal valorProduto;
	
	@Column(name = "HR_INCL_COMPRA")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHoraInclusao;
	
	@Column(name = "HR_MANUT_COMPRA")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHoraManutencao;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Calendar getDataHoraCompra() {
		return dataHoraCompra;
	}

	public void setDataHoraCompra(Calendar dataHoraCompra) {
		this.dataHoraCompra = dataHoraCompra;
	}

	public int getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(int quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public BigDecimal getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(BigDecimal valorProduto) {
		this.valorProduto = valorProduto;
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
