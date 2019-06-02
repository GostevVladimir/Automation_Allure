package ru.gostev.autotest.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class LotPage extends BasePage {

  public LotPage(WebDriver wd) {
    super(wd);
    PageFactory.initElements(wd, this);
  }

  @FindBy(xpath = ".//*[@id='histogramid']//li")
  private List<WebElement> ebayReviewList;

  @FindBy(id = "itemTitle")
  private WebElement titleName;

  @Step("Вывести колличество всех отзывов")
  public void printCountOfStarsAndReviews(){
    for(int i = 0; i < ebayReviewList.size(); i++){
      System.out.println(ebayReviewList.get(i).getAttribute("aria-label"));
    }
  }
  @Step("Вполнить проверку тайтла {expectedTitle}")
  public void verifyTitle(String expectedTitle){
    Assert.assertTrue(titleName.getText().split("- ", 2)[0].equals(expectedTitle));
  }
}
