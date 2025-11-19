package testcases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class RemoteDriver {
	
	@Test
	public void RemoteDriverTest() throws MalformedURLException {
		
	DesiredCapabilities cap = new DesiredCapabilities();
	
	cap.setBrowserName("Chrome");
		
	ChromeOptions opt = new ChromeOptions();
	
	opt.addArguments("--no-sandbox");
	opt.setAcceptInsecureCerts(true);
	opt.addArguments("--ignore-certificate-errors", "--allow-insecure-localhost");	
	
	cap.merge(opt);
	
	WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.102:4444/wd/hub"), cap);
	
	driver.manage().window().maximize();
	
	driver.get("https://www.goole.com");
	
	System.out.println(driver.getTitle());

    driver.quit();		

	}
}
