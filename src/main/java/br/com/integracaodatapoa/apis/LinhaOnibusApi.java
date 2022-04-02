package br.com.integracaodatapoa.apis;

import br.com.integracaodatapoa.models.LinhaOnibusModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinhaOnibusApi {
	
	private String id;
	private String codigo;
	private String nome;

	public LinhaOnibusModel converter() {
		return new LinhaOnibusModel(id,codigo,nome);
	}

}
