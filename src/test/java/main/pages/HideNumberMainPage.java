package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

/**
 * Created by tester on 5/31/17.
 */
public class HideNumberMainPage extends PageBase {
    public HideNumberMainPage(WebDriver driver) {
        super(driver);
    }


    @FindBys(@FindBy(css = "span.item.phone > span.js-showPhoneBlock.bold"))
    public List<WebElement> phoneNumberInAds;

    @FindBys(@FindBy(css = ".ticket-item.new__ticket.paid"))
    private List<WebElement> idAds;

    @FindBys(@FindBy(css = ".size14.phone_show_link"))
    private List<WebElement> linkShow;

    public List<WebElement> getIdAds() {
        return idAds;
    }

    public List<WebElement> getLinkShow() {
        return linkShow;
    }

    public List<WebElement> getPhoneNumberInAds() {
        return phoneNumberInAds;
    }

    public void pressButtonShow(int value) {
        linkShow.get(value).click();
    }


    /**
     * without main page
     **/

    @FindBys(@FindBy(css = "span.item.phone"))
    private List<WebElement> phoneNumberInAdsMainSearch;
    @FindBys(@FindBy(css = "#searchResults > div.ticket-item"))
    private List<WebElement> idAdsMainSearch;
    @FindBy(css = ".size18.show-more.fl-r")
    private WebElement openNextPage;

    public List<WebElement> getPhoneNumberInAdsMainSearch() {
        return phoneNumberInAdsMainSearch;
    }

    public List<WebElement> getIdAdsMainSearch() {
        return idAdsMainSearch;
    }

    public WebElement getOpenNextPage() {
        return openNextPage;
    }

    @Step("Нажимаем на кнопку \"Следующая\"")
    public void pressNextPage() {
        waiter(By.id("topFilterTextSearchInputWrapper"), 30);
        getOpenNextPage().click();
    }

    /**
     * дилер
     **/
    @FindBys(@FindBy(css = ".phones.size18"))
    private List<WebElement> phoneNumberDealers;

    public List<WebElement> getPhoneNumberDealers() {
        return phoneNumberDealers;
    }

    @FindBys(@FindBy(css = "div.ticket-photo.loaded > span > a:nth-child(2)"))
    private List<WebElement> linkOnMegaPhoto;

    @FindBy(css = "#megaphotoUserInfo > span.bold.green > span a")
    private WebElement showElementMegaPhoto;

    public List<WebElement> getLinkOnMegaPhoto() {
        return linkOnMegaPhoto;
    }

    public WebElement getShowElementMegaPhoto() {
        return showElementMegaPhoto;
    }

    /**мегафото на каталогах без главной**/

    @FindBys(@FindBy(css = "div.content-bar div.ticket-photo span a:nth-child(2)"))
    private List<WebElement> linkOnMegaPhotoWithoutMain;

    public List<WebElement> getLinkOnMegaPhotoWithoutMain() {
        return linkOnMegaPhotoWithoutMain;
    }

    @FindBy(css = "#megaphotoHeader > a")
    private WebElement linkCloseMegaPhoto;

    public WebElement getLinkCloseMegaPhoto() {
        return linkCloseMegaPhoto;
    }


    /****/
    @FindBy(css = ".link.green")
    private WebElement hideNumberInSelerBlock;

    @FindBy(css = ".proven-seller div[class=\"item-phone\"] div[class=\"phone\"] span[class=\"mhide\"]")
    private WebElement showPhoneNumberOnFinalPage;


//    @FindBy(css = ".phone-wrap a")
//    private WebElement showPhoneNumberOnWall;

    @FindBy(css = ".phone-wrap")
    private WebElement showPhoneNumberOnWall;

    public WebElement getShowPhoneNumberOnFinalPage() {
        return showPhoneNumberOnFinalPage;
    }

    public WebElement getHideNumberInSelerBlock() {
        return hideNumberInSelerBlock;
    }

    public WebElement getShowPhoneNumberOnWall() {
        return showPhoneNumberOnWall;
    }
}
