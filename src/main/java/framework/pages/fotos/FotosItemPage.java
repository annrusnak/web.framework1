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

public class FotosItemPage extends Page {
	
	@FindBy(how = How.XPATH, using = "//*[@class=\"buy_button\"]")
	private WebElement buyButton;

	public FotosItemPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Step("Go to cart")
	public FotosCartPage goToCart() {
		buyButton.click();

		return LitsPageFactory.initElements(webDriver, FotosCartPage.class);
	}

	@Override
	public Function<WebDriver, ?> isPageLoaded() {
		return ExpectedConditions.visibilityOf(buyButton);

	}

}
