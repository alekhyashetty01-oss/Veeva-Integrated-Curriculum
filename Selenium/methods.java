package first;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class methods {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//wait method
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//get()
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		//get title
		String title = driver.getTitle();
        System.out.println("Page Title: " + title);
        //get current url
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        //get pagesource
        String pageSource = driver.getPageSource();
        System.out.println("Page Source contains 'OrangeHRM': " + pageSource.contains("OrangeHRM"));
        // getWindowHandle()
        String windowId = driver.getWindowHandle();
        System.out.println("Window Handle ID: " + windowId);
        // getWindowHandels()
        Set<String> allWindowIds = driver.getWindowHandles();
        System.out.println("Total Open Windows: " + allWindowIds.size());
        //wait method
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")) );
        usernameField.sendKeys("Admin");
        
        driver.findElement(By.name("password")).sendKeys("admin123");
        
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginButton.click();
       
     // isDisplayed() 
        boolean isLogoDisplayed = driver.findElement(By.xpath("//div[@class='orangehrm-login-branding']/img")).isDisplayed();
        System.out.println("Logo is displayed: " + isLogoDisplayed);

        // isEnabled() 
        boolean isUsernameEnabled = usernameField.isEnabled();
        System.out.println("Username input is enabled: " + isUsernameEnabled);

        boolean isLoginBtnEnabled = loginButton.isEnabled();
        System.out.println("Login button is enabled: " + isLoginBtnEnabled);

        // isSelected() 
        boolean isSelectedStatus = usernameField.isSelected();
        System.out.println("Username field is selected (checked): " + isSelectedStatus);
         
        Thread.sleep(5000);
       driver.quit();
        
        
	}

}
