package ru.summersnow.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

//Описание страницы почтового ящика
public class MailPage extends Page{

    @FindBy(how = How.XPATH, using = "//li[@class=\"style-root_1bj style-current_m4I\"]") //поле Почтовый ящик
    @CacheLookup
    public WebElement inboxFolder;



    public MailPage(WebDriver webDriver) {
        super(webDriver);
    }
}
