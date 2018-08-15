package br.com.cadastroprodutocliente.util;

import javax.persistence.AttributeConverter;

import br.com.cadastroprodutocliente.model.NivelAcesso;

public class NivelAcessoConverter implements AttributeConverter<NivelAcesso, Integer>{

	@Override
	public Integer convertToDatabaseColumn(NivelAcesso nivelAcesso) {
		return nivelAcesso.getId();
	}

	@Override
	public NivelAcesso convertToEntityAttribute(Integer integer) {
		return NivelAcesso.deId(integer);
	}

}
