package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	protected WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@id='acc-alert-close']")
	WebElement popupClose;

	@FindBy(xpath = "//input[@placeholder='Please enter your Phone Number or Email']")
	WebElement phone;

	@FindBy(xpath = "//input[@placeholder='Please enter your password']")
	WebElement password;

	@FindBy(css = "[type='submit']")
	WebElement login;

	public WebElement popupClose() {
		return popupClose;
	}

	public WebElement phone() {
		return phone;
	}

	public WebElement password() {
		return password;
	}

	public WebElement login() {
		return login;
	}
}
