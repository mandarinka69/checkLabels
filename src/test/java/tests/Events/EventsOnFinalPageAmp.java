package tests.Events;

import main.TestSuiteBase;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarNameValuePair;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EventsOnFinalPageAmp extends TestSuiteBase {
    @Test
    public void BrowsProxy() throws Exception {

        BrowserProxy browserProxy = new BrowserProxy();
        DesiredCapabilities caps = DesiredCapabilities.chrome(); //здесь мы в зависимости от браузера берем capabilities    browserProxy.startServer(caps);
        browserProxy.startServer(caps);
        WebDriver webDriver = new RemoteWebDriver(caps);
//        webDriver.get("https://auto.ria.com/auto_honda_cr_v_20232868.amp.html");
        webDriver.get("https://www.google.de/amp/s/auto.ria.com/auto_honda_cr_v_20232868.amp.html");
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
            writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","state_id отсутствует");
        }
        if(arrayListName.contains("price") == false){
            System.out.println("Параметр price отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","price отсутствует");
        }
        if(arrayListName.contains("owner_id") == false){
            System.out.println("Параметр owner_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","state_id отсутствует");
        }
        if(arrayListName.contains("model_id") == false){
            System.out.println("Параметр model_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","model_id отсутствует");
        }
        if(arrayListName.contains("marka_id") == false){
            System.out.println("Параметр marka_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","marka_id отсутствует");
        }
        if(arrayListName.contains("city_id") == false){
            System.out.println("Параметр city_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","city_id отсутствует");
        }
        if(arrayListName.contains("currency_id") == false){
            System.out.println("Параметр currency_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","currency_id отсутствует");
        }
        if(arrayListName.contains("year") == false){
            System.out.println("Параметр year отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","year отсутствует");
        }
        if(arrayListName.contains("category_id") == false){
            System.out.println("Параметр category_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","category_id отсутствует");
        }
        if(arrayListName.contains("body_id") == false){
            System.out.println("Параметр body_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","body_id отсутствует");
        }
        if(arrayListName.contains("year") == false){
            System.out.println("Параметр year отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","year отсутствует");
        }
        if(arrayListName.contains("proposal_id") == false){
            System.out.println("Параметр proposal_id отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","proposal_id отсутствует");
        }
        if(arrayListName.contains("is_amp") == false){
            System.out.println("Параметр is_amp отсутствует");
            writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","is_amp отсутствует");
        }

        writeFile("/src/test/java/main/resources/eventsOnFinalPageAmp.txt","\n");


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
