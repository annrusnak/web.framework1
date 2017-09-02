package framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LitsPageFactory {
	
	@SuppressWarnings("unchecked")
	public static <T extends Page> T initElements(WebDriver webDriver, Class<T> clazz) {
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		Page page = PageFactory.initElements(webDriver, clazz);
		wait.until(page.isPageLoaded());
		
		return (T) page;
	} 

}
