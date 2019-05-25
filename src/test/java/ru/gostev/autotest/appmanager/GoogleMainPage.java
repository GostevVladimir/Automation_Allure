package ru.gostev.autotest.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class GoogleMainPage extends HelperBase{

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


  @Step
  public void search(String text){
    type(searchString,text);
  }

  @Step
  public void openFirstLink(){
    click(firstLink);
  }

  @Step
  public void openSearchPage(){
    wd.get(URL);
  }

}


