package tests.Events;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarNameValuePair;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ira on 8/10/2017.
 */
public class events_android_mobile {
    @Test
    public void BrowsProxy() throws Exception {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "015d257838280417");
        BrowserProxy browserProxy = new BrowserProxy();
        browserProxy.startServer(desiredCapabilities);
        AndroidDriver webDriver;


//        browserProxy.newHar("newHar");


        try {
            webDriver = new AndroidDriver(new URL("http://127.0.0.1:4722/wd/hub"), desiredCapabilities);

            webDriver.get("https://auto.ria.com/auto___19718494.html");
            HarEntry harEntry = browserProxy.getHarEntryByContainsKey("phoneAccessStat", false);
            HashMap<String, String> hashMap = new HashMap<String, String>();


            ArrayList<String> arrayListName = new ArrayList<String>();
            ArrayList<String> arrayListValue = new ArrayList<String>();
            for (HarNameValuePair nameValuePair : harEntry.getRequest().getQueryString()) {
                System.out.println(nameValuePair.getName() + ": " + nameValuePair.getValue());
//                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt", nameValuePair.getName() + ": " + nameValuePair.getValue());
                arrayListName.add(nameValuePair.getName());
                arrayListValue.add(nameValuePair.getValue());

            }

        }catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
