package br.edu.ifpb.projeto.siteIT;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class UsuarioLoginIT {
	private WebDriver driver;
	private String localhost = "http://localhost:8080/estagios/";

	@Before
	public void setUp() {
		driver = new HtmlUnitDriver();
		driver.manage().deleteAllCookies();

		// Abre a página
		driver.get(localhost + "usuario/login");
	}

	@Test
	public void testa_preenchimento_formulario() {
		WebElement button = driver.findElement(By.tagName("button"));
		button.submit();

		assertEquals(true, driver.getPageSource().contains("O campo email precisa ser preenchido."));
	}

	@Test
	public void testa_login_incorreto() {
		WebElement button = driver.findElement(By.tagName("button"));
		WebElement email = driver.findElement(By.id("email-input"));
		WebElement senha = driver.findElement(By.id("senha-input"));

		email.sendKeys("nao@existe.com");
		senha.sendKeys("password");
		button.submit();

		assertEquals(true, driver.getPageSource().contains("E-mail ou senha incorretos"));
	}

/*	@Test
*	public void testa_login_correto() {
*		WebElement button = driver.findElement(By.tagName("button"));
*		WebElement email = driver.findElement(By.id("email-input"));
*		WebElement senha = driver.findElement(By.id("senha-input"));
*
*		email.sendKeys("mail@mail.com");
*		senha.sendKeys("password");
*		button.submit();
*
*		assertEquals(localhost, driver.getCurrentUrl());
*	}
*/
}
