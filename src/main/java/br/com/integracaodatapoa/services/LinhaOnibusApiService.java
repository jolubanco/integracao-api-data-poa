package br.com.integracaodatapoa.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.integracaodatapoa.apis.LinhaOnibusApi;
import br.com.integracaodatapoa.controllers.dtos.LinhaOnibusDto;

@Service
public class LinhaOnibusApiService {
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	public static List<LinhaOnibusApi> consomeApi(RestTemplate restTemplate){

		//CONVERSOR DE TODAS AS MIDIAS PARA JSON
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));         
		messageConverters.add(converter);  
		restTemplate.setMessageConverters(messageConverters);  
		//
		
		String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
		
		ResponseEntity<List<LinhaOnibusApi>> response = restTemplate.exchange(
		  url,
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<LinhaOnibusApi>>(){});
		
		return response.getBody();	
	}

}
