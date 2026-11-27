package first;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class question3{

    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/windows");
        String parentWindow = driver.getWindowHandle();
        System.out.println("Parent Window ID: " + parentWindow);
        driver.findElement(By.linkText("Click Here")).click();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        System.out.println("Child Tab Title: " + driver.getTitle());
        Thread.sleep(1500);
        driver.close();
        driver.switchTo().window(parentWindow);
        System.out.println("Back to Parent Tab Title: " + driver.getTitle());
        Thread.sleep(1500);
        driver.quit();
    }
}
