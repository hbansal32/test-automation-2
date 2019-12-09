package pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public static WebDriver driver;
	public static Properties prop;
	
	public static String projectPath = System.getProperty("user.dir");
	public static Logger log = LogManager.getLogger(BasePage.class.getName());
	public By pageLoader = By.xpath("//div[@class='overlay lr-loading-screen-overlay' and @style='display: block;']");

	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(projectPath + "//src//main//java//resources//config.properties/");
		prop.load(fis);
		String browserName = prop.getProperty("browserName");
		switch (browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "//drivers//chromedriver.exe");
			driver = new ChromeDriver();
			log.info("Chrome Driver initialized successfully");
			break;

		case "IE":
			System.setProperty("webdriver.ie.driver", projectPath + "//drivers//IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			log.info("IE Driver initialized successfully");
			break;

		default:
			log.info("Driver not found");
			break;
		}
		return driver;
	}

	public void launchApplication() {
		driver.get(prop.getProperty("applicationUrl"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Application launched successfully");
	}

	public String getScreenshotPath(String result) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String path=projectPath + "//target//screenshots//" + result + "_screenshot_" + timeStamp + ".png";
		FileHandler.copy(src,new File(path));
		return path;
	}

	public WebElement waitTillElementClickable(WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		WebElement waitElement = wait.until(ExpectedConditions.elementToBeClickable(element));
		return waitElement;
	}
	
	public boolean waitTillElementInvisible(WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public WebElement getPageLoader() {
		return driver.findElement(pageLoader);
	}
}
