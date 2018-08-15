package br.com.cadastroprodutocliente.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Column(name = "HR_MANUT_CATEGORIA_PRODUTO")
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
