package ru.gostev.autotest.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


public class BasePage {
  protected WebDriver wd;
  protected final static int WAITING_TIME_IN_SECONDS = 30;

  public BasePage(WebDriver wd) {
    this.wd = wd;
  }

  protected void type(WebElement locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = locator.getAttribute("value");
      if (!text.equals(existingText)) {
        locator.clear();
        locator.sendKeys(text);
        locator.sendKeys(Keys.ENTER);
      }
    }
  }

  protected void click(WebElement locator) {
    locator.click();
  }

  public static void selectOptionFromDropDownByText(WebElement elem, String option) {
    List<WebElement> list = elem.findElements(By.xpath("following-sibling::*//li"));
    for (WebElement element : list) {
      if (element.getText().equals(option)) {
        element.click();
        return;
      }
    }
  }

  public static void dependableClick(WebDriver driver, WebElement element) {
    final int MAXIMUM_WAIT_TIME = 15;
    final int MAX_STALE_ELEMENT_RETRIES = 10;

    WebDriverWait wait = new WebDriverWait(driver, MAXIMUM_WAIT_TIME);
    int retries = 0;
    while (true) {
      try {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        clickElementByJS(driver, element);
        return;
      } catch (StaleElementReferenceException e) {
        if (retries < MAX_STALE_ELEMENT_RETRIES) {
          retries++;
          continue;
        } else {
          throw e;
        }
      }
    }
  }

  protected static void clickElementByJS(WebDriver driver, WebElement element) {
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("console.log(arguments[0]);arguments[0].click();", element);
  }

  protected void assertEqualsList(String[] before, String[] after) {
    for (int i = 0; i < before.length; i++) {
      Assert.assertEquals(before[i], after[i]);
    }
  }

  protected List<WebElement> getWebElements(By element) {
    List<WebElement> elements = wd.findElements(element);
    return elements;
  }

  protected WebElement getElementList(String nameElementList, List<WebElement> elements) {
    for (int i = 0; i < elements.size(); i++) {
      if (nameElementList.equals(elements.get(i).getText())) {
        return elements.get(i);
      }
    }
    return null;
  }

  protected void moveTo(WebElement element) {
    Actions action = new Actions(wd);
    action.moveToElement(element).perform();
  }

  protected void clickToLink(WebElement locator, WebDriver driver, int timeout) {
    final WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.until(ExpectedConditions.refreshed(
            ExpectedConditions.elementToBeClickable(locator)));
    locator.click();
  }

  protected void waitForJQuery() {
    (new WebDriverWait(wd, 2000)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return jQuery.active == 0");
      }
    });
  }

  public static WebElement waitForElementPresent(WebElement element, WebDriver driver) {
    return (new WebDriverWait(driver, WAITING_TIME_IN_SECONDS))
            .until(ExpectedConditions.visibilityOf(element));
  }

  protected void waitForElementPresentAndClick(WebElement element, WebDriver driver) {
    waitForElementPresent(element, driver).click();
  }

  protected void switchDriverToSecondTabOfBrowser() {
    ArrayList<String> windows = new ArrayList<>(wd.getWindowHandles());
    windows.forEach(s -> System.out.println());
    String secondTabName = new ArrayList<>(wd.getWindowHandles()).get(1);
    wd.switchTo().window(secondTabName);
    System.setProperty("current.window.handle", secondTabName);
  }

  protected void switchDriverToFirstTabOfBrowser() {
    String firstTabName = new ArrayList<>(wd.getWindowHandles()).get(0);
    wd.switchTo().window(firstTabName);
    System.setProperty("current.window.handle", firstTabName);
  }

  protected void switchDriverToAnyTabOfBrowser(int tabIndex) {
    String parentHandle = new ArrayList<>(wd.getWindowHandles()).get(0);
    String anyTabName = new ArrayList<>(wd.getWindowHandles()).get(tabIndex);
    wd.switchTo().window(anyTabName);
    System.setProperty("current.window.handle", parentHandle);
  }

  protected void closeDriverToSecondTabOfBrowser() {
    String firstTabName = new ArrayList<>(wd.getWindowHandles()).get(0);
    String secondTabName = new ArrayList<>(wd.getWindowHandles()).get(1);
    wd.switchTo().window(secondTabName);
    wd.close();
    System.setProperty("close.current.window.handle", firstTabName);
  }

  protected void closeDriverToAnyTabOfBrowser(int tabIndex) {
    String parentHandle = new ArrayList<>(wd.getWindowHandles()).get(0);
    String anyTabName = new ArrayList<>(wd.getWindowHandles()).get(tabIndex);
    wd.switchTo().window(anyTabName);
    wd.close();
    System.setProperty("close.current.window.handle", parentHandle);
  }

  protected void navigateBackPage() {
    wd.navigate().back();
  }

}
