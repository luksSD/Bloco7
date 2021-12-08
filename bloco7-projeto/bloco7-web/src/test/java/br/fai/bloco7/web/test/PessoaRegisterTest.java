package br.fai.bloco7.web.test;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PessoaRegisterTest {

	private final WebDriver driver;

	public PessoaRegisterTest() {

		System.out.println("Inciando construtor");
		this.driver = new ChromeDriver();
		driver.get("https://www.fai-mg.br/faionline/");
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		System.out.println("Configurando a classe de teste");
		System.setProperty("webdriver.chrome.driver", "L://chromedriver.exe");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRegisterPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreatePessoa() {
		fail("Not yet implemented");
	}

}
