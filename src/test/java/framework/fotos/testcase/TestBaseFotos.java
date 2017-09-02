package framework.fotos.testcase;

import framework.pages.LitsPageFactory;
import framework.pages.fotos.FotosHomePage;
import framework.utility.LogFactory;
import framework.utility.PropertyLoader;
import framework.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBaseFotos {
	
	private static final Logger LOG = LogFactory.getLogger(TestBaseFotos.class);

	protected WebDriver webDriver;
	
	protected FotosHomePage fotosHomePage;

	@BeforeMethod
	public void setup() {
		String browserName = System.getProperty("browser");
		LOG.info("Navigating to test url");
		webDriver = WebDriverFactory.getInstance(browserName);
		webDriver.manage().timeouts().implicitlyWait(Integer.valueOf(PropertyLoader.loadProperty("implicit.timeout")), TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(Integer.valueOf(PropertyLoader.loadProperty("implicit.timeout")), TimeUnit.SECONDS);
		
		LOG.info("Navigating to test url");
		webDriver.get(PropertyLoader.loadProperty("testsite4.url"));

		fotosHomePage = LitsPageFactory.initElements(webDriver, FotosHomePage.class);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (webDriver != null) {
			LOG.info("Killing web driver");
			WebDriverFactory.killDriverInstance();
		}
	}

}