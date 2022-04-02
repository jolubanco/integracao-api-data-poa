package br.com.integracaodatapoa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.integracaodatapoa.controllers.dtos.LinhaOnibusDto;
import br.com.integracaodatapoa.controllers.forms.LinhaOnibusForm;
import br.com.integracaodatapoa.services.LinhaOnibusService;

@RestController
@RequestMapping("/linhaOnibus")
public class LinhaOnibusController {
	
	@Autowired
	private LinhaOnibusService linhaOnibusService;
	
	@GetMapping
	public List<LinhaOnibusDto> listaLinhasDeOnibus(){
		return linhaOnibusService.listaLinhasDeOnibus();
	}
	
	@GetMapping("/{idLinha}")
	public ResponseEntity<LinhaOnibusDto> exibeLinhaDeOnibus(@PathVariable("idLinha") String IdLinha) {
		return null;
	}
	
	@GetMapping("/filtroNome/{nomeLinha}") //pode ser uma lista
	public ResponseEntity<List<LinhaOnibusDto>> filtraLinhaDeOnibusPorNome(@PathVariable("nomeLinha") String nomeLinha) {
		return null;
	}
	
	@PostMapping
	public ResponseEntity<LinhaOnibusDto> cadastraLinhaDeOnibus(@RequestBody LinhaOnibusForm form, UriComponentsBuilder uriBuilder) {
		return null;
	}
	
	@PostMapping("/consomeApi")
	public ResponseEntity<?> consomeApiDataPoa() {
		return linhaOnibusService.cadastraLinhaDeOnibus();	
	}
	
	@PutMapping
	public ResponseEntity<LinhaOnibusDto> atualizaLinhaDeOnibus(@RequestBody LinhaOnibusForm form) {
		return null;
	}
	
	@DeleteMapping("/{idLInha}")
	public ResponseEntity<?> deletaLinhaDeOnibus(@PathVariable("idLinha") String idLinha){
		return null;
	}
	

}
