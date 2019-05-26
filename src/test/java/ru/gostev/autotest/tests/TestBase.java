package ru.gostev.autotest.tests;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import ru.gostev.autotest.Listener.MyTestListener;
import ru.gostev.autotest.appmanager.ApplicationManager;

@Listeners(MyTestListener.class)
public class TestBase {

  protected final ApplicationManager app = new ApplicationManager();

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
