package parent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	protected WebDriver driver;
	protected Properties prop;

	public WebDriver initializeDriver() throws IOException {

		// Traversing the properties file
		prop = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "//resources//Global.properties");
		prop.load(fs);

		// Invoke Chrome Browser
		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//resources//chromedriver.exe");
			driver = new ChromeDriver();

		}

		// Invoke Edge Browser
		else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {

			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "//resources//msedgedriver.exe");
			driver = new EdgeDriver();

		} else {
			System.out.println("Enter valid browser name in properties file");
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		return driver;

	}

}
