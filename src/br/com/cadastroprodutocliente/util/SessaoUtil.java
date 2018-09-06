package br.com.cadastroprodutocliente.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.cadastroprodutocliente.model.Usuario;

public class SessaoUtil {

	private List<String> paginasComunicadas;
	private String identificadorObjeto;
	private Object objetoTemporario;

	public SessaoUtil() {
	}
	
	public SessaoUtil(String pagina) {
		this.paginasComunicadas = new ArrayList<String>();
		this.paginasComunicadas.add(pagina);
	}
	
	public SessaoUtil(String pagina, String identificador, Object objetoTemporario) {
		this.paginasComunicadas = new ArrayList<String>();
		this.paginasComunicadas.add(pagina);
		this.identificadorObjeto = identificador;
		this.objetoTemporario = objetoTemporario;
	}

	public static void gravarAreaFlash(String identificadorArea, Object object) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(identificadorArea, object);
	}

	public static Object consultarAreaFlash(String identificador) {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(identificador);
	}

	public static void gravarAreaSessionMap(SessaoUtil sessaoUtil) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("comunicacaotemporaria", sessaoUtil);
	}

	public static SessaoUtil consultarAreaSessionMap() {
		SessaoUtil comunicacaoTemporaria = (SessaoUtil) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("comunicacaotemporaria");
		if (SiteUtil.emptyOrNull(comunicacaoTemporaria)) {
			return null;
		}
		return comunicacaoTemporaria;
	}

	public static void limparAreaSessionMap() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("comunicacaotemporaria");
	}

	public static String obterCaminhoDePao() {
		String paginasAcessadas = "";
		SessaoUtil comunicacaoTemporaria = SessaoUtil.consultarAreaSessionMap();
		if (!SiteUtil.emptyOrNull(comunicacaoTemporaria)) {
			int aux = 0;
			for (String pagina : comunicacaoTemporaria.getPaginasComunicadas()) {
				if (aux == 0) {
					paginasAcessadas = pagina;
				} else {
					paginasAcessadas = paginasAcessadas + " - " + pagina;
				}
				aux++;
			}
		}
		return paginasAcessadas;
	}

	public static void gravarUsuarioSessao(Usuario usuario) {
		usuario.setSenha(null);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("usuario", usuario);
	}

	public static Usuario obterUsuarioSessao() {
		Usuario usuarioSessao = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuario");
		return usuarioSessao;
	}

	public static void finalizarSessao() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
	}

	public List<String> getPaginasComunicadas() {
		return paginasComunicadas;
	}

	public void setPaginasComunicadas(List<String> paginasComunicadas) {
		this.paginasComunicadas = paginasComunicadas;
	}

	public String getIdentificador() {
		return identificadorObjeto;
	}

	public Object getObjetoTemporario() {
		return objetoTemporario;
	}

}
