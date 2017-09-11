/**
 * 
 */
package com.plivo.smsgateway.integration.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Base64Utils;

import com.plivo.smsgateway.repo.AccountRepository;
import com.plivo.smsgateway.repo.PhoneNumberRepo;
import com.plivo.smsgateway.server.Application;

/**
 * @author abhineet
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration.properties")
public class SMSGatewayIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
    private AccountRepository employeeRepository;
	
	@MockBean
	private PhoneNumberRepo phoneNumberRepo;
	
	@Test
	public void testNegativeAuth() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/hello")
				.header(HttpHeaders.AUTHORIZATION,
						"Basic " + Base64Utils.encodeToString("plivo2:xxxxxxx".getBytes()))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}
}
