package ria.com.base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import ru.yandex.qatools.allure.annotations.Attachment;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 08.09.14
 */
public class OnFailure extends TestListenerAdapter{
    public RemoteWebDriver driver;
    @Override
    public void onTestFailure(final ITestResult tr) {

        RemoteWebDriver driver = (RemoteWebDriver) tr.getTestContext().getAttribute("driverKey");
        makeScreenshot(driver);
//	    getSystemProperties();
//	        createAttachment2();
    }

//	    @Attachment("Hi, I'm attachment in your testng listener")
//	    public String createAttachment2() {
//	        return "My own attachment body!";
//	    }


    @Attachment
    public byte[] makeScreenshot(RemoteWebDriver driver) {

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

//	    @Attachment(value = "System Environment", type = "text/plain")
//	    public byte[] getSystemProperties() {
//	        Properties props = System.getProperties();
//	        StringBuilder result = new StringBuilder();
//	        for (String prop : props.stringPropertyNames()) {
//	            result.append(prop)
//	                    .append(" = ")
//	                    .append(System.getProperty(prop))
//	                    .append("\n");
//	        }
//	        return result.toString().getBytes();
//	    }
}
