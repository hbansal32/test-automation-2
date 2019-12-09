package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "emailid")
	private WebElement emailBox;

	@FindBy(id = "validation-loginradius-raas-login-emailid")
	private WebElement emailErrorMessage;

	@FindBy(name = "password")
	private WebElement passwordBox;

	@FindBy(id = "validation-loginradius-raas-login-password")
	private WebElement passwordErrorMessage;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	public WebElement getEmailBox() {
		return this.emailBox;
	}

	public WebElement getEmailErrorMessage() {
		return this.emailErrorMessage;
	}

	public WebElement getPasswordBox() {
		return this.passwordBox;
	}

	public WebElement getPasswordErrorMessage() {
		return this.passwordErrorMessage;
	}

	public WebElement getLoginButton() {
		waitTillElementClickable(loginButton, 10);
		return this.loginButton;
	}

	public void loginUsingCredentials(String email, String password) {
		waitLoginPageToLoad();
		emailBox.sendKeys(email);
		passwordBox.sendKeys(password);
		try {
			getLoginButton().click();
			waitTillElementInvisible(getPageLoader(), 20);
		} catch (Exception e) {
		}
	}

	private void waitLoginPageToLoad() {
		/*try {
			waitTillElementInvisible(getPageLoader(), 10);
		} catch (Exception e) {
		}*/
		waitTillElementClickable(emailBox, 10).clear();
		waitTillElementClickable(passwordBox, 10).clear();
		waitTillElementClickable(loginButton, 10);
	}

	public void logout() {
		// TODO Auto-generated method stub

	}
}