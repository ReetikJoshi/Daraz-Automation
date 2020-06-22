package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	protected WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@id='acc-alert-close']")
	WebElement popupClose;

	@FindBy(xpath = "//input[@placeholder='Please enter your phone number']")
	WebElement phone;

	@FindBy(xpath = "//input[@placeholder='SMS Verification Code']")
	WebElement code;

	@FindBy(xpath = "//input[@placeholder='Minimum 6 characters with a number and a letter']")
	WebElement password;

	@FindBy(className = "next-menu-content")
	WebElement birthdayList;

	@FindBy(id = "month")
	WebElement month;

	@FindBy(xpath = "//li[contains(text(),'December')]")
	WebElement selectMonth;

	@FindBy(id = "day")
	WebElement day;

	@FindBy(xpath = "//li[contains(text(),'20')]")
	WebElement selectDay;

	@FindBy(id = "year")
	WebElement year;

	@FindBy(xpath = "//li[contains(text(),'1990')]")
	WebElement selectYear;

	@FindBy(id = "gender")
	WebElement gender;

	@FindBy(css = "[value='male']")
	WebElement male;

	@FindBy(xpath = "//input[@placeholder='Enter your first and last name']")
	WebElement fullName;

	@FindBy(xpath = "//input[@placeholder='Please enter your email']")
	WebElement email;

	@FindBy(css = "[type='submit']")
	WebElement submit;

	public WebElement popupClose() {
		return popupClose;
	}

	public WebElement phone() {
		return phone;
	}

	public WebElement code() {
		return code;
	}

	public WebElement password() {
		return password;
	}

	public WebElement birthdayList() {
		return birthdayList;
	}

	public WebElement month() {
		return month;
	}

	public WebElement selectMonth() {
		return selectMonth;
	}

	public WebElement day() {
		return day;
	}

	public WebElement selectDay() {
		return selectDay;
	}

	public WebElement year() {
		return year;
	}

	public WebElement selectYear() {
		return selectYear;
	}

	public WebElement gender() {
		return gender;
	}

	public WebElement male() {
		return male;
	}

	public WebElement fullName() {
		return fullName;
	}

	public WebElement email() {
		return email;
	}

	public WebElement submit() {
		return submit;
	}

}
