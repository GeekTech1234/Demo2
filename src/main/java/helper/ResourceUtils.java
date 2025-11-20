package helper;

import dataProvider.ConfigReader;

public class ResourceUtils {
	
	public static String getApplicationUrl()
	{
		return ConfigReader.getProperty("url");
	}
	
	public static String getRemoteExecutionFlag()
	{
		return ConfigReader.getProperty("remote.execution.flag");
	}
	
	public static String getBrowser()
	{
		return ConfigReader.getProperty("browser");
	}
	
	public static String getRemoteURL()
	{
		String url="http://"+ConfigReader.getProperty("remote.server.hub.ip")+":"+ConfigReader.getProperty("remote.server.hub.port")+"/wd/hub";
		return url;
	}

}
