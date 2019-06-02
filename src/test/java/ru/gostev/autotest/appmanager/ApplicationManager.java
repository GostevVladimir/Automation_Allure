package ru.gostev.autotest.appmanager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver wd;
  private GoogleMainPage googleMainPage;
  private WikipediaPage WikipediaPage;
  private EbayMainPage ebayMainPage;
  private CellPhonesAndAccessoriesPage cellPhonesAndAccessoriesPage;
  private LotPage lotPage;
  private Properties properties;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if(browser.equals(BrowserType.CHROME)) {
      if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      }else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }
    }

    wd.manage().window().maximize();
    wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    googleMainPage = new GoogleMainPage(wd);
    ebayMainPage = new EbayMainPage(wd);
    cellPhonesAndAccessoriesPage = new CellPhonesAndAccessoriesPage(wd);
    lotPage = new LotPage(wd);
    WikipediaPage = new WikipediaPage(wd);
  }

  public void stop() {
    wd.quit();
  }

  public GoogleMainPage getGoogleMainPage() {
    return googleMainPage;
  }
  public EbayMainPage getEbayMainPage() {
    return ebayMainPage;
  }
  public WikipediaPage getWikipediaPage() {
    return WikipediaPage;
  }

  public CellPhonesAndAccessoriesPage getCellPhonesAndAccessoriesPage() {
    return cellPhonesAndAccessoriesPage;
  }

  public LotPage getLotPage() {
    return lotPage;
  }

  public byte[] takeScreenshot(){
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }
}
