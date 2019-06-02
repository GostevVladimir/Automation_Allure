package ru.gostev.autotest.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EbayMainPage extends BasePage{

  public EbayMainPage(WebDriver wd) {
    super(wd);
    PageFactory.initElements(wd, this);
  }

  @FindBy(xpath = "//*[@name='q']")
  private WebElement searchString;

  @FindBy(xpath = "//*[@name='btnK']")
  private WebElement searchButton;

  @FindBy(xpath = "(.//*[@id='rso']//h3)[1]")
  private WebElement firstLink;

  @FindBy(id = "gh-shop-a")
  private WebElement shopByCategoryDropDown;

  @FindBy(id = "gh-sbc-o")
  private WebElement shopByCategoryDropDownOption;

  @Step("Открыть покупку по категориям")
  public void clickShopByCategoryDropDown(){
    shopByCategoryDropDown.click();
  }

  @Step ("Выбрать {option}")
  public void selectOptionFromShopCategoryDropDown(String option) {
    if (shopByCategoryDropDownOption.isDisplayed()) {
      selectOptionFromDropDownByText(shopByCategoryDropDown, option);
    }
  }
}
