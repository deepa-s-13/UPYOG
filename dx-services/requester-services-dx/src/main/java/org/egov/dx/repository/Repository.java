package org.egov.dx.repository;

import java.util.HashMap;
import java.util.Map;

import org.egov.dx.util.PTServiceDXConstants;
import org.egov.tracer.model.CustomException;
import org.egov.tracer.model.ServiceCallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@org.springframework.stereotype.Repository
@Slf4j
public class Repository {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	//@Qualifier("secondaryMapper")
	private ObjectMapper mapper;
		
	/**
	 * Fetches results from external services through rest call.
	 * 
	 * @param request
	 * @param uri
	 * @return Object
	 */
	public Object fetchResult(StringBuilder uri, Object request) {
		
		Object response = null;
		log.info("URI: " + uri.toString());
		try {
			log.info(mapper.writeValueAsString(request));
			response = restTemplate.postForObject(uri.toString(), request, Map.class);
		} catch (ResourceAccessException e) {
			
			Map<String, String> map = new HashMap<>();
			map.put(PTServiceDXConstants.CONNECT_EXCEPTION_KEY, e.getMessage());
			throw new CustomException(map);
		}  catch (HttpClientErrorException e) {

			log.info("the error is : " + e.getResponseBodyAsString());
			throw new ServiceCallException(e.getResponseBodyAsString());
		}catch (Exception e) {

			log.error("Exception while fetching from searcher: ", e);
		}
		return response;
	}
}
