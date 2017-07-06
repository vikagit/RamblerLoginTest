package ru.summersnow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import ru.summersnow.pages.RegistrationPage;

import java.util.concurrent.TimeUnit;

//Класс тестирования поля "Пароль"
//////////////////////////////////////////////
public class PasswordFieldTest extends JUnitTestBase {

  private RegistrationPage page;

  @BeforeEach // перед каждым тестом. запускаем таймер ожидания для того, чтобы успели загрузиться сообщения об ошибке
  public void initPageObjects() {
    page = PageFactory.initElements(driver, RegistrationPage.class);
    driver.get(baseUrl+"/account/registration");
    driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
    page.firstnameField.sendKeys("Маша");
    page.lastnameField.sendKeys("Иванова");
    page.emailField.sendKeys("Masha");
   //page.dayField.getOptions();
   // page.monthField.sendKeys("Апрель");
  }

  @Test //PF001 Проверяем, что не Пароль не может состоять из 1 символа
  public void testPasswordOneCharString(){
    page.passwordField.sendKeys("r");
    page.submitButton.submit();
    System.out.println("Запрет пароля из одной буквы");
    Assertions.assertEquals(page.passwordErrMsg.getText(),"Недопустимый пароль");
  }

  @Test //PF002 Проверяем, что  Пароль не может состоять из 2 символов
  public void testPasswordTwoCharString(){
    page.passwordField.sendKeys("r");
    page.submitButton.submit();
    System.out.println("Запрет пароля из 2 символов");
    Assertions.assertEquals(page.passwordErrMsg.getText(),"Недопустимый пароль");
  }

  @Test //PF003 Проверяем, что Пароль не может состоять из 33 символов
  public void testPassword33CharString(){
    page.passwordField.sendKeys("1234_67890123gfssdd-67890123tyuio");
    page.submitButton.submit();
    System.out.println("Запрет пароля из 33 символов");
    Assertions.assertEquals(page.passwordErrMsg.getText(),"Недопустимый пароль");
  }

  @Test //PF004 Проверяем, что Пароль не может состоять из 1 цифры
  public void testPasswordOneNumberString(){
    page.passwordField.sendKeys("7");
    page.submitButton.submit();
    System.out.println("Запрет пароля из одной цифры");
    Assertions.assertEquals(page.passwordErrMsg.getText(),"Недопустимый пароль");
  }

  @Test //PF005 Пароль не может состоять из 2 цифр
  public void testPasswordTwoNumberString(){
    page.passwordField.sendKeys("37");
    page.submitButton.submit();
    System.out.println("Запрет пароля из двух цифр");
    Assertions.assertEquals(page.passwordErrMsg.getText(),"Недопустимый пароль");
  }

  @Test //PF006 Пароль не может состоять из 33 цифр
  public void testPassword33NumberString(){
    page.passwordField.sendKeys("123456789012345678901234567890123");
    page.submitButton.submit();
    System.out.println("Запрет пароля из 33 цифр");
    Assertions.assertEquals(page.passwordErrMsg.getText(),"Недопустимый пароль");
  }

  @Test //PF007 Пароль может состоять из строки сложной комбинации спецсимволов, некоторые из которых присутствуют в SQL, HTML, JS: “[|]’~< !--@/*$%^&#*/()?>,.*/\
  public void testPasswordSpecSimbolString(){
    page.passwordField.sendKeys("“[|]’~< !--@/*$%^&#*/()?>,.*/\\");
    page.submitButton.submit();
    System.out.println("Запрет пароля из сложной комбинации спецсимволов");
    Assertions.assertEquals(page.passwordErrMsg.getText(),"Недопустимый пароль");
  }

  @Test //PF008 Пароль не может состоять из строки пробелов
  public void testPasswordSpaceString(){
    page.passwordField.sendKeys("     ");
    page.submitButton.submit();
    System.out.println("Запрет пароля из пробелов");
    Assertions.assertEquals(page.passwordErrMsg.getText(),"Недопустимый пароль");
  }

  @Test //PF009 Нельзя ввести Пароль начинающийся с пробела
  public void testPasswordStartSpaceString(){
    page.passwordField.sendKeys("_5hrdg");
    page.submitButton.submit();
    System.out.println("Запрет пароля начинающегося с пробела");
    Assertions.assertEquals(page.passwordErrMsg.getText(),"Недопустимый пароль");
  }

  @Test //PF0010  Нельзя ввести Пароль заканчивающийся пробелом
  public void testPasswordЕndSpaceString(){
    page.passwordField.sendKeys("5hrdg ");
    page.submitButton.submit();
    System.out.println("Запрет пароля заканчивающегося на пробел");
    Assertions.assertEquals(page.passwordErrMsg.getText(),"Недопустимый пароль");
  }

  @Test //PF0011  Пароль может состоять из 6 разрешенных символов
  public void testPassword6CharString(){
    page.passwordField.sendKeys("5hrdgt");
    page.submitButton.submit();
    System.out.println("Пароль из 6 разрешенных символов ");
    boolean exists = driver.findElements( By.xpath("//input[@id=\"password.main\"]/../../../div[@class=\"src-components-Status-styles--message--cGbII\"]")).size() != 0;
  }

  @Test //PF0012  Пароль не может состоять из 32 разрешенных символов
  public void testPassword32CharString(){
    page.passwordField.sendKeys("ptlrkei8gk_tu-tue.dlwp95kdjfiru5");
    page.submitButton.submit();
    System.out.println("Пароль из 32 разрешенных символов ");
    Assertions.assertEquals(page.passwordErrMsg.getText(),"Недопустимый пароль");
  }

  @Test //PF0013  Пароль может состоять из 10 разрешенных символов
  public void testPassword10CharString(){
    page.passwordField.sendKeys("ptlrkei8gk");
    page.submitButton.submit();
    System.out.println("Пароль из 10 разрешенных символов ");
    boolean exists = driver.findElements( By.xpath("//input[@id=\"password.main\"]/../../../div[@class=\"src-components-Status-styles--message--cGbII\"]")).size() != 0;
    Assertions.assertFalse(exists);
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
