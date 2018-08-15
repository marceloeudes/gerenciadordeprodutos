package br.com.cadastroprodutocliente.model;

import java.util.Arrays;
import java.util.List;

public enum NivelAcesso {

	ADMINISTRADOR(1, "Administrador"),
	FUNCIONARIO(2, "Funcionário");
	
	private int id;
	private String descricao;
	
	private NivelAcesso (int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static List<NivelAcesso> listarNivelAcesso(){
		return Arrays.asList(NivelAcesso.values());
	}

	public static final NivelAcesso deId(int id) {
        for(NivelAcesso nivelAcesso : NivelAcesso.values()) {
            if(nivelAcesso.id == id) {
                return nivelAcesso;
            }
        }
        throw new IllegalArgumentException("Nivel de acesso inválido: " + id);
    }
	
}
