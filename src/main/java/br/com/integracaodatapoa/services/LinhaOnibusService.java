package br.com.integracaodatapoa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.integracaodatapoa.apis.LinhaOnibusApi;
import br.com.integracaodatapoa.controllers.dtos.LinhaOnibusDto;
import br.com.integracaodatapoa.models.LinhaOnibusModel;
import br.com.integracaodatapoa.repositories.LinhaOnibusRepository;

@Service
public class LinhaOnibusService {
	
	private LinhaOnibusRepository linhaOnibusRepository;
	private RestTemplate restTemplate;
	
	@Autowired
	public LinhaOnibusService(LinhaOnibusRepository linhaOnibusRepository,RestTemplate restTemplate) {
		this.linhaOnibusRepository = linhaOnibusRepository;
		this.restTemplate = restTemplate;
	}

	public List<LinhaOnibusDto> listaLinhasDeOnibus() {
		List<LinhaOnibusModel> linhas = linhaOnibusRepository.findAll();
		List<LinhaOnibusDto> linhaDto = new ArrayList<>();
		linhas.forEach(linha -> {
			linhaDto.add(new LinhaOnibusDto(linha));
		});
		return linhaDto;
	}

	public ResponseEntity<?> cadastraLinhaDeOnibus() {
		List<LinhaOnibusApi> api = LinhaOnibusApiService.consomeApi(restTemplate);
		salvarDB(api);
		return ResponseEntity.ok().build();
	}
	
	private void salvarDB(List<LinhaOnibusApi> linhas) {
		linhas.forEach(linha -> {
			linhaOnibusRepository.save(new LinhaOnibusModel(linha));
		});
	}

	private List<LinhaOnibusDto> converterDto(List<LinhaOnibusApi> linhas) {
		List<LinhaOnibusDto> linhaOnibusDto = new ArrayList<>();
		linhas.forEach(linha -> {
			linhaOnibusDto.add(new LinhaOnibusDto(linha));
			linhaOnibusRepository.save(new LinhaOnibusModel(linha));
		});
		return linhaOnibusDto;
	}

}
