package br.com.integracaodatapoa.controllers.forms;

import java.util.Optional;

import br.com.integracaodatapoa.models.LinhaOnibusModel;
import br.com.integracaodatapoa.repositories.LinhaOnibusRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LinhaOnibusAtualizarForm {
	
	private String id;
	private String codigo;
	private String nome;

}
