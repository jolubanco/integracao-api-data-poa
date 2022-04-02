package br.com.integracaodatapoa.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.integracaodatapoa.apis.LinhaOnibusApi;
import br.com.integracaodatapoa.models.LinhaOnibusModel;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinhaOnibusDto {
	
	private String id;
	private String codigo;
	private String nome;
	
	public LinhaOnibusDto(LinhaOnibusApi linha) {
		this.id = linha.getId();
		this.codigo = linha.getCodigo();
		this.nome = linha.getNome();
	}

	public LinhaOnibusDto(LinhaOnibusModel linha) {
		this.id = linha.getId();
		this.codigo = linha.getCodigo();
		this.nome = linha.getNome();
	}

}
