package first;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class git {
    public static void main(String args[]) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        driver.get("https://github.com/login");
        
        driver.findElement(By.id("login_field")).sendKeys("alekhyanalam8331@gmail.com");
        
        driver.findElement(By.id("password")).sendKeys("Alekhya@2005");
        
        driver.findElement(By.name("commit")).click();
        
        Thread.sleep(4000);
        driver.quit();
    }
}