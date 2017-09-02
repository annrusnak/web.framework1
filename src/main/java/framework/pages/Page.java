package framework.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import framework.utility.LogFactory;

/*
 * Abstract class representation of a Page in the UI with additional features. Page object pattern
 * @author Taras Lytvyn
 */
public abstract class Page implements IPageLoaded {
	
	private static final Logger LOG = LogFactory.getLogger(Page.class);
	
	protected WebDriver webDriver;
	
	/*
	 * Constructor injecting the WebDriver interface
	 * 
	 * @param webDriver
	 */
	
	public Page(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public String getTitle() {
		return webDriver.getTitle();
	}
	
	public String getPageUrl() {
		return webDriver.getCurrentUrl();
	}
	
	public boolean isElementPresent(WebElement element) {
		LOG.info("Checking if element is present " + element);
        try {
        	element.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
	
	public WebDriverWait getWebDriverWait(int seconds) {
		WebDriverWait wait = new WebDriverWait(webDriver, seconds);
		return wait;
	}
	
	public void jsClickOnElement(WebElement webElement) {
		LOG.info("Clicking on web element " + webElement);
		getJSExecutor().executeScript("arguments[0].click();", webElement);
	} 
	
	public void scrollToElement(WebElement webElement) {
		LOG.info("Scrollling to web element " + webElement);
		getJSExecutor().executeScript("arguments[0].scrollIntoView(true);", webElement);
	} 
	
	public boolean isElementPresent(int seconds, WebElement webElement) {
		try {
			getWebDriverWait(seconds).until(driver -> webElement.isEnabled());
			return true;
		} catch (TimeoutException e) {
			LOG.error("Element is not available");
			return false;
		}
	}
	
	private JavascriptExecutor getJSExecutor() {
		return (JavascriptExecutor) webDriver;
	}
	
}