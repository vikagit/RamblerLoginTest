package ru.summersnow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.summersnow.pages.AuthPage;
import ru.summersnow.pages.MailPage;
import ru.summersnow.pages.RegistrationPage;

import java.util.concurrent.TimeUnit;

public class MailTest extends JUnitTestBase {

  private AuthPage page;


  @BeforeEach
  public void initPageObjects() {
    page = PageFactory.initElements(driver, AuthPage.class);
    driver.get("https://id.rambler.ru/login-20/?#login");
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test // Авторизуемся
  public void testEmailStringOfSpaces() {
    driver.manage().timeouts().pageLoadTimeout(2,TimeUnit.SECONDS);
    page.emailField.sendKeys("test145029@");
    page.passwordField.sendKeys("123pass");

   // wait.until(new ExpectedCondition<Boolean>() {
     //            private int i = 0;
     //            public Boolean apply(WebDriver driver) {
      //             WebElement elm = driver.findElement(By.name("login"));
      //             System.out.println("->"+i);
      //             i++;
      //             return i>20;
      //           }});

    //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    //driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);

  //  page.submitButton.click();
    ((JavascriptExecutor) driver).executeScript("setTimeout(function(){document.getElementById('myCheck').click()}, 5000);");

    waitForLoad(driver,"https://id.rambler.ru/login-20/?#login");
    System.out.println(driver.getCurrentUrl());
    Assertions.assertEquals(driver.getCurrentUrl(),"https://id.rambler.ru/account/#profile");
  }

  public void waitForLoad(WebDriver driver,String initpage) {
    ExpectedCondition<Boolean> pageLoadCondition = new
            ExpectedCondition<Boolean>() {
              public Boolean apply(WebDriver driver) {
                return !driver.getCurrentUrl().equals(initpage);
              }
            };
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(pageLoadCondition);
  }

 /* @Test // Проверяем на какой сейчас странице находимся
  public void testCurrentURL(){
    System.out.println(driver.getCurrentUrl());
    Assertions.assertEquals(driver.getCurrentUrl(),"https://id.rambler.ru/login-20/?#login");

  }*/
}
