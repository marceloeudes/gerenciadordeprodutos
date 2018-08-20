package br.com.cadastroprodutocliente.model;

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

@Entity(name="TB_CATEGORIA_PRODUTO")
public class Categoria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CD_CATEGORIA_PRODUTO")
	private int codigo;
	
	@Column(name="NM_CATEGORIA_PRODUTO")
	private String nome;
	
	@Column(name = "HR_INCL_CATEGORIA_PRODUTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHoraInclusao;
	
	@ManyToOne
	@JoinColumn(name = "CD_USUARIO_INCL_CATEGORIA_PRODUTO")
	private Usuario usuarioInclusao;
	
	@Column(name = "HR_MANUT_CATEGORIA_PRODUTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHoraManutencao;
	
	@ManyToOne
	@JoinColumn(name = "CD_USUARIO_MANUT_CATEGORIA_PRODUTO")
	private Usuario usuarioManutencao;
	
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
	public Calendar getDataHoraInclusao() {
		return dataHoraInclusao;
	}
	public void setDataHoraInclusao(Calendar dataHoraInclusao) {
		this.dataHoraInclusao = dataHoraInclusao;
	}
	public Usuario getusuarioInclusao() {
		return usuarioInclusao;
	}
	public void setusuarioInclusao(Usuario usuarioInclusao) {
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
