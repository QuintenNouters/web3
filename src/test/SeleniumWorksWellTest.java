package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumWorksWellTest {

		private WebDriver driver;

		@Before
		public void setUp() throws Exception {
			// pas aan indien nodig
			System.setProperty("webdriver.chrome.driver", "E:\\Quinten_DATA\\Documents\\School\\ChromeDriver\\chromedriver.exe");
			// windows: gebruik dubbele \\ om pad aan te geven
			// hint: zoek een werkende test op van web 2 ...
			driver = new ChromeDriver();
			driver.get("https://nl.wikipedia.org/wiki/Hoofdpagina");
		}


		@After
		public void clean(){
			driver.quit();
		}
		
		@Test 
		public void browserVindtWikipedia() {
			assertEquals("Wikipedia, de vrije encyclopedie", driver.getTitle());
		}

		@Test
		public void wikipediaVindtSelenium() {
			WebElement field = driver.findElement(By.id("searchInput"));
			field.clear();
			field.sendKeys("selenium");
			WebElement link = driver.findElement(By.id("searchButton"));
			link.click();
			
			assertEquals("Seleen - Wikipedia", driver.getTitle());
			
			assertEquals("Seleen", driver.findElement(By.tagName("h1")).getText());

	}

}
