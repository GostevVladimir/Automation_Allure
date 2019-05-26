package ru.gostev.autotest.appmanager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver wd;
  private GoogleMainPage googleMainPage;
  private WikipediaPage WikipediaPage;


  public void init() {
    wd = new ChromeDriver();
    wd.manage().window().maximize();
    wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    googleMainPage = new GoogleMainPage(wd);
    googleMainPage.openSearchPage();
    WikipediaPage = new WikipediaPage(wd);
  }

  public void stop() {
    wd.quit();
  }

  public GoogleMainPage getGoogleMainPage() {
    return googleMainPage;
  }
  public WikipediaPage getWikipediaPage() {
    return WikipediaPage;
  }

  public byte[] takeScreenshot(){
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }
}
