package testcases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import base.BaseClass;
import browserFactory.BrowserFactory;
import helper.ResourceUtils;

public class RemoteDriver extends BaseClass{
	
	@Test
	public void RemoteDriverTest() throws MalformedURLException {
		
	DesiredCapabilities cap = new DesiredCapabilities();
	
	cap.setBrowserName("Chrome");
		// cap.setBrowserName("Chrome");
		
	ChromeOptions opt = new ChromeOptions();
	
	opt.addArguments("--no-sandbox");
	opt.setAcceptInsecureCerts(true);
	opt.addArguments("--ignore-certificate-errors", "--allow-insecure-localhost");	
	
	cap.merge(opt);
	
	driver.manage().window().maximize();
	
	driver.get("https://www.google.com");
	
	System.out.println(driver.getTitle());

    driver.quit();		

	}
}
