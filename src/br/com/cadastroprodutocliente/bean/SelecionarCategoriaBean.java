package br.com.cadastroprodutocliente.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cadastroprodutocliente.dao.CategoriaDao;
import br.com.cadastroprodutocliente.dao.ICategoriaDao;
import br.com.cadastroprodutocliente.model.Categoria;
import br.com.cadastroprodutocliente.model.Produto;
import br.com.cadastroprodutocliente.util.FacesMessageUtil;
import br.com.cadastroprodutocliente.util.Mensagens;
import br.com.cadastroprodutocliente.util.SiteUtil;

@ManagedBean
@ViewScoped
public class SelecionarCategoriaBean {

	private List<Categoria> categorias;
	private ICategoriaDao categoriaDao;
	private Categoria selecionado;
	private Produto novoProduto;
	private String paginaAnterior;
	private Object temporario;

	@PostConstruct
	public void inicializar() {
		categoriaDao = new CategoriaDao();
		categorias = categoriaDao.listarCategoria();
		selecionado = new Categoria();
		novoProduto = (Produto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("produto");
		paginaAnterior = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("paginaanterior");
		temporario = FacesContext.getCurrentInstance().getExternalContext().getFlash().get("temporario");
	}
	
	public String confirmarSelecao() {
		if (selecionadoValido()) {
			novoProduto.setCategoria(selecionado);
			gravarMemoriaFLash();
			return paginaAnterior;
		}
		return null;
	}
	
	public String voltar() {
		gravarMemoriaFLash();
		return paginaAnterior;
	}
	
	public boolean selecionadoValido() {
		if (SiteUtil.emptyOrNull(selecionado)) {
			FacesMessageUtil.addMenssage(Mensagens.NENHUM_REGISTRO_SELECIONADO);
			return false;
		}
		return true;
	}
	
	public void gravarMemoriaFLash() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("produto", novoProduto);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("temporario", temporario);
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public ICategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(ICategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	public Categoria getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Categoria selecionado) {
		this.selecionado = selecionado;
	}

	public Produto getNovoProduto() {
		return novoProduto;
	}

	public void setNovoProduto(Produto novoProduto) {
		this.novoProduto = novoProduto;
	}

	public String getPaginaAnterior() {
		return paginaAnterior;
	}

	public void setPaginaAnterior(String paginaAnterior) {
		this.paginaAnterior = paginaAnterior;
	}
	
}
