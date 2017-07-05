package ru.summersnow.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public class RegistrationPage extends Page{

    @FindBy(how = How.ID_OR_NAME, using = "firstname") //поле Имя
    @CacheLookup
    public WebElement firstnameField;

    @FindBy(how = How.ID_OR_NAME, using = "lastname") //поле Фамилия
    @CacheLookup
    public WebElement lastnameField;

    @FindBy(how = How.ID_OR_NAME, using = "login.username") //поле Почтовый ящик
    @CacheLookup
    public WebElement emailField;

    @FindBy(how = How.XPATH,using = "//input[@id=\"login.username\"]/../../../div[@class=\"src-components-Status-styles--message--cGbII\"]") //сообщение об ошибке поля Почтовый ящик
    public WebElement emailErrMsg;

    @FindBy(how = How.ID_OR_NAME, using = "password.main") //поле Пароль
    @CacheLookup
    public WebElement passwordField;

    @FindBy(how = How.ID_OR_NAME, using = "password.confirm") //поле Повтор пароля
    @CacheLookup
    public WebElement passwordConfirmField;

    @FindBy(how = How.XPATH,using = "//button[@type=\"submit\"]") //кнопка ЗАРЕГИСТРИРОВАТЬСЯ
    public WebElement submitButton;




    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }
}
