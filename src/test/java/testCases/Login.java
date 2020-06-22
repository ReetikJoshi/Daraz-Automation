package testCases;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.LoginPage;
import parent.Base;

public class Login extends Base {

	protected WebDriver driver;
	protected String phone, email, password;
	protected LoginPage lp;

	@BeforeTest
	public void setUp() throws IOException {
		driver = initializeDriver();

		// Go to login URL and maximize the current window
		driver.get(prop.getProperty("loginURL"));
		driver.manage().window().maximize();

		lp = new LoginPage(driver);

		// Close the pop-up box if it appears
		try {
			lp.popupClose().click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void extractData() throws IOException, ParseException {
		// Extract every data from the json file
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\User.json");

		// Read JSON file
		Object obj = jsonParser.parse(reader);

		// Extracts entire json file
		JSONArray usersList = (JSONArray) obj;

		// Extracts every block - one json object
		JSONObject users = (JSONObject) usersList.get(0);

		// Extracts each data in the block
		JSONObject user = (JSONObject) users.get("users");

		// Storing JSON data into variables for reusability
		phone = (String) user.get("phone");
		password = (String) user.get("password");
		email = (String) user.get("email");

	}

	@Test(dependsOnMethods = "extractData")
	public void login() throws InterruptedException {

		// Enter phone number
		lp.phone().sendKeys(phone);

		// Enter password
		lp.password().sendKeys(password);

		// Click login button
		lp.login().click();
		Thread.sleep(2000);

		// Verify if the title of the page is valid or not
		Assert.assertEquals(driver.getTitle(), "Manage My Account");
	}

	@AfterTest
	public void tearDown() {

		// Close the current browser window
		driver.close();

		// Instantiate the driver object to null
		driver = null;
	}
	// NOTE:- Also there is an Extent Reports folder to view the result in nice
	// format

}
