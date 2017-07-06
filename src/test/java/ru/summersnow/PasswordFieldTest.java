package ru.summersnow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
