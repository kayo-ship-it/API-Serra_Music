package org.serratec.serratec_music.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl("http://localhost:8080");
		devServer.setDescription("Servidor Local");
		
		Contact contato = new Contact();
		contato.setEmail("kayo.l.conceicao@aluno.senai.br");
		contato.setName("Kayo Rentes");
		contato.setUrl("https://www.linkedin.com/in/kayo-rentes-da-conceição-5b4bb4226/");
		
		License apacheLicense = new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html");
		
		Info info = new Info()
				.title("API - Serratec Music")
				.version("1.0")
				.description("API para gerenciamento de músicas")
				.contact(contato)
				.termsOfService("http://swagger.io/terms/")
				.license(apacheLicense);
		
		return new OpenAPI().info(info).servers(List.of(devServer));
	}
}
