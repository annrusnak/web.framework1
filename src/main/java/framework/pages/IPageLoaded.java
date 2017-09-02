package framework.pages;

import org.openqa.selenium.WebDriver;

import com.google.common.base.Function;

public interface IPageLoaded {
	
	Function<WebDriver, ?> isPageLoaded();

}
