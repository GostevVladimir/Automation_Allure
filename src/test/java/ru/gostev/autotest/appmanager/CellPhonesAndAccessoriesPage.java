package ru.gostev.autotest.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class CellPhonesAndAccessoriesPage extends BasePage {

  public CellPhonesAndAccessoriesPage(WebDriver wd) {
    super(wd);
    PageFactory.initElements(wd, this);
  }

  @FindBy(xpath = "//*[@class='b-list__header']")
  private WebElement headerShopByCategory;

  @FindBy(xpath = ".//*[text()='More refinements...']/parent::button")
  private WebElement moreRefinements;

  @FindBy(xpath = ".//*[@data-value='Купить сейчас']")
  private WebElement buyItNowRadiobutton;

  //===================RESUALT=================================

  @FindBy(xpath = "(.//*[@class='s-item__title-hotness']/following-sibling::a/h3)[3]")
  private WebElement nameOfTheThirdLot;

  @FindBy(xpath = "(.//*[@class='s-item__detail s-item__detail--primary'][1])[3]")
  private WebElement priceOfTheThirdLot;

  //===================NETWORK=================================

  @FindBy(xpath = ".//input[contains(@id,'Network_AT')]")
  private WebElement atAndTChekbox;

  @FindBy(xpath = ".//input[contains(@id,'Network_Sprint_cbx')]")
  private WebElement sprintChekbox;

  @FindBy(xpath = ".//input[contains(@id,'Network_Verizon_cbx')]")
  private WebElement verizonChekbox;

//======================BRAND==================================

  @FindBy(id = "refineOverlay-mainPanel-Brand")
  private WebElement brandButton;

  @FindBy(id = "refineOverlay-subPanel-Brand_Motorola_cbx")
  private WebElement motorolaChekbox;

  @FindBy(id = "refineOverlay-subPanel-Brand_Samsung_cbx")
  private WebElement samsungChekbox;

  @FindBy(id = "refineOverlay-subPanel-Brand_Apple_cbx")
  private WebElement appleChekbox;

//======================PRICE==================================

  @FindBy(id = "refineOverlay-mainPanel-price")
  private WebElement priceButton;

  @FindBy(id = "refineOverlay-subPanel-_x-price-0-0[0]")
  private WebElement priceFrom;

  @FindBy(id = "refineOverlay-subPanel-_x-price-0-1[0]")
  private WebElement priceTo;

  @FindBy(xpath = ".//button[text()='Применить']")
  private WebElement applyButton;

  //  ============================================================
  @FindBy(xpath = ".//*[@rel='next']")
  private WebElement showingItemsNextAfterCurrent;

  @Step("Выбрать {option}")
  public void selectOptionFromShopByCategory(String option) {
    selectOptionFromDropDownByText(headerShopByCategory, option);
  }

  @Step ("Кликнуть по Оптимизация поиска...")
  public void clickMoreRefinements(){
    waitForElementPresentAndClick(moreRefinements, wd);
  }

  @Step("Установить чекбокс At&t")
  public void atAndTClick(){
    atAndTChekbox.click();
  }

  @Step("Установить чекбокс Sprint")
  public void sprintClick(){
    sprintChekbox.click();
  }

  @Step("Установить чекбокс Verizon")
  public void verizonClick(){
    verizonChekbox.click();
  }

  @Step("Перейти на вкладку Brand")
  public void brandButtonClick(){
    brandButton.click();
  }

  @Step("Установить чекбокс Motorola")
  public void motorolaClick(){
    motorolaChekbox.click();
  }

  @Step("Установить чекбокс Samsung")
  public void samsungClick(){
    samsungChekbox.click();
  }

  @Step("Установить чекбокс Apple")
  public void appleClick(){
    appleChekbox.click();
  }

  @Step("Перейти на вкладку Price")
  public void priceButtonClick(){
    priceButton.click();
  }

  @Step("Установить цену от {text}")
  public void setPriceFrom(String text){
    type(priceFrom,text);
  }

  @Step("Установить цену до {text}")
  public void setPriceTo(String text){
    type(priceTo,text);
  }

  @Step("Нажать кнопку Потверждения")
  public void applyButtonClick(){
    applyButton.click();
  }

  @Step("Установить радиокнопку Купить сейчас")
  public void buyItNowRadiobuttonClick(){
    dependableClick(wd, buyItNowRadiobutton);
  }

  @Step("Вернуть название третьего лота")
  public String getNameThirdLot(){
    return nameOfTheThirdLot.getText();
  }

  @Step("Вернуть цену третьего лота")
  public String getPriceThirdLot(){
    return priceOfTheThirdLot.getText();
  }

  @Step("Выполнить деактивацию фильтров")
  public void deactivateBrandFilter(){
    brandButtonClick();
    samsungClick();
    motorolaClick();
    appleClick();
    applyButtonClick();
  }

  @Step("Установить новый ценовой интервал")
  public void setNewPrice(String price){
    priceButtonClick();
    int prc = Integer.parseInt(price.substring(0, price.indexOf(',')));
    int from = prc - 50;
    int to = prc + 50;
    setPriceFrom(Integer.toString(from));
    setPriceTo(Integer.toString(to));
    applyButtonClick();
  }

  @Step("Вполнить поиск лота по имени {expectedName} и цене {expectedPrice}")
  public void findLotByNameAmdPrice(String expectedName, String expectedPrice){
    int pageNumber = 1;
    boolean exist = true;
    while (exist && pageNumber < 2) {
      if(!checkListWebElementsHaveExpectedText(expectedPrice, expectedName)){
        break;
      }else{
        exist = clickNextPageResult();
        pageNumber++;
      }
    }
  }

  @Step("Проверка результирующего списка поиска")
  public  boolean checkListWebElementsHaveExpectedText(String expectedPrice, String expectedName) {

    new WebDriverWait(wd, 10)
            .ignoring(StaleElementReferenceException.class)
            .until((WebDriver d) -> {
              List<WebElement> listOfNameLot = d.findElements(By.xpath(".//*[@class='s-item__title-hotness']/following-sibling::a/h3"));
              List<WebElement>listOfPriceLot = wd.findElements(By.xpath(".//*[@class='s-item__detail s-item__detail--primary'][1]"));
              for (int i = 0; i < listOfNameLot.size(); i++){
                if(listOfNameLot.get(i).getText().contains(expectedName) && listOfPriceLot.get(i).getText().contains(expectedPrice)){
                  listOfNameLot.get(i).click();
                  System.out.println("Совпадение найдено");
                  break;
                }
              }
              return true;
            });
    return false;
  }

  protected boolean clickNextPageResult() {
    try {
      moveTo(showingItemsNextAfterCurrent);
      showingItemsNextAfterCurrent.click();
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}
