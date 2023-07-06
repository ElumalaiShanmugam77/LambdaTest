package lambdatest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.net.*;
import java.time.Duration;
import java.util.HashMap;

public class TestScenarios {

	private WebDriver driver;

	String username = "elumalais7594";
	String accessKey = "ScBSibmTDPsMRg2YQCKJGKUVIKnXyGFswFtUFqdVFsNBH8K8XE";
	
	HashMap<String, Object> ltOptions = new HashMap<String, Object>();

	@Parameters({ "browserName", "Url" })
	@BeforeMethod
	public void setUp(String browserName, String url) {

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.setPlatformName("Windows 10");
			browserOptions.setBrowserVersion("88.0");
			ltOptions.put("visual", true);
			ltOptions.put("video", true);
			ltOptions.put("network", true);
			ltOptions.put("w3c", true);
			browserOptions.setCapability("LT:Options", ltOptions);

			try {
				driver = new RemoteWebDriver(
						new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"),
						browserOptions);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (browserName.equalsIgnoreCase("Edge")) {
			EdgeOptions browserOptions = new EdgeOptions();
			browserOptions.setPlatformName("macOS Sierra");
			browserOptions.setBrowserVersion("87.0");
			ltOptions.put("visual", true);
			ltOptions.put("video", true);
			ltOptions.put("network", true);
			ltOptions.put("w3c", true);
			browserOptions.setCapability("LT:Options", ltOptions);
			try {
				driver = new RemoteWebDriver(
						new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"),
						browserOptions);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (browserName.equalsIgnoreCase("Firefox")) {
			FirefoxOptions browserOptions = new FirefoxOptions();
			browserOptions.setPlatformName("Windows 7");
			browserOptions.setBrowserVersion("82.0");
			ltOptions.put("visual", true);
			ltOptions.put("video", true);
			ltOptions.put("network", true);
			ltOptions.put("w3c", true);
			browserOptions.setCapability("LT:Options", ltOptions);
			try {
				driver = new RemoteWebDriver(
						new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"),
						browserOptions);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (browserName.equalsIgnoreCase("internet explorer")) {
			InternetExplorerOptions browserOptions = new InternetExplorerOptions();
			browserOptions.setPlatformName("Windows 10");
			browserOptions.setBrowserVersion("11.0");
			ltOptions.put("visual", true);
			ltOptions.put("video", true);
			ltOptions.put("network", true);
			ltOptions.put("w3c", true);
			browserOptions.setCapability("LT:Options", ltOptions);
			try {
				driver = new RemoteWebDriver(
						new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"),
						browserOptions);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
	}

	@Test
	public void testScenario1() {
		// Test Scenario 1
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("body")));

		SoftAssert softAssert = new SoftAssert();
		String pageTitle = driver.getTitle();
		softAssert.assertEquals(pageTitle, "LambdaTest");
		softAssert.assertAll();
	}

	@Test
	public void testScenario2() {
		// Test Scenario 2
		WebElement checkboxDemoLink = driver.findElement(By.linkText("Checkbox Demo"));
		checkboxDemoLink.click();

		WebElement checkbox = driver.findElement(By.id("isAgeSelected"));
		checkbox.click();

		boolean isSelected = checkbox.isSelected();
		Assert.assertTrue(isSelected);

		checkbox.click();

		isSelected = checkbox.isSelected();
		Assert.assertFalse(isSelected);
	}

	@Test
	public void testScenario3() {
		// Test Scenario 3
		driver.navigate().to("https://www.lambdatest.com/selenium-playground/");

		WebElement javascriptAlertsLink = driver.findElement(By.linkText("Javascript Alerts"));
		javascriptAlertsLink.click();

		WebElement clickMeButton = driver.findElement(
				By.xpath("//button[@class='btn btn-dark my-30 mx-10 hover:bg-lambda-900 hover:border-lambda-900']"));
		clickMeButton.click();

		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		Assert.assertEquals(alertText, "Alert box!");

		alert.accept();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
