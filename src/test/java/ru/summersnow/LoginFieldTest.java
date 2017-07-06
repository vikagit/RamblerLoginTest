package ru.summersnow;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.summersnow.pages.RegistrationPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

// класс тестирования поля Логин
//////////////////////////////////////////////
public class LoginFieldTest extends JUnitTestBase {

  private RegistrationPage page;

  @BeforeEach // перед каждым тестом. запускаем таймер ожидания для того, чтобы успели загрузиться сообщения об ошибке
  public void initPageObjects() {
    page = PageFactory.initElements(driver, RegistrationPage.class);
    driver.get(baseUrl+"/account/registration");
    driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
    page.firstnameField.sendKeys("Маша");
    page.lastnameField.sendKeys("Иванова");
    page.passwordField.sendKeys("sfdsg8483");
    page.passwordConfirmField.sendKeys("sfdsg8483");
   //page.dayField.getOptions();
   // page.monthField.sendKeys("Апрель");


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

  @Test //LF002 Проверяем, что поле Почтовый ящик не позволяет логин из двух букв
  public void testEmailTwoCharString(){
    page.emailField.sendKeys("rr");
    page.passwordField.sendKeys("sfdsg8483");
    page.passwordConfirmField.sendKeys("sfdsg8483");
    page.submitButton.submit();
    System.out.println("Запрет Email-а из двух букв");
    System.out.println(page.emailErrMsg.getText());
    Assertions.assertEquals(page.emailErrMsg.getText(),"Логин должен быть от 3 до 31 символов");
  }

  @Test //LF003 Проверяем, что поле Почтовый ящик не позволяет логин из 32 букв
  public void testEmail32CharString() {
    page.emailField.sendKeys("relgkfmvhroekgpwmvkrldnvtkremflt");
    page.submitButton.submit();
    System.out.println("Запрет Email-а из 32 букв");
    Assertions.assertEquals(page.emailErrMsg.getText(), "Логин должен быть от 3 до 31 символов");
  }
    @Test //LF004 Проверяем, что поле Почтовый ящик не позволяет логин из 1 цифры
    public void testEmailOneNumbers(){
      page.emailField.sendKeys("6");
      page.submitButton.submit();
      System.out.println("Запрет Логина из 1 цифры");
      Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }

  @Test //LF005 Проверяем, что поле Почтовый ящик не позволяет логин из 2 цифр
    public void testEmailTwoNumbers(){
    page.emailField.sendKeys("67");
    page.submitButton.submit();
    System.out.println("Запрет Логина из 2 цифр");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }

  @Test //LF006 Проверяем, что поле Почтовый ящик не позволяет логин из 32 цифр
  public void testEmail32Numbers(){
    page.emailField.sendKeys("12345678901234567890123456789012");
    page.submitButton.submit();
    System.out.println("Запрет Логина из более чем 31 цифры");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Логин должен быть от 3 до 31 символов");
  }
  @Test //LF007 Проверяем, что поле Почтовый ящик не позволяет логин из 32 цифр
  public void testEmailManySymbols(){
    page.emailField.sendKeys("1f03_wdwf-wfwefwe-eqwr033435-dwfwe023435m");
    page.submitButton.submit();
    System.out.println("Запрет Логина из более чем 31 символа");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Логин должен быть от 3 до 31 символов");
  }

  @Test //LF008 Проверяем, что Логин не может начинаться на спецсимвол
  public void testStartSpecSymbol(){
    page.emailField.sendKeys("_kdslffsg");
    page.submitButton.submit();
    System.out.println("Запрет Логина начинающегося со спецсимвола");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }
  @Test //LF009 Проверяем, что Логин не может заканчиваться на спецсимвол
  public void testEndSpecSymbol(){
    page.emailField.sendKeys("kdslffsg-");
    page.submitButton.submit();
    System.out.println("Запрет Логина заканчивающегося на спецсимвол");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }

  @Test //LF0010 Проверяем, что Логин не может содержать 2 одинаковых спецсимвола подряд
  public void testTwoSpecSymbol(){
    page.emailField.sendKeys("kds..lffsg");
    page.submitButton.submit();
    System.out.println("Запрет Логина содержащего 2 спецсимвола подряд");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }
  @Test //LF0011 Проверяем, что Логин не может состоять из строки сложной комбинации символов, некоторые из которых присутствуют в SQL, HTML, JS: “[|]’~< !--@/*$%^&#*/()?>,.*/\
  public void testManySpecSymbols(){
    page.emailField.sendKeys("“[|]’~< !--@/*$%^&#*/()?>,.*/\\");
    page.submitButton.submit();
    System.out.println("Запрет Логина содержащего сложную комбинацию спецсимволов");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Логин должен быть от 3 до 31 символов");
  }
  @Test // LF012 Проверяем, что поле Почтовый ящик не позволяет строку из пробелов
  public void testEmailStringOfSpaces(){
    page.emailField.sendKeys("     ");
    page.submitButton.submit();
    System.out.println("Запрет Email из пробелов");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }
  @Test // LF013 Проверяем, что Нельзя ввести логин начинающийся с пробела
  public void testEmailStartSpace(){
    page.emailField.sendKeys(" fhfter6ey");
    page.submitButton.submit();
    System.out.println("Запрет Email начинающегося с пробела");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }
  @Test // LF014 Проверяем, что Нельзя ввести логин заканчивающийся пробелом
  public void testEmailEndSpace(){
    page.emailField.sendKeys("grtrjryyku ");
    page.submitButton.submit();
    System.out.println("Запрет Email заканчивающегося на пробел");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }

  @Test // LF015 Проверяем, что Логин может состоять из 3 разрешенных символов
  public void testEmailThreeSymbols(){
    page.emailField.sendKeys("ff6");
    page.submitButton.submit();
    System.out.println("Email из 3 символов");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }
  @Test // LF016 Проверяем, что Логин может состоять из 31 разрешенного символа
  public void testEmail31Symbols(){
    page.emailField.sendKeys("23hgjkldfg654hbntukdp65gvhfryui");
    page.submitButton.submit();
    System.out.println("Логин из 31 буквы разрешен");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }

  @Test // LF017 Проверяем, что Логин может состоять из 10 разрешенных символов
  public void testEmail10Symbols(){
    page.emailField.sendKeys("12gh45jh78");
    page.submitButton.submit();
    System.out.println(" Email из 10 символов");
    boolean exists = driver.findElements( By.xpath("//input[@id=\"login.username\"]/../../../div[@class=\"src-components-Status-styles--message--cGbII\"]")).size() != 0;
    Assertions.assertFalse(exists);
  }

  @Test // LF018 Проверяем, что поле Почтовый ящик не позволяет кириллицу
  public void testEmailIncorrectChars(){
    page.emailField.sendKeys("павп");
    page.submitButton.submit();
    System.out.println("Запрет Email кириллицей");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }

  @Test // LF019 Проверяем, что Логин может состоять из 30 разрешенных символов
  public void testEmail30Symbols(){
    page.emailField.sendKeys("23hgjkldfg654hbntukdp65gvhfryu");
    page.submitButton.submit();
    System.out.println("Логин из 30 букв разрешен");
    Assertions.assertEquals(page.emailErrMsg.getText(),"Недопустимый логин");
  }

  @Test // Проверяем на какой сейчас странице находимся
  public void testCurrentURL(){
    System.out.println(driver.getCurrentUrl());
    Assertions.assertEquals(driver.getCurrentUrl(),"https://id.rambler.ru/account/registration");
  }
 /* public void waitForLoad(WebDriver driver, String initpage) {
    ExpectedCondition<Boolean> pageLoadCondition = new
            ExpectedCondition<Boolean>() {
              public Boolean apply(WebDriver driver) {
                return !driver.getCurrentUrl().equals(initpage);
              }
            };
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(pageLoadCondition);
  }
  */
}
