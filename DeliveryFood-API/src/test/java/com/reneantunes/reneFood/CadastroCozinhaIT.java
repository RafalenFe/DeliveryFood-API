package com.reneantunes.reneFood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.reneantunes.reneFood.domain.model.Cozinha;
import com.reneantunes.reneFood.domain.repository.CozinhaRepository;
import com.reneantunes.reneFood.util.DatabaseCleaner;
import com.reneantunes.reneFood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {
	
	private static final int Id_Cozinha_Inexistente = 100;
	
	private Cozinha cozinhaAmericana;
	private int quantidadeCozinhasCadastradas;
	private String jsonCorretoCozinhaChinesa;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner dataBaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	
	
	@BeforeEach
	private void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource(
				"/json/correto/cozinha-chinesa.json");
		
		dataBaseCleaner.clearTables();
		prepararDados();
		
	}
	
	@Test	
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
			given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
		}	
	
		@Test	
		public void deveRetornarQuantidadeCorretaDeCozinhas_QuandoConsultarCozinhas() {
				given()
					.accept(ContentType.JSON)
				.when()
					.get()
				.then()
					.body("", hasSize(quantidadeCozinhasCadastradas));
			}	
	
		@Test
		public void deveRetornarStatus201_QuandoCadastrarCozinha() {
			given()
				.body(jsonCorretoCozinhaChinesa)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.CREATED.value());
		}
		
		@Test
		public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
			given()
				.pathParam("cozinhaId", cozinhaAmericana.getId())
				.accept(ContentType.JSON)
			.when()
				.get("/{cozinhaId}")
			.then()
				.statusCode(HttpStatus.OK.value())
				.body("nome", equalTo(cozinhaAmericana.getNome()));
		}

		@Test
		public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
			given()
				.pathParam("cozinhaId", Id_Cozinha_Inexistente)
				.accept(ContentType.JSON)
			.when()
				.get("/{cozinhaId}")
			.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
			
		}
		
		private void prepararDados() {
			Cozinha cozinhaTailandesa = new Cozinha();
			cozinhaTailandesa.setNome("Tailandesa");
			cozinhaRepository.save(cozinhaTailandesa);
			
			cozinhaAmericana = new Cozinha();
			cozinhaAmericana.setNome("Americana");
			cozinhaRepository.save(cozinhaAmericana);
			
			quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
		}
}
