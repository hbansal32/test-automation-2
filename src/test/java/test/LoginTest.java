package test;

import java.util.Map;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.ExcelUtil;

public class LoginTest extends BaseTest {

	@Test(groups="Positive", description= "Verify the messages for invalid email address format.", dataProvider = "getData", dataProviderClass = ExcelUtil.class)
	public void LoginTest4_Test(Map<String, String> map) {
		loginPage.loginUsingCredentials(map.get("incompleteEmail"), map.get("validPassword"));
		Assert.assertEquals(loginPage.getEmailErrorMessage().getText(), "The Email Id field must contain a valid email address.", "Invalid email field error appears on page.");
	}
	
	@Test(groups="Positive", description= "Verify the messages for invalid password format.", dataProvider = "getData", dataProviderClass = ExcelUtil.class)
	public void LoginTest5_Test(Map<String, String> map) {
		loginPage.loginUsingCredentials(map.get("validEmail"), map.get("shortPassword"));
		Assert.assertEquals(loginPage.getPasswordErrorMessage().getText(), "The Password field must be at least 6 characters in length.", "Invalid password field error appears on page.");
	}
	
	@Test(groups="Positive", description= "Verify the messages for invalid email and password format.", dataProvider = "getData", dataProviderClass = ExcelUtil.class)
	public void LoginTest6_Test(Map<String, String> map) {
		loginPage.loginUsingCredentials(map.get("incompleteEmail"), map.get("shortPassword"));
		Assert.assertEquals(loginPage.getEmailErrorMessage().getText(), "The Email Id field must contain a valid email address.", "Invalid email field error appears on page.");
		Assert.assertEquals(loginPage.getPasswordErrorMessage().getText(), "The Password field must be at least 6 characters in length.", "Invalid password field error appears on page.");
	}
	
	@Test(groups="Negative", description= "Verify the unsuccessful login for invalid email and password.", dataProvider = "getData", dataProviderClass = ExcelUtil.class)
	public void LoginTest7_Test(Map<String, String> map) {
		loginPage.loginUsingCredentials(map.get("invalidEmail"), map.get("invalidPassword"));
		Assert.assertTrue(true, "Unsuccessful login for invalid email and password.");
	}
	
	@Test(groups="Negative", description= "Verify the unsuccessful login for valid email and password, but those are not related to each other", dataProvider = "getData", dataProviderClass = ExcelUtil.class)
	public void LoginTest8_Test(Map<String, String> map) {
		loginPage.loginUsingCredentials(map.get("validEmail2"), map.get("validPassword"));
		Assert.assertTrue(true, "Unsuccessful login when valid email and password not related to each other.");
	}
	
	@Test(groups="Positive", description= "Verify if the data in password field is encrypted.", dataProvider = "getData", dataProviderClass = ExcelUtil.class)
	public void LoginTest9_Test(Map<String, String> map) {
		loginPage.loginUsingCredentials(map.get("validEmail"),map.get("validPassword"));
		Assert.assertTrue(loginPage.getPasswordBox().getAttribute("type").equals("password"), "Password field is set as password attribute at browser level.");
		loginPage.logout();
	}
	
	@Test(groups="Positive", description= "Verify if password can be copied from password field", dataProvider = "getData", dataProviderClass = ExcelUtil.class)
	public void LoginTest10_Test(Map<String, String> map) {
		loginPage.loginUsingCredentials(map.get("invalidEmail"),map.get("validPassword"));
		Assert.assertTrue(loginPage.getPasswordBox().getText().equals(""), "Password field is blank after copied from browser.");
	}
	
	@Test(groups="Positive", description= "Verify if the Enter key of the keyboard is working correctly on the login page.", dataProvider = "getData", dataProviderClass = ExcelUtil.class)
	public void LoginTest11_Test(Map<String, String> map) {
		loginPage.loginUsingCredentials(map.get("validEmail"),map.get("validPassword")+Keys.ENTER);
		loginPage.logout();
	}
	
	@Test(groups="Positive", description= "Verify if a user will be able to login with a valid username and valid password.", dataProvider = "getData", dataProviderClass = ExcelUtil.class)
	public void LoginTest12_Test(Map<String, String> map) {
		loginPage.loginUsingCredentials(map.get("validEmail"), map.get("validPassword"));
		loginPage.logout();
	}
	
	@Test(groups="Negative", description= "Verify the login page for empty email field and valid password and submit button is clicked.", dataProvider = "getData", dataProviderClass = ExcelUtil.class)
	public void LoginTest1_Test(Map<String, String> map) throws InterruptedException {
		Thread.sleep(3000);
		loginPage.loginUsingCredentials("", map.get("validPassword"));
		Assert.assertEquals(loginPage.getEmailErrorMessage().getText(), "The Email Id field is required.", "Invalid email field error appears on page.");
	}
	
	@Test(groups="Negative", description= "Verify the login page for valid email field and empty password field and Submit button is clicked.", dataProvider = "getData", dataProviderClass = ExcelUtil.class)
	public void LoginTest2_Test(Map<String, String> map) throws InterruptedException {
		Thread.sleep(3000);
		loginPage.loginUsingCredentials(map.get("validEmail"), "");
		Assert.assertEquals(loginPage.getPasswordErrorMessage().getText(), "The Password field is required.", "Invalid password field error appears on page.");
	}
	
	@Test(groups="Negative", description= "Verify the login page for empty email and password fields and Submit button is clicked.", dataProvider = "getData", dataProviderClass = ExcelUtil.class)
	public void LoginTest3_Test(Map<String, String> map) throws InterruptedException {
		Thread.sleep(3000);
		loginPage.loginUsingCredentials("", "");
		Assert.assertEquals(loginPage.getEmailErrorMessage().getText(), "The Email Id field is required.", "Invalid email field error appears on page.");
		Assert.assertEquals(loginPage.getPasswordErrorMessage().getText(), "The Password field is required.", "Invalid password field error appears on page.");
	}
}
