package first;

	import java.time.Duration;
	import org.openqa.selenium.By;
	import org.openqa.selenium.chrome.ChromeDriver;
	public class conformationbox {
	    public static void main(String[] args) throws InterruptedException {

	        ChromeDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        //alerts comformation
	        driver.get("https://www.webdriveruniversity.com/Popup-Alerts/index.html");
	        driver.findElement(By.xpath("//span[@id='button4']//p[contains(text(),'CLICK ME!')]")).click();
	        Thread.sleep(4000);
	       // driver.switchTo().alert().accept();
	        driver.switchTo().alert().dismiss();
	        Thread.sleep(5000);
	        driver.close();
	    }
	}

