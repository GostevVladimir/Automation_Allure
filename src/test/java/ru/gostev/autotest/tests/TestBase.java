package ru.gostev.autotest.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import ru.gostev.autotest.Listener.MyTestListener;
import ru.gostev.autotest.appmanager.ApplicationManager;

@Listeners(MyTestListener.class)
public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeMethod
  public void setUp(ITestContext context) throws Exception {
    app.init();
    context.setAttribute("app", app);
  }

  @AfterMethod
   public void tearDown() {
    app.stop();
  }
}
