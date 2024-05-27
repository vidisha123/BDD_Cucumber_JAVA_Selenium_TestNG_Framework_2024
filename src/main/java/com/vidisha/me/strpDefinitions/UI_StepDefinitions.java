package com.vidisha.me.strpDefinitions;

import java.time.Duration;
import java.util.Collection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.cucumber.java.en.*;
import PageObjects.Login;

public class UI_StepDefinitions {
	
	//WebDriver driver;
	
	private WebDriver driver;
    private Login login;
	
	//Below are Hooks
	
	@Before
    public void setup(Scenario sc){
		String ScenarioName = sc.getName();
		Collection c = sc.getSourceTagNames();
		System.out.println("Scenario Name: "+ScenarioName);
		System.out.println("Tags includes: "+c.toString());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
    }

    @After
    public void tearDown(Scenario sc){
    	Status status = sc.getStatus();
		System.out.println("Test Status: "+status);
        if(driver!=null){
        	driver.close();
            driver.quit();
        }
        
    }
    
    //Below are step definitions
	
	
	@Given("^User launches URL$")
	public void User_launches_URL() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		login = new Login(driver);
		
	}
	
	
	@When("^User gives valid credentials$")
	public void User_gives_valid_credentials() {
		//driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		//driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		//driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		login.enterEmail("Admin");
		login.enterPassword("admin123");
		login.clickLoginButton();
		
	}
		
	
	
	@Then("^User lands on home page$")
	public void User_lands_on_home_page(){
		
		//String dash = driver.findElement(By.xpath("//h6[text()='Dashboard']")).getText();
		//Assert.assertEquals(dash, "Dashboard");
		login.checkHomePage();
	}
		
	
	
	@When("^User gives invalid credentials$")
	public void User_gives_invalid_credentials() {
		//driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		//driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin12356");
		//driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		login.enterEmail("Admin");
		login.enterPassword("admin12345");
		login.clickLoginButton();
	}
		
	
	
	@Then("^User sees error message$")
	public void User_sees_error_message(){
		//String invalid = driver.findElement(By.xpath("//p[text()='Invalid credentials']")).getText();
		String invalid = login.checkErrorMessage();
		Assert.assertEquals(invalid, "Invalid credentials");
	}
	
	@When ("^User clicks on forgot password$")
	public void User_clicks_on_forgot_password(){
		//WebElement forgot = driver.findElement(By.xpath("//p[text()='Forgot your password? ']"));
		//forgot.click();
		login.clickForgottenPassword();
	}
	
	@Then ("^User lands on Reset Password page$")
	public void User_lands_on_Reset_Password_page(){
		//String reset = driver.findElement(By.xpath("//h6[text()='Reset Password']")).getText();
		String reset = login.checkResetPassword();
		Assert.assertEquals(reset, "Reset Password");
	}
		
	

}
