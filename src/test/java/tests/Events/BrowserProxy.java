package tests.Events;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarNameValuePair;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class BrowserProxy {

    private BrowserMobProxy proxyServer;
    private boolean isServerStarted;


    public void startServer(DesiredCapabilities capabilities) {
        proxyServer = new BrowserMobProxyServer(0);
        try {
            proxyServer.start();
            isServerStarted = true;
        } catch (Exception e) {
            throw new RuntimeException("Can't start proxy-server on port: " + proxyServer.getPort(), e);
        }

        Proxy proxy = null;
        try {
            proxy = createHttpProxy(proxyServer.getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        createNewHar();
        capabilities.setCapability(CapabilityType.PROXY, proxy);
    }

    private Proxy createHttpProxy(int port) throws UnknownHostException {
        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        String proxyStr = String.format("%s:%d", InetAddress.getLocalHost().getCanonicalHostName(), port);
        proxy.setHttpProxy(proxyStr);
        proxy.setSslProxy(proxyStr);
        return proxy;
    }

    public void createNewHar() {
        proxyServer.newHar();
    }

    public void stopServer() {
        if (isServerStarted) {
            try {
                proxyServer.stop();
            } catch (Exception e) {
                throw new RuntimeException("Can't stop proxy server", e);
            }

        }
    }


    public int getCallsCountByContainsKey(String key, boolean clearHistory) {
        int result = 0;
        Har har = proxyServer.getHar();
        if (har != null) {
            HarLog harLog = har.getLog();
            List<HarEntry> entries = harLog.getEntries();
            for (HarEntry entry : entries) {
                if (entry.getRequest().getUrl().contains(key)) {
                    result++;
                }

            }

        } else {
            return -1;
        }
        if (clearHistory) {
            createNewHar();
        }
        return result;
    }


    public HarEntry getHarEntryByContainsKey(String key, boolean clearHistory) {
        int result = 0;
        Har har = proxyServer.getHar();
        if (har != null) {
            HarLog harLog = har.getLog();
            List<HarEntry> entries = harLog.getEntries();
            for (HarEntry entry : entries) {
                if (entry.getRequest().getUrl().contains(key)) {
                    return entry;
                }

            }

        }
        if (clearHistory) {
            createNewHar();
        }
        return null;
    }

    public Har getHar() {
        return proxyServer.getHar();
    }

    public static class EventsOnShowHideNumberFinalPageHar {
        @Test
        public void BrowsProxy() throws Exception {
            BrowserProxy browserProxy = new BrowserProxy();
            DesiredCapabilities caps = DesiredCapabilities.chrome(); //здесь мы в зависимости от браузера берем capabilities    browserProxy.startServer(caps);
            browserProxy.startServer(caps);
            WebDriver webDriver = new RemoteWebDriver(caps);
            webDriver.get("https://auto.ria.com/auto_honda_cr_v_20232868.html");
            webDriver.findElement(By.cssSelector(".size14.phone_show_link.link-dotted.mhide")).click();
            Thread.sleep(1500);
            HarEntry harEntry = browserProxy.getHarEntryByContainsKey("phoneAccessStat", false);

            ArrayList<String> arrayListName = new ArrayList<String>();
            ArrayList<String> arrayListValue = new ArrayList<String>();
            for(HarNameValuePair nameValuePair : harEntry.getRequest().getQueryString()){
                System.out.println(nameValuePair.getName()+ ": " + nameValuePair.getValue());
    //            writeFile("/src/test/java/main/resources/ex.txt", nameValuePair.getName()+ ": " + nameValuePair.getValue());
                arrayListName.add(nameValuePair.getName());
                arrayListValue.add(nameValuePair.getValue());

            }

            if(arrayListName.contains("proposal_id") == false){
                System.out.println("Параметр proposal_id отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt","proposal_id отсутствует");
            }

            if(arrayListName.contains("owner_id") == false){
                System.out.println("Параметр owner_id отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt","owner_id отсутствует");
            }

            if(arrayListName.contains("phone") == false){
                System.out.println("Параметр phone отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt","phone отсутствует");
            }

            if(arrayListName.contains("marka_id") == false){
                System.out.println("Параметр marka_id отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt","marka_id отсутствует");
            }

            if(arrayListName.contains("model_id") == false){
                System.out.println("Параметр model_id отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt","model_id отсутствует");
            }

            if(arrayListName.contains("ip") == false){
                System.out.println("Параметр ip отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt","ip отсутствует");
            }

            if(arrayListName.contains("screentype") == false){
                System.out.println("Параметр screentype отсутствует");
                writeFile("/src/test/java/main/resources/eventOnFinalPAgeShowHideNumber.txt","screentype отсутствует");
            }


            writeFile("/src/test/java/main/resources/example.txt","\n");

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
}
