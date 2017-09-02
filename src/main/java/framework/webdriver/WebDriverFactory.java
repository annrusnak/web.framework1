package framework.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;

import framework.utility.LogFactory;
import framework.utility.PropertyLoader;
import framework.utility.WebDriverListener;

/**
 * A factory that returns a singleton of WebDriver object.
 */
public class WebDriverFactory {
	
	private static final Logger LOG = LogFactory.getLogger(WebDriverFactory.class);

	private static final String CHROME = "chrome";
	private static final String FIREFOX = "firefox";

	//private static WebDriver webDriver;
	private static EventFiringWebDriver eventDriver;

	private WebDriverFactory() {

	}

	/**
	 * Gets the single instance of WebDriverFactory.
	 * 
	 * @param browser
	 *            the browser set in properties
	 * @return single instance of WebDriverFactory
	 * @throws Exception
	 *             the exception of invalid browser property
	 */
	public static WebDriver getInstance(String browser) {
		WebDriver webDriver = null;
		DesiredCapabilities dc = new DesiredCapabilities();
		if (eventDriver == null) {
				if (CHROME.equals("chrome")) {

					setChromeDriver();
					
					ChromeOptions options = new ChromeOptions();
					options.addArguments("test-type");
					options.addArguments("disable-infobars");
					// set some options
					dc = DesiredCapabilities.chrome();
					dc.setCapability(ChromeOptions.CAPABILITY, options);

					webDriver = new ChromeDriver(dc);
					LOG.info("Chrome driver was started");

				} else if (FIREFOX.equals(browser)) {
					setFFDriver();
					
					FirefoxProfile fp = new FirefoxProfile();
					dc = DesiredCapabilities.firefox();
					dc.setCapability("marionette", false);
					dc.setCapability(FirefoxDriver.PROFILE, fp);
					
					webDriver = new FirefoxDriver(dc);
					LOG.info("Firefox driver was created");
				} else
					throw new IllegalArgumentException(
							"Invalid browser property set in configuration file");
				
			webDriver.manage().timeouts().implicitlyWait(Integer.
						valueOf(PropertyLoader.loadProperty("implicit.timeout")), TimeUnit.SECONDS);
			
			eventDriver = new EventFiringWebDriver(webDriver);
			eventDriver.register(new WebDriverListener());
			
			eventDriver.manage().timeouts().implicitlyWait(Integer.valueOf(PropertyLoader.loadProperty("implicit.timeout")), TimeUnit.SECONDS);
			eventDriver.manage().window().maximize();
		}
		
		return eventDriver;
	}

	public static WebDriver getSetDriver() {
		if (eventDriver == null) {
			throw new IllegalStateException("Driver is not set");
		}
		return eventDriver;
	}

	/**
	 * Kill driver instance.
	 * 
	 * @throws Exception
	 */
	public static void killDriverInstance() {
		eventDriver.quit();
		eventDriver = null;
		LOG.info("Web driver was ended");
	}

	/**
	 * Sets the chrome driver path for specific OS.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private static void setChromeDriver() {
		String osName = System.getProperty("os.name").toLowerCase();
		StringBuffer chromeBinaryPath = new StringBuffer(
				"src/main/resources/drivers/");

		if (osName.startsWith("win")) {
			chromeBinaryPath.append("chrome-win/chromedriver.exe");
		} else if (osName.startsWith("lin")) {
			chromeBinaryPath.append("chrome-lin/chromedriver");
		} else if (osName.startsWith("mac")) {
			chromeBinaryPath.append("chrome-mac/chromedriver");
		} else
			throw new IllegalStateException("Your OS is invalid for webdriver tests");

		System.setProperty("webdriver.chrome.driver",
				chromeBinaryPath.toString());
		LOG.info("Chrome driver path was set");
	}
	
	private static void setFFDriver() {
		String osName = System.getProperty("os.name").toLowerCase();
		StringBuffer chromeBinaryPath = new StringBuffer(
				"src/main/resources/drivers/");

		if (osName.startsWith("win")) {
			chromeBinaryPath.append("ff-win/geckodriver.exe");
		} else if (osName.startsWith("lin")) {
			chromeBinaryPath.append("ff-lin/geckodriver");
		} else if (osName.startsWith("mac")) {
			chromeBinaryPath.append("ff-mac/geckodriver");
		} else
			throw new IllegalStateException("Your OS is invalid for webdriver tests");

		System.setProperty("webdriver.gecko.driver",
				chromeBinaryPath.toString());
		LOG.info("FF driver path was set");
	}

}