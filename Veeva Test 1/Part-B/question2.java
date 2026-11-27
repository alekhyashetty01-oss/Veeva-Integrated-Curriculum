package first;

import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class question2 {
    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert alert1 = driver.switchTo().alert();
        System.out.println("Alert 1: " + alert1.getText());
        Thread.sleep(1500);
        alert1.accept(); 
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert2 = driver.switchTo().alert();
        System.out.println("Alert 2: " + alert2.getText());
        Thread.sleep(1500);
        alert2.accept(); 
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert alert3 = driver.switchTo().alert();
        System.out.println("Alert 3: " + alert3.getText());
        alert3.sendKeys("Automation Test"); 
        Thread.sleep(1500);
        alert3.accept(); 
        Thread.sleep(2000);
        driver.quit();
    }
}