package tests.Events;

import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarNameValuePair;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EventsOnShowHideNumberFinalPageMobileSize {
    @Test
    public void BrowsProxy() throws Exception {
        BrowserProxy browserProxy = new BrowserProxy();
        DesiredCapabilities caps = DesiredCapabilities.chrome(); //здесь мы в зависимости от браузера берем capabilities    browserProxy.startServer(caps);
        browserProxy.startServer(caps);
        WebDriver webDriver = new RemoteWebDriver(caps);
        webDriver.get("https://auto.ria.com/auto_honda_cr_v_20232868.html");
        Dimension d = new Dimension(320,568);
        webDriver.manage().window().setSize(d);
        webDriver.get("https://auto.ria.com/auto_honda_cr_v_20232868.html");

        Thread.sleep(1500);

        HarEntry harEntry = browserProxy.getHarEntryByContainsKey("auto.ria.com/buShowStat/", false);
//        String callsCount = browserProxy.getCallsCountByContainsKey("", false);
//        Assert.assertTrue(callsCount == 2,"Unexpected calls count");

        HashMap<String, String> hashMap = new HashMap<String, String>();


        ArrayList<String> arrayListName = new ArrayList<String>();
        ArrayList<String> arrayListValue = new ArrayList<String>();
        for(HarNameValuePair nameValuePair : harEntry.getRequest().getQueryString()){
            System.out.println(nameValuePair.getName()+ ": " + nameValuePair.getValue());
//            writeFile("/src/test/java/main/resources/ex.txt", nameValuePair.getName()+ ": " + nameValuePair.getValue());
            arrayListName.add(nameValuePair.getName());
            arrayListValue.add(nameValuePair.getValue());

        }
        if(arrayListName.contains("state_id") == false){
            System.out.println("Параметр state_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnShowHideNumberFinalPageMobileSize.txt","state_id отсутствует");
        }
        if(arrayListName.contains("price") == false){
            System.out.println("Параметр price отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnShowHideNumberFinalPageMobileSize.txt","price отсутствует");
        }
        if(arrayListName.contains("owner_id") == false){
            System.out.println("Параметр owner_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnShowHideNumberFinalPageMobileSize.txt","state_id отсутствует");
        }
        if(arrayListName.contains("model_id") == false){
            System.out.println("Параметр model_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnShowHideNumberFinalPageMobileSize.txt","model_id отсутствует");
        }
        if(arrayListName.contains("marka_id") == false){
            System.out.println("Параметр marka_id отсутствует");
            writeFile("/src/test/java/main/resources/eeventsOnShowHideNumberFinalPageMobileSize.txt","marka_id отсутствует");
        }
        if(arrayListName.contains("city_id") == false){
            System.out.println("Параметр city_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnShowHideNumberFinalPageMobileSize.txt","city_id отсутствует");
        }
        if(arrayListName.contains("currency_id") == false){
            System.out.println("Параметр currency_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnShowHideNumberFinalPageMobileSize.txt","currency_id отсутствует");
        }
        if(arrayListName.contains("year") == false){
            System.out.println("Параметр year отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnShowHideNumberFinalPageMobileSize.txt","year отсутствует");
        }
        if(arrayListName.contains("category_id") == false){
            System.out.println("Параметр category_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnShowHideNumberFinalPageMobileSize.txt","category_id отсутствует");
        }
        if(arrayListName.contains("body_id") == false){
            System.out.println("Параметр body_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnShowHideNumberFinalPageMobileSize.txt","body_id отсутствует");
        }
        if(arrayListName.contains("year") == false){
            System.out.println("Параметр year отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnShowHideNumberFinalPageMobileSize.txt","year отсутствует");
        }
        if(arrayListName.contains("proposal_id") == false){
            System.out.println("Параметр proposal_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnShowHideNumberFinalPageMobileSize.txt","proposal_id отсутствует");
        }

        writeFile("/src/test/java/main/resources/eventsOnShowHideNumberFinalPageMobileSize.txt","\n");

//            Assert.assertEquals(arrayListName.get(2).contains("state_id"),true, "state_id");
//            Assert.assertEquals(arrayListName.get(3).contains("price"),true, "price");
//            Assert.assertEquals(arrayListName.get(4).contains("owner_id"),true, "owner_id");
//            Assert.assertEquals(arrayListName.get(5).contains("model_id"),true, "model_id");
//            Assert.assertEquals(arrayListName.get(6).contains("marka_id"),true, "marka_id");
//            Assert.assertEquals(arrayListName.get(7).contains("city_id"),true, "city_id");
//            Assert.assertEquals(arrayListName.get(8).contains("currency_id"),true, "currency_id");
//            Assert.assertEquals(arrayListName.get(9).contains("year"),true, "year");
//            Assert.assertEquals(arrayListName.get(10).contains("device"),true, "device");
//            Assert.assertEquals(arrayListName.get(11).contains("category_id"),true, "category_id");
//            Assert.assertEquals(arrayListName.get(12).contains("body_id"),true, "body_id");
//            Assert.assertEquals(arrayListName.get(13).contains("pageVersion"),true, "pageVersion");

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
