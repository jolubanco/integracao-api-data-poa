package br.com.integracaodatapoa.controllers.forms;

import br.com.integracaodatapoa.models.LinhaOnibusModel;
import lombok.Data;

@Data
public class LinhaOnibusForm {
	
	private String codigo;
	private String nome;
	
	public LinhaOnibusModel converte() {
		return new LinhaOnibusModel(codigo,nome);
	}
}
