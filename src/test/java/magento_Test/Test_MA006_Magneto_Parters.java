package magento_Test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Test_MA006_Magneto_Parters {
	AndroidDriver driver;
	
	
	@BeforeClass
	public void setup() throws MalformedURLException {
		
		DesiredCapabilities capability=new DesiredCapabilities();
		capability.setCapability("DeviceName", "pramita");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capability.setCapability(MobileCapabilityType.BROWSER_NAME,"chrome");
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.magento.com");
	}
	
	@Test
	public void choosePartnerTest() throws InterruptedException {
		
		  WebElement ele=driver.findElement(By.xpath("//*[@data-adobe-name='Footer|GlobalNav|AboutClick']"));
		  JavascriptExecutor js=(JavascriptExecutor)driver;
		  js.executeScript("arguments[0].scrollIntoView()", ele);
		  Thread.sleep(5000);
		  js.executeAsyncScript("window.scrollBy(0,-1000)"); 
		  Thread.sleep(5000);
		 
		ele=driver.findElement(By.xpath("//*[@data-adobe-name='Footer|GlobalNav|PartnersClick']"));
		js.executeScript("arguments[0].scrollIntoView()", ele);
		ele.click();
		driver.findElement(By.xpath("//a[@data-adobe-name='Footer|GlobalNav|Partners|ChooseAPartnerClick']")).click();
		
		Thread.sleep(10000);
		String actual=driver.findElement(By.xpath("//*[@class='mage-column mage-centered-headline']/p")).getText();
		String expected="Adobe Partners play a key role in helping eCommerce organizations like yours succeed.\n" + 
				"Every day, our partners help customers of all sizes – across all industries – transform and grow their business.";
		Assert.assertEquals(actual, expected);
	}
	
	@AfterClass
	public void teardown() {
		driver.close();
	}

}
