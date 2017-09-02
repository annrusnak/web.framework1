package framework.pages.fotos;

import com.google.common.base.Function;
import framework.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.allure.annotations.Step;

public class FotosCartPage extends Page {


	@FindBy(how = How.XPATH, using = "//span[@class=\"total_summ\"]")
	private WebElement totalCostLabel;

	public FotosCartPage(WebDriver webDriver) {
		super(webDriver);
	}


	@Step("Getting total price")
	public int getTotalPrice() {
		return Integer.valueOf(totalCostLabel.getText()
				.replaceAll(" ", "")
				.replaceAll("=","")
				.replaceAll("грн",""));
	}

	@Override
	public Function<WebDriver, ?> isPageLoaded() {
		return ExpectedConditions.visibilityOf(totalCostLabel);
	}
}
