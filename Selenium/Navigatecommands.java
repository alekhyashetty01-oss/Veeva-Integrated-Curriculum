package first;

import org.openqa.selenium.chrome.ChromeDriver;

public class Navigatecommands {
public static void main(String [] args)throws InterruptedException
 {
	ChromeDriver driver = new ChromeDriver();
	
	//navigate to()
	driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
	
	driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	
	//navigate back()
	driver.navigate().back();
	System.out.println(driver.getCurrentUrl());
	
	//naigate forward()
	driver.navigate().forward();
	System.out.println(driver.getCurrentUrl());
	
	//navigate refresh()
	driver.navigate().refresh();
	
	Thread.sleep(5000);
	
	driver.close();
}
}
