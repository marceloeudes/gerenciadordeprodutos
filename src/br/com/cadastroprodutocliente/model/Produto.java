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

import br.com.cadastroprodutocliente.util.SiteUtil;

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
	
	@Column(name = "VL_CUSTO_PRODUTO")
	private BigDecimal valorCusto;
	
	@Column(name = "NR_BASE_PRECO_VENDA")
	private Integer baseValorVenda;
	
	@Column(name = "VL_PERCENTUAL_VENDA")
	private Double percentualVenda;
	
	@Column(name = "VL_VENDA_PRODUTO")
	private BigDecimal valorVenda;
	
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

	public BigDecimal getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}

	public Integer getBaseValorVenda() {
		if (SiteUtil.emptyOrNull(baseValorVenda)) {
			return 0;
		}
		return baseValorVenda;
	}

	public void setBaseValorVenda(Integer baseValorVenda) {
		this.baseValorVenda = baseValorVenda;
	}

	public Double getPercentualVenda() {
		if (SiteUtil.emptyOrNull(percentualVenda)) {
			return 0.0d;
		}
		return percentualVenda;
	}

	public void setPercentualVenda(Double percentualVenda) {
		this.percentualVenda = percentualVenda;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
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
