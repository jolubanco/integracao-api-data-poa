package br.com.integracaodatapoa.controllers;

import java.util.List;

import javax.transaction.Transactional;

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
import br.com.integracaodatapoa.controllers.forms.LinhaOnibusAtualizarForm;
import br.com.integracaodatapoa.controllers.forms.LinhaOnibusForm;
import br.com.integracaodatapoa.services.LinhaOnibusService;

@RestController
@RequestMapping("/linhaOnibus")
public class LinhaOnibusController {
	
	@Autowired
	private LinhaOnibusService linhaOnibusService;
	
	@GetMapping//funciona na primeira consulta, na segunda da erro(por causa do like). TIRANDO O LIKE FUNCIONA NORMAL
	public ResponseEntity<List<LinhaOnibusDto>> listaLinhasDeOnibus(String nome){
		return linhaOnibusService.listarLinhasDeOnibus(nome);
	}
	
	@GetMapping("/{idLinha}")
	public ResponseEntity<LinhaOnibusDto> exibeLinhaDeOnibus(@PathVariable("idLinha") String IdLinha) {
		return linhaOnibusService.exibirLinhaDeOnibus(IdLinha);
	}
	
	@PostMapping //verifica se existe ou não
	@Transactional //nao estava atualizando
	public ResponseEntity<LinhaOnibusDto> cadastraLinhaDeOnibus(@RequestBody LinhaOnibusForm form, UriComponentsBuilder uriBuilder) {
		return linhaOnibusService.cadastrarLinhaOnibus(form,uriBuilder);
	}
	
	@PostMapping("/consomeApi")
	public ResponseEntity<?> consomeApiDataPoa() {
		return linhaOnibusService.consumirLinhaDeOnibus();	
	}
	
	@PutMapping
	public ResponseEntity<LinhaOnibusDto> atualizaLinhaDeOnibus(@RequestBody LinhaOnibusAtualizarForm form) {
		return linhaOnibusService.atualizarLinhaDeOnibus(form);
	}
	
	@DeleteMapping("/{idLinha}")
	public ResponseEntity<?> deletaLinhaDeOnibus(@PathVariable("idLinha") String idLinha){
		return linhaOnibusService.deletarLinhaDeOnibus(idLinha);
	}
	

}
