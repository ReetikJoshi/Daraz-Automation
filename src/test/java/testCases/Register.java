package testCases;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.RegisterPage;
import parent.Base;

public class Register extends Base {

	protected WebDriver driver;
	protected String phone, password, fullName, gender, email, month, day, year;
	protected RegisterPage rp;
	protected Actions a;

	@BeforeTest
	public void setUp() throws IOException {

		driver = initializeDriver();
		rp = new RegisterPage(driver);
		a = new Actions(driver);

		// Go to the register URL and maximize the current window
		driver.get(prop.getProperty("registerURL"));
		driver.manage().window().maximize();

		// Close the pop-up box if it appears
		try {
			a.moveToElement(rp.popupClose()).click().build().perform();
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

		// Extract date of birth nested inside the data
		JSONObject dob = (JSONObject) user.get("DOB");

		// Storing JSON data into variables for reusability
		phone = (String) user.get("phone");
		password = (String) user.get("password");
		fullName = (String) user.get("fullName");
		gender = (String) user.get("gender");
		email = (String) user.get("email");
		month = (String) dob.get("month");
		day = (String) dob.get("day");
		year = (String) dob.get("year");

	}

	@Test(dependsOnMethods = "extractData")
	public void register() throws InterruptedException {

		// Enter phone number
		rp.phone().sendKeys(phone);

		// Enter verification code
		rp.code().sendKeys("000000");

		// Enter valid password
		rp.password().sendKeys(password);

		// Select birth month
		rp.month().click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", rp.birthdayList());
		Thread.sleep(2000);
		a.moveToElement(rp.selectMonth()).click().build().perform();

		// Select birth day
		rp.day().click();
		js.executeScript("arguments[0].scrollIntoView(true);", rp.birthdayList());
		Thread.sleep(2000);
		a.moveToElement(rp.selectDay()).click().build().perform();

		// Select birth year
		rp.year().click();
		js.executeScript("arguments[0].scrollIntoView(true);", rp.birthdayList());
		Thread.sleep(2000);
		a.moveToElement(rp.selectYear()).click().build().perform();

		// Select gender
		rp.gender().click();
		Thread.sleep(1000);
		a.moveToElement(rp.male()).click().build().perform();
		Thread.sleep(2000);

		// Enter full name
		rp.fullName().sendKeys(fullName);

		// Enter email
		rp.email().sendKeys(email);

		// Click on submit button
		rp.submit().click();
		Thread.sleep(3000);

	}

	@AfterTest
	public void tearDown() {

		// Close the current browser window
		driver.close();

		// Instantiate the driver object to null
		driver = null;
	}
	// NOTE:- Also there is an Extent Reports folder to view the result in nice format

}
