package tests.Events;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarNameValuePair;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class events_android {
    String str[] = {
            "19718494",
            "19922210",
            "20152163",
            "20050787",
            "20230436",
            "20143570",
            "19620690",
            "14962409",
            "20241841",
            "20236750"
    };

    @Test
    public void test1() throws InterruptedException {


        BrowserProxy server = new BrowserProxy();
        server.start(5555);
        Proxy proxy = ClientUtil.createSeleniumProxy(server);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "015d257838280417");
        desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);
//        desiredCapabilities.setCapability("platformVersion", "5.1.1");
//        desiredCapabilities.setCapability("deviceName", "JK");
//        desiredCapabilities.setCapability("app", "C:/testapp/com.xxxxx.android.apk");
//        desiredCapabilities.setCapability("appPackage", "com.xxxxx.android");
//        desiredCapabilities.setCapability("appActivity", "activities.home.HomeActivity");
//        desiredCapabilities.setCapability("noReset",false);
        server.newHar("newHar");
        AndroidDriver webDriver;
        try {
            webDriver = new AndroidDriver(new URL("http://127.0.0.1:4722/wd/hub"), desiredCapabilities);

            webDriver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
            Har har = server.getHar();
            HarLog log = har.getLog();
            List<HarEntry> entries = new CopyOnWriteArrayList<HarEntry>(log.getEntries());
            System.out.println(entries);
            for (HarEntry entry : entries){
                System.out.println(entry.getRequest().getUrl());
            }


            webDriver.get("http://admin3.ria.com/common_projects.php?target=login&project_id=1&clientID=4931149&curr_window=1");
            for (int i = 0; i < str.length; i++) {
                String auto_id = str[i];
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt", auto_id);
                webDriver.get("https://auto.ria.com/auto___" + str[i] + ".html");
                System.out.println(auto_id);

                webDriver.findElement(By.cssSelector(".size14.phone_show_link.link-dotted.mhide")).click();
                Thread.sleep(1500);
                HarEntry harEntry = server.getHarEntryByContainsKey("phoneAccessStat", false);

                 HashMap<String, String> hashMap = new HashMap<String, String>();


                ArrayList<String> arrayListName = new ArrayList<String>();
                ArrayList<String> arrayListValue = new ArrayList<String>();
                for (HarNameValuePair nameValuePair : harEntry.getRequest().getQueryString()) {
                    System.out.println(nameValuePair.getName() + ": " + nameValuePair.getValue());
                    writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt", nameValuePair.getName() + ": " + nameValuePair.getValue());
                    arrayListName.add(nameValuePair.getName());
                    arrayListValue.add(nameValuePair.getValue());

                }

            }














        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        server.stop();
    }

    public void writeFile(String dir, String text) throws Exception {
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir") + dir, true)) {
            // запись всей строки
            writer.write(text + "\n");

            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
    }

