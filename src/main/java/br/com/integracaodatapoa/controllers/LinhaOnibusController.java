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
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/linhaOnibus")
public class LinhaOnibusController {
	
	@Autowired
	private LinhaOnibusService linhaOnibusService;
	
	@ApiOperation(value = "Lista todas as linhas de ônibus ou filtra pelo o nome da linha, caso o parâmetro 'nome' seja informado via URL")
	@GetMapping
	public ResponseEntity<List<LinhaOnibusDto>> listaLinhasDeOnibus(String nome){
		return linhaOnibusService.listarLinhasDeOnibus(nome);
	}
	
	@ApiOperation(value = "Exibe a linha de ônibus filtrando pelo ID")
	@GetMapping("/{idLinha}")
	public ResponseEntity<LinhaOnibusDto> exibeLinhaDeOnibus(@PathVariable("idLinha") String IdLinha) {
		return linhaOnibusService.exibirLinhaDeOnibus(IdLinha);
	}
	
	@ApiOperation(value = "Verifica se a linha de ônibus informada existe, caso exista atualiza as informações se não cadastra uma nova linha")
	@PostMapping
	@Transactional 
	public ResponseEntity<LinhaOnibusDto> cadastraLinhaDeOnibus(@RequestBody LinhaOnibusForm form, UriComponentsBuilder uriBuilder) {
		return linhaOnibusService.cadastrarLinhaOnibus(form,uriBuilder);
	}
	
	@ApiOperation(value = "Consome as informações da Api Data Poa e salva os dados no banco de dados")
	@PostMapping("/consomeApi")
	public ResponseEntity<?> consomeApiDataPoa() {
		return linhaOnibusService.consumirLinhaDeOnibus();	
	}
	
	@ApiOperation(value = "Atualiza os dados da linha de ônibus que foram informados, sendo o 'nome' ou 'código'")
	@PutMapping
	public ResponseEntity<LinhaOnibusDto> atualizaLinhaDeOnibus(@RequestBody LinhaOnibusAtualizarForm form) {
		return linhaOnibusService.atualizarLinhaDeOnibus(form);
	}
	
	@ApiOperation(value = "Deleta uma linha de ônibus a partir do ID")
	@DeleteMapping("/{idLinha}")
	public ResponseEntity<?> deletaLinhaDeOnibus(@PathVariable("idLinha") String idLinha){
		return linhaOnibusService.deletarLinhaDeOnibus(idLinha);
	}
	

}
