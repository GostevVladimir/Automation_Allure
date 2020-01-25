package ru.gostev.autotest.tests;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class FirstTest extends TestBase{
    @Test
    public void testFirst(){
        app.getGoogleMainPage().search("Пенза");
        app.getGoogleMainPage().openFirstLink();
        app.getWikipediaPage().verifyTitle("Пенза");
    }

}
