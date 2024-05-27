package main.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	private WebDriver driver;

    // Locators
    private By emailInputLocator = By.xpath("//input[@name='username']");
    private By passwordInputLocator = By.xpath("//input[@name='password']");
    private By loginButtonLocator = By.xpath("//button[text()=' Login ']");
    private By dashboardLocator = By.xpath("//h6[text()='Dashboard']");
    private By forgotPasswordLocator = By.xpath("//p[text()='Forgot your password? ']");
    private By errorMessageLocator = By.xpath("//p[text()='Invalid credentials']");
    private By resetPasswordLocator = By.xpath("//h6[text()='Reset Password']");
    private By logoutLinkLocator = By.linkText("Logout");
    


    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Methods
    public void enterEmail(String email) {
        WebElement emailInput = driver.findElement(emailInputLocator);
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordInputLocator);
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
    }
    
    public String checkHomePage() {
        return driver.findElement(dashboardLocator).getText();
    }

    
    public String checkErrorMessage(){
        return driver.findElement(errorMessageLocator).getText();
    }
    
    public void clickForgottenPassword() {
        driver.findElement(forgotPasswordLocator).click();
    }
    
    public String checkResetPassword() {
        return driver.findElement(resetPasswordLocator).getText();
    }

    public boolean checkLogoutLink(){
        return driver.findElement(logoutLinkLocator).isDisplayed();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public String getForgotPwdPageUrl(){
        String forgotPwdPageUrl = driver.getCurrentUrl();
        return forgotPwdPageUrl;
    }



}
