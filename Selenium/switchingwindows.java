package first;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class switchingwindows {

    public static void main(String[] args) throws InterruptedException {
        
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
       
        driver.findElement(By.xpath("//a[normalize-space()='OrangeHRM, Inc']")).click();
     
        Set<String> windid = driver.getWindowHandles();

        List<String> windowlist = new ArrayList<>(windid);

       // String parentId = windowlist.get(0);
        String childId = windowlist.get(1);
        // windows id
       // System.out.println("Parent Window ID: " + parentId);
       // System.out.println("Child Window ID: " + childId);
        // switch child window
      //  driver.switchTo().window(childId);
      //  System.out.println("Child window title: " + driver.getTitle());
        //switch parent window
      //  driver.switchTo().window(parentId);
       // System.out.println("Parent window title: " + driver.getTitle());
        //close all tabs
        //Approach 2 :
          for( String window : windid)
          {
        	  driver.switchTo().window(window);
        	  String title = driver.getTitle();
        	  if(title.contains("OrangeHRM"))
        	  {
        		  System.out.println(driver.getCurrentUrl());
        	  }
          }
        
        
        
        driver.quit();
    }
}