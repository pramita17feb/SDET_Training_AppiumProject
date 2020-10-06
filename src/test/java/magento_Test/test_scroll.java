package magento_Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class test_scroll {

	
	public static AndroidDriver driver;
	
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub

		
		
		DesiredCapabilities capability=new DesiredCapabilities();
		capability.setCapability("DeviceName", "pramita");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capability.setCapability(MobileCapabilityType.BROWSER_NAME,"chrome");
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.magento.com");
        
		driver.findElement(By.xpath("//*[@class='nav-menu-button js-menu-button visible-xs visible-sm']/span")).click();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(By.xpath("//a[@href='https://account.magento.com/customer/account/login']")).click();
		Thread.sleep(5000);

	}

}
