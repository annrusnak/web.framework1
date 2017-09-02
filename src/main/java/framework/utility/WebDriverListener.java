package framework.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;

/**
 * The listener interface for receiving webDriver events.
 * The class that is interested in processing a webDriver
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addWebDriverListener<code> method. When
 * the webDriver event occurs, that object's appropriate
 * method is invoked.
 *
 * @see WebDriverEvent
 */
public class WebDriverListener implements WebDriverEventListener {
	
	private static final Logger LOG = LogFactory.getLogger(WebDriverListener.class);
	
	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver,
			CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver,
			CharSequence[] keysToSend) {
		LOG.info("Element " + element + " value was changed");
	}
	
	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {

	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		LOG.info("Element " + getElementXpath(element) + " was clicked");
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {

	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		LOG.info("Was navigated to url: " + url);
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {

	}

	@Override
	public void afterNavigateBack(WebDriver driver) {

	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {

	}

	@Override
	public void afterNavigateForward(WebDriver driver) {

	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {

	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {

	}

	@Override
	public void beforeScript(String script, WebDriver driver) {

	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		LOG.info(script + " script was executed");
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		LOG.info("WebDriver Exception thrown:", throwable.getMessage());
	}
	
	private String getElementXpath(WebElement element) {
		String elementStringRepresentation = element.toString();
		if (elementStringRepresentation.contains("xpath:")) {
			String substringElementStringRepresentation = 
					elementStringRepresentation.substring(
							elementStringRepresentation.indexOf("xpath: "), 
							elementStringRepresentation.length());
			if (substringElementStringRepresentation.contains("-> xpath:")) {
				return substringElementStringRepresentation.replaceAll(" -> xpath: ", "/");
			} else return substringElementStringRepresentation;
		}
		return "";
	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
}
