package ru.gostev.autotest.tests;

import org.testng.annotations.Test;

public class EbayTest  extends TestBase{

  @Test
  public void ebayTest(){
    app.getEbayMainPage().clickShopByCategoryDropDown();
    app.getEbayMainPage().selectOptionFromShopCategoryDropDown("Мобильные телефоны и аксессуары");
    app.getCellPhonesAndAccessoriesPage().selectOptionFromShopByCategory("Сотовые телефоны и смартфоны");

    app.getCellPhonesAndAccessoriesPage().clickMoreRefinements();

    app.getCellPhonesAndAccessoriesPage().atAndTClick();
    app.getCellPhonesAndAccessoriesPage().sprintClick();
    app.getCellPhonesAndAccessoriesPage().verizonClick();
    app.getCellPhonesAndAccessoriesPage().brandButtonClick();
    app.getCellPhonesAndAccessoriesPage().samsungClick();
    app.getCellPhonesAndAccessoriesPage().motorolaClick();
    app.getCellPhonesAndAccessoriesPage().appleClick();

    app.getCellPhonesAndAccessoriesPage().priceButtonClick();
    app.getCellPhonesAndAccessoriesPage().setPriceFrom("100");
    app.getCellPhonesAndAccessoriesPage().setPriceTo("700");
    app.getCellPhonesAndAccessoriesPage().applyButtonClick();


    app.getCellPhonesAndAccessoriesPage().buyItNowRadiobuttonClick();

//    String lotName = app.getCellPhonesAndAccessoriesPage().getNameThirdLot();
//    String lotPrice = app.getCellPhonesAndAccessoriesPage().getPriceThirdLot();
//
//    app.getCellPhonesAndAccessoriesPage().clickMoreRefinements();
//    app.getCellPhonesAndAccessoriesPage().deactivateBrandFilter();
//
//    app.getCellPhonesAndAccessoriesPage().clickMoreRefinements();
//    app.getCellPhonesAndAccessoriesPage().setNewPrice(lotPrice);
//
//    app.getCellPhonesAndAccessoriesPage().findLotByNameAmdPrice(lotName, lotPrice);
//    app.getLotPage().verifyTitle(lotName);
//    app.getLotPage().printCountOfStarsAndReviews();
  }
}
