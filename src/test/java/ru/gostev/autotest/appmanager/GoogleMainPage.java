package ru.gostev.autotest.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleMainPage extends BasePage {

  public GoogleMainPage(WebDriver wd) {
    super(wd);
    PageFactory.initElements(wd, this);
  }

  private final String URL = "https://www.google.ru/";

  @FindBy(xpath = "//*[@name='q']")
  private WebElement searchString;

  @FindBy(xpath = "//*[@name='btnK']")
  private WebElement searchButton;

  @FindBy(xpath = "(.//*[@id='rso']//h3)[1]")
  private WebElement firstLink;


  @Step ("Поиск {text}")
  public void search(String text){
    type(searchString,text);
  }

  @Step ("Переход по первой ссылки")
  public void openFirstLink(){
    click(firstLink);
  }

  @Step ("Открыта главная страница поиска Google")
  public void openSearchPage(){
    wd.get(URL);
  }

}


