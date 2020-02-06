package test;



import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestSelenium {
	
		private static final String PASS = "pass";

		@Test
		void testSelenium(){
		
		//Simple test to check the new Insertion menu
		//First you log in and go back to main page
		//You create a new insertion with test data
		//You search again for the insertion using the title
		//You click on the last result and see if the information matches
		//Creator : Davide Leoni
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		driver.get("http://localhost:8080/Dynamic_Scambio/index.jsp");
		
		//register
		driver.findElement(By.xpath("/html/body/form[2]/button")).click();
		//Register page
		driver.findElement(By.xpath("//*[@id=\"type\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"enroll\"]")).sendKeys("A1234");
		driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("Davide");
		driver.findElement(By.xpath("//*[@id=\"surname\"]")).sendKeys("Leoni");
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("dav");
		driver.findElement(By.xpath("//*[@id=\"newpsw\"]")).sendKeys(PASS);
		driver.findElement(By.xpath("//*[@id=\"checkpsw\"]")).sendKeys(PASS);
		driver.findElement(By.xpath("/html/body/form/p[9]/input")).click();
		//Back to index
		
		driver.findElement(By.xpath("/html/body/form[3]/button")).click(); //click button to login
		//login page
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("dav");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(PASS);
		driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
		//profile page
		driver.findElement(By.xpath("/html/body/a[6]")).click();
		//back to main page
		driver.findElement(By.xpath("/html/body/form[5]/button")).click();
		//new Insertion Page
		driver.findElement(By.xpath("//*[@id=\"title\"]")).sendKeys("Test Insertion");
		driver.findElement(By.xpath("//*[@id=\"uni\"]")).sendKeys("Test Description");
		driver.findElement(By.xpath("//*[@id=\"price\"]")).sendKeys("23");
		driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys("Test City");
		driver.findElement(By.xpath("//*[@id=\"subj\"]")).sendKeys("Test Subject");
		driver.findElement(By.xpath("//*[@id=\"desc\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"desc\"]")).sendKeys("Test Description");
		driver.findElement(By.xpath("//*[@id=\"book\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"notes\"]")).click();
		driver.findElement(By.xpath("/html/body/form/p[5]/input")).click(); //Click on the insert button
		//Back to main page
		
		driver.findElement(By.xpath("//*[@id=\"research\"]")).sendKeys("Test Insertion");
		driver.findElement(By.xpath("/html/body/form[4]/input[8]")).click(); //Search for the previously inserted Insertion
		//Research page
		
		driver.findElement(By.xpath("/html/body/form[3]/button")).click();
		//Insertion detail page
		WebElement txtBoxContent = driver.findElement(By.xpath("/html/body/p[1]"));
		
		assertEquals(txtBoxContent.getText(),"Title : Test Insertion Price : 23 Seller : Data : "+ LocalDate.now().toString());
		
		driver.close();
		
	}
}
