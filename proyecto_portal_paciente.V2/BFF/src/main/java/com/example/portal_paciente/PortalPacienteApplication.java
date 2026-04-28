package com.example.portal_paciente;

import BackOffice.portal_paciente.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages ={"com.example.portal_paciente","BackOffice"})
@EntityScan(basePackages = {"BackOffice", "com.example.portal_paciente"})
public class PortalPacienteApplication {
	public static void main(String[] args) {
		SpringApplication.run(PortalPacienteApplication.class, args);
	}
	@Value("${backend.api.url}")
	private String backendApiUrl;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@Primary
	public ApiClient apliclient() {
		ApiClient client = new ApiClient();
		client.setBasePath(backendApiUrl);
		return client;
	}

}
