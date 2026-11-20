package first;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckBox {
    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://webdriveruniversity.com/");

        
        String mainWindow = driver.getWindowHandle();

        WebElement dropdownCard = driver.findElement(By.id("dropdown-checkboxes-radiobuttons"));

        driver.executeScript("arguments[0].scrollIntoView(true);", dropdownCard);
        Thread.sleep(1000); 
        dropdownCard.click();

        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        WebElement option1 = driver.findElement(By.xpath("//input[@value='option-1']"));
        option1.click();

        System.out.println("Option 1 checked: " + option1.isSelected());
        
        WebElement option2 = driver.findElement(By.xpath("//input[@value='option-2']"));
        option2.click();

        System.out.println("Option 2 checked: " + option1.isSelected());
         Thread.sleep(5000);
         driver.quit();;
        
    }
}