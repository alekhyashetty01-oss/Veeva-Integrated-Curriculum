package first;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

class second {
    public static void main(String args[]) throws InterruptedException
    {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.linkText("CONTACT")).click();
        driver.findElement(By.partialLinkText("Con")).click();
  
    Thread.sleep(4000);
    driver.close();
    
    }
}