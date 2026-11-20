package first;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class handlealerts {

	public static void main (String args[]) throws InterruptedException
	{
		 ChromeDriver driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		 driver.get("https://webdriveruniversity.com/");
		
		 String parentWindow = driver.getWindowHandle();

		 WebElement popupCard = driver.findElement(By.id("popup-alerts"));
		
		 driver.executeScript("arguments[0].scrollIntoView(true);", popupCard);
		 driver.executeScript("arguments[0].click();", popupCard);
		 
	
		 Set<String> allWindows = driver.getWindowHandles();
		 for (String window : allWindows) {
		     if (!window.equals(parentWindow)) {
		         driver.switchTo().window(window);
		         break;
		     }
		 }

		 System.out.println("Current Page Title: " + driver.getTitle());
		 
		 driver.findElement(By.id("button1")).click();
		 Thread.sleep(2000);
		 

		driver.switchTo().alert().accept();
		driver.quit();
}
}