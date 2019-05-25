package ru.gostev.autotest.appmanager;

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

  public void verifyTitle(String expectedTitle){
    switchDriverToSecondTabOfBrowser();
    Assert.assertTrue(titleName.getText().equals(expectedTitle));
  }
}
