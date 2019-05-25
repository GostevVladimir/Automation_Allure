package ru.gostev.autotest.appmanager;

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


  public void search(String text){
    type(searchString,text);
  }

  public void openFirstLink(){
    click(firstLink);
  }

  public void openSearchPage(){
    wd.get(URL);
  }

}


