package ru.gostev.autotest.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class WikipediaPage extends HelperBase{

  public WikipediaPage(WebDriver wd) {
    super(wd);
    PageFactory.initElements(wd, this);
  }

  @FindBy(id = "firstHeading")
  private WebElement titleName;

  @Step
  public void verifyTitle(String expectedTitle){
    switchDriverToSecondTabOfBrowser();
    Assert.assertTrue(titleName.getText().equals(expectedTitle));
  }
}
