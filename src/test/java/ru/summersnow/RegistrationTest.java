package ru.summersnow;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.summersnow.pages.RegistrationPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegistrationTest extends JUnitTestBase {

  private RegistrationPage page;

  @BeforeEach // перед каждым тестом. запускаем таймер ожидания для того, чтобы успели загрузиться сообщения об ошибке
  public void initPageObjects() {
    page = PageFactory.initElements(driver, RegistrationPage.class);
    driver.get(baseUrl+"/account/registration");

  }

  @Test // Проверяем, что поле Почтовый ящик пустое
  public void testIsEmailEmptyOnStart() {
    System.out.println("Проверяем, что поле Почтовый ящик пустое");
    Assertions.assertTrue(page.emailField.getText().equals(""));
  }

  @Test //LF001 Проверяем, что поле Почтовый ящик не позволяет логин из одной буквы
  public void testEmailOneCharString(){
    page.emailField.sendKeys("r");
    page.submitButton.submit();
    System.out.println("Запрет Email-а из одной буквы");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }

  @Test // LF012 Проверяем, что поле Почтовый ящик не позволяет строку из пробелов
  public void testEmailStringOfSpaces(){
    page.emailField.sendKeys("     ");
    page.submitButton.submit();
    System.out.println("Запрет Email из пробелов");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }

  @Test // LF018 Проверяем, что поле Почтовый ящик не позволяет кириллицу
  public void testEmailIncorrectChars(){
    page.emailField.sendKeys("павп");
    page.submitButton.submit();
    System.out.println("Запрет Email кириллицей");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }

  @Test // Проверяем на какой сейчас странице находимся
  public void testCurrentURL(){
    System.out.println(driver.getCurrentUrl());
    Assertions.assertEquals(driver.getCurrentUrl(),"https://id.rambler.ru/account/registration");
  }

}
