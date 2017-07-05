package ru.summersnow.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AuthPage extends Page{

    @FindBy(how = How.ID_OR_NAME, using = "login") //поле Почтовый ящик
    @CacheLookup
    public WebElement emailField;

    @FindBy(how = How.ID_OR_NAME, using = "password") //поле Пароль
    @CacheLookup
    public WebElement passwordField;

    @FindBy(how = How.XPATH,using = "//button[@type=\"submit\"]") //кнопка ЗАРЕГИСТРИРОВАТЬСЯ
    @CacheLookup
    public WebElement submitButton;

    public AuthPage(WebDriver webDriver) {
        super(webDriver);
    }
}
