package framework.utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import framework.webdriver.WebDriverFactory;

public class TestExecutionListener extends TestListenerAdapter {
	
	private static final Logger LOG = LogFactory.getLogger(TestExecutionListener.class);

	/**
	 * Prints the test results to report.
	 * 
	 * @param result
	 *            the result
	 */
	
	private void printTestResults(ITestResult result) {
		if (result.getParameters().length != 0) {
			String params = null;
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}
			LOG.info("Output params: " + params);
		}

		String status = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			status = "Pass";
			break;
		case ITestResult.FAILURE:
			status = "Failed";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
			break;
		}

		LOG.info("Test status is: " + status);
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		printTestResults(arg0);
	}
	
	@Override
	public void onTestSuccess(ITestResult arg0) {
		printTestResults(arg0);
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		printTestResults(arg0);
		LOG.info("Taking screenshot for failed test");
		testScreenshotOnFinish(WebDriverFactory.getSetDriver());
	}

	@Step("Screenshot after test")
    public void testScreenshotOnFinish(WebDriver driver) {
    	getScreenshot(driver);
    }
    
    @Attachment(value = "Screenshot after failed test", type = "image/png")
    public byte[] getScreenshot(WebDriver driver) {
    	byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    	
    	return screenshot;
    }

}
