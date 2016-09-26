package com.soloxis.todo.controllers;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.soloxis.todo.models.Todo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoControllerTest {

	private RestTemplate restTemplate;
	private static final String APPURL = "http://localhost:8080/api";

	@Before
	public void beforeTest() {
		restTemplate = new RestTemplate(getClientHttpRequestFactory());

		checkOneEntityExsits();
	}
	
	
	//GET
	@Test
	public void testGetMethod() {
		final ResponseEntity<String> response = restTemplate.getForEntity(APPURL, String.class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));

	}

	private void checkOneEntityExsits() {
		Calendar cal = Calendar.getInstance();
		Todo todo = new Todo("testEntity", true, new Date(cal.getTime().getTime()));
		restTemplate.postForEntity(APPURL, todo, Todo.class);

	}

	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		final int timeout = 5;
		final RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout * 1000)
				.setConnectionRequestTimeout(timeout * 1000).setSocketTimeout(timeout * 1000).build();

		final CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

		return new HttpComponentsClientHttpRequestFactory(client);
	}

}
