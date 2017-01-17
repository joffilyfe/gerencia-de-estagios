package br.edu.ifpb.projeto.siteIT;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class TesteSiteControllerIT {
	private WebDriver driver;
	private String localhost = "http://localhost:8080/estagios";

	@Before
	public void setUp() {
		driver = new HtmlUnitDriver();

		// Abre a página
		driver.get(localhost);
	}

	@Test
	public void testa_botao_de_codigo_fonte() {
		WebElement element = driver.findElement(By.className(("btn-success")));
		assertEquals("Código fonte", element.getText());
	}

	@Test
	public void testa_link_para_home() {
		WebElement el = driver.findElement(By.linkText("Home"));
		assertEquals(localhost, el.getAttribute("href"));
	}
}
