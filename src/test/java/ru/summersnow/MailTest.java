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

// Класс проверки почты на наличие новых писем
//////////////////////////////////////////////
public class MailTest extends JUnitTestBase {

  private AuthPage page;

  @BeforeEach
  public void initPageObjects() {
    page = PageFactory.initElements(driver, AuthPage.class);
    driver.get("https://mail.rambler.ru");
    //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
  }

  @Test // Авторизуемся
  public void testNewMail() {
    //driver.manage().timeouts().pageLoadTimeout(2,TimeUnit.SECONDS);
    page.emailField.sendKeys("test145029");
    page.passwordField.sendKeys("123pass");

    ((JavascriptExecutor) driver).executeScript("setTimeout(function(){document.evaluate('//button[@type=\"submit\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click()}, 5000);");

    waitForLoad(driver,"https://mail.rambler.ru/");
    System.out.println(driver.getCurrentUrl());

    MailPage page;
    page = PageFactory.initElements(driver, MailPage.class);
    System.out.println(page.inboxFolder.getAttribute("title"));
    Assertions.assertEquals(page.inboxFolder.getAttribute("title"), "Писем нет");
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

}
