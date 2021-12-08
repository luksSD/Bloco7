package br.fai.bloco7.web.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PessoaRegisterTest {

	private final WebDriver driver;

	public PessoaRegisterTest() {

		System.out.println("Inciando construtor");
		this.driver = new ChromeDriver();
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

	@SuppressWarnings("deprecation")
	@Test
	public void testGetRegisterPage() {
		driver.get("http://localhost:1000/");

		final WebElement target1 = driver.findElement(By.xpath("//span[text()='Conta']"));
		final WebElement target2 = driver.findElement(By.xpath("//a[text()=' Registrar ']"));
		final Actions s = new Actions(driver);
		s.moveToElement(target1);
		s.click(target2);
		s.build().perform();

	}

}
