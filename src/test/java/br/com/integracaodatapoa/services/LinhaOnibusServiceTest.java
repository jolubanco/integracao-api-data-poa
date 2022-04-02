package br.com.integracaodatapoa.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import br.com.integracaodatapoa.controllers.dtos.LinhaOnibusDto;

public class LinhaOnibusServiceTest {
	

	public static void main(String[] args){
		
		RestTemplate restTemplate = new RestTemplate();

		//CONVERSOR DE TODAS AS MIDIAS PARA JSON
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));         
		messageConverters.add(converter);  
		restTemplate.setMessageConverters(messageConverters);  
		//
		
		String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
		
		ResponseEntity<List<LinhaOnibusDto>> response = restTemplate.exchange(
		  url,
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<LinhaOnibusDto>>(){});
		
		List<LinhaOnibusDto> linhasDeOnibus = response.getBody(); 
		System.out.println(linhasDeOnibus);
	}

}
