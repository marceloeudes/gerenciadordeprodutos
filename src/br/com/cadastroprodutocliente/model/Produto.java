package br.com.cadastroprodutocliente.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "TB_PRODUTO")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_PRODUTO")
	private int codigo;
	
	@Column(name = "DS_PRODUTO")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "CD_CATEGORIA_PRODUTO")
	private Categoria categoria;
	
	@Column(name = "VL_PRODUTO")
	private BigDecimal valor;
	
	@Column(name = "NR_QTD_ESTOQUE")
	private int estoque;
	
	@Column(name = "HR_INCL_PRODUTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHoraInclusao;
	
	@ManyToOne
	@JoinColumn(name = "CD_USUARIO_INCL_PRODUTO")
	private Usuario usuarioInclusao;
	
	@Column(name = "HR_MANUT_PRODUTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHoraManutencao;

	@ManyToOne
	@JoinColumn(name = "CD_USUARIO_MANUT_PRODUTO")
	private Usuario usuarioManutencao;
		
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Calendar getDataHoraInclusao() {
		return dataHoraInclusao;
	}

	public void setDataHoraInclusao(Calendar dataHoraInclusao) {
		this.dataHoraInclusao = dataHoraInclusao;
	}

	public Usuario getUsuarioInclusao() {
		return usuarioInclusao;
	}

	public void setUsuarioInclusao(Usuario usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}
	
	public Calendar getDataHoraManutencao() {
		return dataHoraManutencao;
	}

	public void setDataHoraManutencao(Calendar dataHoraManutencao) {
		this.dataHoraManutencao = dataHoraManutencao;
	}

	public Usuario getUsuarioManutencao() {
		return usuarioManutencao;
	}

	public void setUsuarioManutencao(Usuario usuarioManutencao) {
		this.usuarioManutencao = usuarioManutencao;
	}

}
