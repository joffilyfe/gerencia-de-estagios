package br.edu.ifpb.projeto.siteIT;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class UsuarioCadastroIT {
	private WebDriver driver;
	private String localhost = "http://localhost:8080/estagios/";

	@Before
	public void setUp() {
		driver = new HtmlUnitDriver();
		driver.manage().deleteAllCookies();

		// Abre a página
		driver.get(localhost + "usuario/cadastro");
	}

	@Test
	public void testa_preenchimento_formulario() {
		WebElement button = driver.findElement(By.tagName("button"));
		button.submit();

		assertEquals(true, driver.getPageSource().contains("O campo nome precisa ser preenchido."));
	}

	@Test
	public void testa_cadastro_realizado_com_sucesso() {
		WebElement button = driver.findElement(By.tagName("button"));
		WebElement nome = driver.findElement(By.id("nome-input"));
		WebElement email = driver.findElement(By.id("email-input"));
		WebElement senha = driver.findElement(By.id("senha-input"));

		List<WebElement> tipo = driver.findElements(By.name("tipo"));

		nome.sendKeys("usuario de testes");
		email.sendKeys("mail@mail.com");
		senha.sendKeys("password");
		tipo.get(0).click();
		button.submit();

		assertEquals(localhost, driver.getCurrentUrl());
	}

}
