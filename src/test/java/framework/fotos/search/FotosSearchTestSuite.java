package framework.fotos.search;

import framework.fotos.testcase.TestBaseFotos;
import framework.pages.fotos.FotosCartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FotosSearchTestSuite extends TestBaseFotos {

	@Test
	public void iphoneSearchTest() {
		FotosCartPage fotosCartPage = fotosHomePage
				.searchProduct("Iphone")
				.openProduct(0)
				.goToCart();
		
		Assert.assertTrue(fotosCartPage.getTotalPrice() > 20000);
	}

}
