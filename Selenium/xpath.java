package first;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class xpath {
    public static void main(String[] args) throws InterruptedException
    {

        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 1. Open home page
        driver.get("https://www.automationexercise.com");

        // 2. Click Products
        driver.findElement(By.xpath("//a[@href='/products']")).click();

        // 3. Search box lo "tops" 
        driver.findElement(By.id("search_product")).sendKeys("tops");
        // 4. Submit search button click chey
        driver.findElement(By.id("submit_search")).click();
        // 5. veiw product
        driver.findElement(By.xpath("//a[contains(text(),'View Product')]")).click();
        // 6.add to cart
        driver.findElement(By.xpath("//button[@type='button']")).click();
        //7.countinue shoping
        driver.findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block']")).click();
        //8.cart
        driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
       //9. checkout
        driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]")).click();
        //10.counite on cart
        driver.findElement(By.xpath("//button[contains(text(),'Continue On Cart')]")).click();
        //11.close the window
          Thread.sleep(5000);
        driver.close();
    }
}