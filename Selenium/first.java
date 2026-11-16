package first;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


class first
{
	public static void main(String args[]) throws InterruptedException
	{
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/automation-practice-form");
		driver.findElement(By.id("firstName")).sendKeys("Alekhya");
		driver.findElement(By.id("lastName")).sendKeys("Nalam");
		driver.findElement(By.id("userEmail")).sendKeys("ammu@gmail.com");
		driver.findElement(By.cssSelector("input[id='gender-radio-1']")).click();
		driver.findElement(By.id("userNumber")).sendKeys("8247360164");
		driver.findElement(By.id("currentAddress")).sendKeys("b.c.colony street!");
		
		driver.findElement(By.cssSelector("input[id='hobbies-checkbox-1']")).click();
		
		WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
        subjectsInput.sendKeys("Maths");
        subjectsInput.sendKeys(Keys.ENTER);
        
        WebElement stateInput = driver.findElement(By.id("react-select-3-input"));
        stateInput.sendKeys("NCR");
        stateInput.sendKeys(Keys.ENTER);
        
        WebElement cityInput = driver.findElement(By.id("react-select-4-input"));
        cityInput.sendKeys("Delhi");
        cityInput.sendKeys(Keys.ENTER);
       
     
		driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']")).click();
		
		
        
	   Thread.sleep(4000);
		driver.close();
		
	}
}