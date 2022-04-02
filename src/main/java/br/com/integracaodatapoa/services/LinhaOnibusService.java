package br.com.integracaodatapoa.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.integracaodatapoa.apis.LinhaOnibusApi;
import br.com.integracaodatapoa.controllers.dtos.LinhaOnibusDto;
import br.com.integracaodatapoa.controllers.forms.LinhaOnibusAtualizarForm;
import br.com.integracaodatapoa.controllers.forms.LinhaOnibusForm;
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

	public List<LinhaOnibusDto> listarLinhasDeOnibus() {
		List<LinhaOnibusModel> linhas = linhaOnibusRepository.findAll();
		List<LinhaOnibusDto> linhaDto = new ArrayList<>();
		linhas.forEach(linha -> {
			linhaDto.add(new LinhaOnibusDto(linha));
		});
		return linhaDto;
	}

	public ResponseEntity<?> consumirLinhaDeOnibus() {
		List<LinhaOnibusApi> api = LinhaOnibusApiService.consomeApi(restTemplate);
		salvarDB(api);
		return ResponseEntity.ok().build();
	}
	
	private void salvarDB(List<LinhaOnibusApi> linhas) {
		linhas.forEach(linha -> {
			linhaOnibusRepository.save(new LinhaOnibusModel(linha));
		});
	}

	public ResponseEntity<LinhaOnibusDto> exibirLinhaDeOnibus(String idLinha) {
		Optional<LinhaOnibusModel> linhaOnibus = linhaOnibusRepository.findById(idLinha);
		if(linhaOnibus.isPresent()) {
			return ResponseEntity.ok(new LinhaOnibusDto(linhaOnibus.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<LinhaOnibusDto> exibirLinhaDeOnibusFiltradaPeloNome(String nomeLinha) {
		Optional<LinhaOnibusModel> linhaOnibus = linhaOnibusRepository.findByNomeLike(nomeLinha);
		if(linhaOnibus.isPresent()) {
			return ResponseEntity.ok(new LinhaOnibusDto(linhaOnibus.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<LinhaOnibusDto> cadastrarLinhaOnibus(LinhaOnibusForm form, UriComponentsBuilder uriBuilder) {
		
		LinhaOnibusModel linhaOnibus = form.converte();
		Optional<LinhaOnibusModel> optional = linhaOnibusRepository.findByCodigo(linhaOnibus.getCodigo());
		
		if(optional.isEmpty()) {
			
			linhaOnibusRepository.save(linhaOnibus);
			URI uri = uriBuilder.path("/linhaOnibus/{id}").buildAndExpand(linhaOnibus.getId()).toUri();
			return ResponseEntity.created(uri).body(new LinhaOnibusDto(linhaOnibus));
			
		} else {
			
			optional.get().setNome(linhaOnibus.getNome());
			optional.get().setCodigo(linhaOnibus.getCodigo());//devo alterar o codigo?
			linhaOnibusRepository.save(optional.get());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		}

	}

	public ResponseEntity<?> deletarLinhaDeOnibus(String idLinha) {
		Optional<LinhaOnibusModel> linhaOnibus = linhaOnibusRepository.findById(idLinha);
		if(linhaOnibus.isPresent()) {
			linhaOnibusRepository.delete(linhaOnibus.get());
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	//rever logica
	public ResponseEntity<LinhaOnibusDto> atualizarLinhaDeOnibus(LinhaOnibusAtualizarForm form) {
		Optional<LinhaOnibusModel> linhaOnibus = linhaOnibusRepository.findById(form.getId());
		if(linhaOnibus.isPresent()) {
			if (!form.getNome().isBlank() && !form.getCodigo().isBlank()) {
				linhaOnibus.get().setNome(form.getNome());
				linhaOnibus.get().setCodigo(form.getCodigo());
				linhaOnibusRepository.save(linhaOnibus.get());
				return ResponseEntity.noContent().build();
			} else if(!form.getNome().isBlank()) {
				linhaOnibus.get().setNome(form.getNome());
				linhaOnibusRepository.save(linhaOnibus.get());
				return ResponseEntity.noContent().build();
			} else if (!form.getCodigo().isBlank()){
				linhaOnibus.get().setCodigo(form.getCodigo());
				linhaOnibusRepository.save(linhaOnibus.get());
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.badRequest().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
