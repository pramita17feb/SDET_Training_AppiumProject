package magento_Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Test_MA006_Magento_unsuccesfulLogin {
	
	public AndroidDriver driver;
		
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
	public void unsuccessfulLogin() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//*[@class='nav-menu-button js-menu-button visible-xs visible-sm']/span")).click();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(By.xpath("//a[@href='https://account.magento.com/customer/account/login']")).click();
		Thread.sleep(5000);
		File iofile=new File("C:\\Users\\PramitaGhosh\\Documents\\Appium-Workspace\\appiumMaven_Assignment\\src\\test\\java\\TestData\\testDataMagento.xlsx"); //	\\appiumMaven_Assignment\\src\\test\\java\\
		FileInputStream fis=new FileInputStream(iofile);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheet("Sheet1");
		int rowcount=sheet.getLastRowNum();
		for(int i=1;i<=rowcount;i++) {
			String usernm=sheet.getRow(i).getCell(0).getStringCellValue();
			String passwd=sheet.getRow(i).getCell(1).getStringCellValue();
			System.out.println("User Nm::  "+usernm);
			System.out.println("Passwd::  "+passwd);
			driver.findElement(By.xpath("//*[@id='email']")).sendKeys(usernm);;
			driver.findElement(By.xpath("//*[@title='Password']")).sendKeys(passwd);
			driver.hideKeyboard();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			System.out.println("#######Clicked on Login#########");
			Thread.sleep(15000);
			String loginmsg=driver.findElement(By.xpath("//*[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")).getText();
			Thread.sleep(2000);
			System.out.println("login msg::   "+loginmsg);
			Assert.assertEquals(loginmsg, "Invalid login or password.");
			driver.navigate().back();
			Thread.sleep(2000);

		}
		fis.close();

	}

}
