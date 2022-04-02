package br.com.integracaodatapoa.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.integracaodatapoa.apis.LinhaOnibusApi;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LinhaOnibusModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String codigo;
	private String nome;
	
	public LinhaOnibusModel(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public LinhaOnibusModel(LinhaOnibusApi linha) {
		this.id= linha.getId();
		this.codigo = linha.getCodigo();
		this.nome = linha.getNome();
	}

}
