package first;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class question1 {
	public static void main (String args[]) throws InterruptedException
	{
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.findElement(By.xpath("//div[@class='form-group']//input[@name='name']")).sendKeys("Alekhya");
		driver.findElement(By.name("email")).sendKeys("alekhanalam8331@gmail.com");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("Alekhya@2005");
		driver.findElement(By.xpath("//input[@id='exampleCheck1']")).click();
		WebElement genderDropdown = driver.findElement(By.id("exampleFormControlSelect1"));
		Select selectGender = new Select(genderDropdown);
		selectGender.selectByVisibleText("Female");
		driver.findElement(By.xpath("//input[@id='inlineRadio1']")).click();
		WebElement dobInput = driver.findElement(By.name("bday"));
		dobInput.sendKeys("19012005");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(4000);
		driver.close();	        
	}
}
