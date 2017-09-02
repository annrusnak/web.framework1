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

import java.util.List;

public class FotosSearchResultsPage extends Page {
	
	@FindBy(how = How.XPATH, using = "//*[@class=\"search_result_block\"]//li//a[contains( text(),'Мобильные телефоны')]")
	private WebElement filterLabel;
	
	@FindBy(how = How.XPATH, using = "//*[@class=\"catalog_item\"]")
	private List<WebElement> searchResultItemsList;

	public FotosSearchResultsPage(WebDriver webDriver) {
		super(webDriver);
	}


	@Step("Open product by index: {0}")
	public FotosItemPage openProduct(int index) {
		filterLabel.click();
		if (!searchResultItemsList.isEmpty()) {
			if (searchResultItemsList.size() <= index) {
				throw new IllegalArgumentException("Invalid index was passed");
			}
			
			searchResultItemsList.get(index).click();
			
			return LitsPageFactory.initElements(webDriver, FotosItemPage.class);
		} else throw new IllegalStateException("Search result is empty");
	}

	@Override
	public Function<WebDriver, ?> isPageLoaded() {
		return ExpectedConditions.visibilityOf(filterLabel);
	}
}
