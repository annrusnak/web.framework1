package framework.pages.fotos;

import com.google.common.base.Function;
import framework.pages.LitsPageFactory;
import framework.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.allure.annotations.Step;

public class FotosHomePage extends Page {
	
	@FindBy(how = How.ID, using = "head_search_input")
	private WebElement searchTextField;
	
	@FindBy(how = How.XPATH, using = "//*[@class=\"submit\"]")
	private WebElement searchButton;

	public FotosHomePage(WebDriver webDriver) {
		super(webDriver);
	}
	
	@Step("Search for product: {0}")
	public FotosSearchResultsPage searchProduct(String productName) {
		searchTextField.sendKeys(productName);
		searchButton.click();
		
		return LitsPageFactory.initElements(webDriver, FotosSearchResultsPage.class);
	}

	@Override
	public Function<WebDriver, ?> isPageLoaded() {
		return ExpectedConditions.visibilityOf(searchTextField);
	}
}
