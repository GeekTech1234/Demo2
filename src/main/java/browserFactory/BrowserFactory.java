package browserFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import dataProvider.ConfigReader;
import helper.ResourceUtils;

public class BrowserFactory {
	
	static WebDriver driver;
	
	public static WebDriver getBrowserInstance()
	{
		return driver;
	}
	
	public static WebDriver startBrowser(String browserName, String applicationURL)
	{		
		if(browserName.equals("Chrome") || browserName.equals("GC") || browserName.equals("Google Chrome"))
		{
			if(Boolean.parseBoolean(ConfigReader.getProperty("remote.execution.flag")))
			{
				try
				{
					DesiredCapabilities cap = new DesiredCapabilities();
					
					cap.setBrowserName("Chrome");
						
					ChromeOptions opt = new ChromeOptions();
					
					opt.addArguments("--no-sandbox");
					opt.setAcceptInsecureCerts(true);
					opt.addArguments("--ignore-certificate-errors", "--allow-insecure-localhost");	
					
					cap.merge(opt);
					
					String remoteURL = ResourceUtils.getRemoteURL();
					driver=new RemoteWebDriver(new URL(remoteURL), cap);
				} 
				catch (MalformedURLException e)
				{
					System.out.println("URL is malformed "+e.getMessage());
				}
			}
			else
			{
				driver=new ChromeDriver();
			}
		}
		else if(browserName.equals("Firefox") || browserName.equals("FF") || browserName.equals("Mozila FireFox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browserName.equals("Safari"))
		{
			driver=new SafariDriver();
		}
		else if(browserName.equals("Edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		
//		Incase of Opera or any other browser, which is not mentioned above and if default driver code is not added in IfElse condition,
//		then driver will remain null, so it will throw NullPointerException.
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
		
		driver.get(applicationURL);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
	}

}
