package main;


import main.pages.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import ria.com.base.TestBase;
import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Step;


import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by tester on 5/31/17.
 */
public class TestSuiteBase extends TestBase {

    public MainPage mainPage;
    public HideNumberMainPage hideNumberMainPage;

    @Parameter
    public String Time_load_page;

    public String Link;


    @BeforeMethod
    public void getPage() {
        hideNumberMainPage = PageFactory.initElements(driver, HideNumberMainPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
    }

    @Step("Admin3: id {0}")
    public void loginWithURl(String user_id) {
        driver.get("http://admin3.ria.com/common_projects.php?target=login&project_id=1&clientID=" + user_id + "&curr_window=1");
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
    }

    @Step("Open {0}")
    public void openPage(String url) {
        driver.manage().deleteAllCookies();

        double start = System.currentTimeMillis();

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        double finish = System.currentTimeMillis();
        double totalTime = finish - start;
        double seconds = totalTime / 1000;
        String message = " " + seconds + "sec.";
        Time_load_page = message;
        Link = driver.getCurrentUrl();
        System.out.println(message+"\n");
        System.out.println(driver.getCurrentUrl()+"\n");
        step("Time load page ["  + url + "] = " + seconds + " sec.");

    }

    @Step("Open: {0}, Cookies: {1} {2}")
    public void openPageWithCookies(String url, String cookieName, String cookieValue){
        driver.manage().deleteAllCookies();
        double start = System.currentTimeMillis();

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        double finish = System.currentTimeMillis();
        double totalTime = finish - start;
        double seconds = totalTime / 1000;
        String message = " " + seconds + "sec.";
        Time_load_page = message;
        Link = driver.getCurrentUrl();
        System.out.println(message+"\n");
        System.out.println(driver.getCurrentUrl()+"\n");
        step("Time load page ["  + url + "] = " + seconds + " sec.");

        Date curr = new Date();
        Cookie cookie = new Cookie(cookieName, cookieValue);
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }


//    @Step("Login admin3 {0}")
//    public void loginAdmin3(String user_id){
////        $("http://admin3.ria.com/common_projects.php?target=login&project_id=1&clientID=" + user_id + "&curr_window=1");
//        driver.get("http://admin3.ria.com/common_projects.php?target=login&project_id=1&clientID=" + user_id + "&curr_window=1");
//        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
//        mainPage.waiter(mainPage.buttonAddAds);
//
//    }

//    @Step("Логинимся в Admin3")
    public void admin3() {
        driver.get("http://admin3.ria.com/?target=login");
        driver.findElement(By.id("email")).sendKeys("test@ria.ua");
        driver.findElement(By.name("password")).sendKeys("nazar1989");
        driver.findElement(By.id("submit")).click();
    }

    public void switchWindow(int numberOfWindow) {
        String handle = driver.getWindowHandles().toArray()[numberOfWindow].toString();
        driver.switchTo().window(handle);
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } catch (Exception e) {
            JSONObject json = new JSONObject();
            return json;
        } finally {
            is.close();
        }
    }

    public static String readJsonFalseFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return jsonText;
        } catch (Exception e) {
            return "null";
        } finally {
            is.close();
        }
    }

    public static JSONArray readJsonArrayFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));


            String jsonText = readAll(rd);

            JSONArray arr = new JSONArray(jsonText);

            //System.out.println(arr.toString(2));

            return arr;
        } finally {
            is.close();
        }
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

    public void readFile(String adress) throws Exception {

        BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir") + adress));
        String line;
        List<String> lines = new ArrayList<String>();
        while ((line = in.readLine()) != null) {
            lines.add(line);
        }

        //если нужен массив то список можно запросто преобрпзовать
        String[] linesAsArray = lines.toArray(new String[lines.size()]);

        System.out.print(linesAsArray.length + "\n");
        for (int i = 0; i < linesAsArray.length; i++) {
            System.out.print(linesAsArray[i] + "\n");
        }

    }

    @Step("{0}")
    public void step(String parameter) {
    }

}
