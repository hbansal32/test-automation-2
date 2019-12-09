package test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import pageObjects.AccountPage;
import pageObjects.BasePage;
import pageObjects.LoginPage;

public class BaseTest extends BasePage {
	AccountPage accountPage;
	LoginPage loginPage;

	@BeforeTest
	public void initialize() throws Exception {
		driver = initializeDriver();
		accountPage = new AccountPage(driver);
		loginPage = new LoginPage(driver);
		launchApplication();
		accountPage.clickLoginButton();
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.navigate().to(projectPath+"/target/surefire-reports/ExtentReportsTestNG.html");
		//driver.quit();
		driver = null;
		log.info("Driver closed successfully");
	}
}
