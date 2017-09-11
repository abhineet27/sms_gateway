/**
 * 
 */
package com.plivo.smsgateway.integration.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.plivo.smsgateway.domain.Message;
import com.plivo.smsgateway.domain.Response;

/**
 * @author abhineet
 *
 */
public class SMSGatewayTest {

	private RestTemplate restTemplate;

	private static final String BASE_URL = "http://ec2-52-21-154-2.compute-1.amazonaws.com:8080/sms-gateway/api";

	@Before
	public void beforeTest() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void negativeAuthTest() {

		TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplate,"plivo2", "passowrd");
        ResponseEntity<String> response = testRestTemplate.getForEntity(BASE_URL + "/hello", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.FORBIDDEN));
	}
	
	@Test
	public void positiveAuthTest() {

		TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplate,"plivo2", "54P2EOKQ47");
        ResponseEntity<String> response = testRestTemplate.getForEntity(BASE_URL + "/hello", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}
	
	@Test
	public void inboundValidationSmsTest1() {

		TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplate,"plivo2", "54P2EOKQ47");
		HttpEntity<Message> request = new HttpEntity<>(new Message("from","to","text"));
		ResponseEntity<Response> httpResponse = testRestTemplate.exchange(BASE_URL + "/inbound/sms", HttpMethod.POST, request, Response.class);
		Response response = httpResponse.getBody();
		assertThat(response.getError(), is("from is invalid"));
	}
	
	@Test
	public void inboundValidationSmsTest2() {

		TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplate,"plivo2", "54P2EOKQ47");
		HttpEntity<Message> request = new HttpEntity<>(new Message("123456","123456","text"));
		ResponseEntity<Response> httpResponse = testRestTemplate.exchange(BASE_URL + "/inbound/sms", HttpMethod.POST, request, Response.class);
		Response response = httpResponse.getBody();
		assertThat(response.getError(), is("to parameter not found"));
	}
	
	@Test
	public void outboundValidationSmsTest1() {

		TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplate,"plivo2", "54P2EOKQ47");
		HttpEntity<Message> request = new HttpEntity<>(new Message("123456","123456","text"));
		ResponseEntity<Response> httpResponse = testRestTemplate.exchange(BASE_URL + "/outbound/sms", HttpMethod.POST, request, Response.class);
		Response response = httpResponse.getBody();
		assertThat(response.getError(), is("from parameter not found"));
	}
	
	@Test
	public void outboundValidationSmsTest2() {

		TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplate,"plivo2", "54P2EOKQ47");
		HttpEntity<Message> request = new HttpEntity<>(new Message("123456","xxxx","text"));
		ResponseEntity<Response> httpResponse = testRestTemplate.exchange(BASE_URL + "/outbound/sms", HttpMethod.POST, request, Response.class);
		Response response = httpResponse.getBody();
		assertThat(response.getError(), is("to is invalid"));
	}
	
	@Test
	public void inboundSmsOkTest() {

		TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplate,"plivo2", "54P2EOKQ47");
		HttpEntity<Message> request = new HttpEntity<>(new Message("441235330075","16052299352","text"));
		ResponseEntity<Response> httpResponse = testRestTemplate.exchange(BASE_URL + "/inbound/sms", HttpMethod.POST, request, Response.class);
		Response response = httpResponse.getBody();
		assertThat(response.getMessage(), is("inbound sms ok"));
		assertThat(response.getError(), is(""));
	}
	
	@Test
	public void outboundSmsOkTest() {

		TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplate,"plivo3", "9LLV6I4ZWI");
		HttpEntity<Message> request = new HttpEntity<>(new Message("441235330075","16052299352","text"));
		ResponseEntity<Response> httpResponse = testRestTemplate.exchange(BASE_URL + "/outbound/sms", HttpMethod.POST, request, Response.class);
		Response response = httpResponse.getBody();
		assertThat(response.getMessage(), is("outbound sms ok"));
		assertThat(response.getError(), is(""));
	}
}
