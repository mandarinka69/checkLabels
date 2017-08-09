package tests.Events;

import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarNameValuePair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EventsOnShowHideNumberFinalPageHar {
    @Test
    public void BrowsProxy() throws Exception {
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


//        String auto_id = "20134931";
//        writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt",auto_id);

        BrowserProxy browserProxy = new BrowserProxy();
        DesiredCapabilities caps = DesiredCapabilities.chrome(); //здесь мы в зависимости от браузера берем capabilities    browserProxy.startServer(caps);
        browserProxy.startServer(caps);
        WebDriver webDriver = new RemoteWebDriver(caps);
        webDriver.get("http://admin3.ria.com/common_projects.php?target=login&project_id=1&clientID=4931149&curr_window=1");
        for (int i = 0; i < str.length; i++) {
            String auto_id = str[i];
            writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt", auto_id);
            webDriver.get("https://auto.ria.com/auto___" + str[i] + ".html");
            System.out.println(auto_id);

            webDriver.findElement(By.cssSelector(".size14.phone_show_link.link-dotted.mhide")).click();
            Thread.sleep(1500);
            HarEntry harEntry = browserProxy.getHarEntryByContainsKey("phoneAccessStat", false);
//        String callsCount = browserProxy.getCallsCountByContainsKey("", false);
//        Assert.assertTrue(callsCount == 2,"Unexpected calls count");

            HashMap<String, String> hashMap = new HashMap<String, String>();


            ArrayList<String> arrayListName = new ArrayList<String>();
            ArrayList<String> arrayListValue = new ArrayList<String>();
            for (HarNameValuePair nameValuePair : harEntry.getRequest().getQueryString()) {
                System.out.println(nameValuePair.getName() + ": " + nameValuePair.getValue());
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt", nameValuePair.getName() + ": " + nameValuePair.getValue());
                arrayListName.add(nameValuePair.getName());
                arrayListValue.add(nameValuePair.getValue());

            }
            if (arrayListName.contains("proposal_id") == false) {
                System.out.println("Параметр proposal_id отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt", "[proposal_id] отсутствует");
            }
            if (arrayListName.contains("owner_id") == false) {
                System.out.println("Параметр owner_id отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt", "[owner_id] отсутствует");
            }
            if (arrayListName.contains("phone") == false) {
                System.out.println("Параметр phone отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt", "[phone] отсутствует");
            }
            if (arrayListName.contains("marka_id") == false) {
                System.out.println("Параметр marka_id отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt", "[marka_id] отсутствует");
            }
            if (arrayListName.contains("model_id") == false) {
                System.out.println("Параметр model_id отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt", "[model_id] отсутствует");
            }
            if (arrayListName.contains("ip") == false) {
                System.out.println("Параметр ip отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt", "[ip] отсутствует");
            }
            if (arrayListName.contains("screentype") == false) {
                System.out.println("Параметр screentype отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt", "[screentype] отсутствует");
            }

            writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt", "\n");

        }
        webDriver.quit();
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
