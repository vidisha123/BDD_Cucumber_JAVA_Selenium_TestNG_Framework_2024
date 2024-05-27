package main.stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.cucumber.java.en.*;
import main.pageObject.LoginPage;

public class UI_StepDefinitions {
	
	//WebDriver driver;
	
	private WebDriver driver;
    private LoginPage login;
	
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

    @After(order=1)
    public void takeScreenshotOnFailure(Scenario sc) throws IOException{
    	String status = sc.getStatus().toString();
		System.out.println("Test Status: "+status);
		
		
		Calendar today = Calendar.getInstance();
		today.clear(Calendar.HOUR); today.clear(Calendar.MINUTE); today.clear(Calendar.SECOND);
		Date todayDate = today.getTime();
		String currentDateTime = todayDate.toString();
				
		if(sc.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot)driver;
			byte[] src = ts.getScreenshotAs(OutputType.BYTES);
			//File dst = new File("target/FailedScreenShots/Screenshot "+currentDateTime+".png");
			//FileHandler.copy(src, dst);
			
			
			sc.attach(src, "image/png", "Screenshot");
			
		}
        
        
    }
    
    @After(order=0)
    public void tearDown(){
    	
        if(driver!=null){
        	driver.close();
            driver.quit();
        }
        
    }
    
    //Below are step definitions
	
	
	@Given("^User launches URL$")
	public void User_launches_URL() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		login = new LoginPage(driver);
		
	}
	
	
	@When("^User gives valid credentials$")
	public void User_gives_valid_credentials() {
		login.enterEmail("Admin");
		login.enterPassword("admin123");
		login.clickLoginButton();
		
	}
		
	
	
	@Then("^User lands on home page$")
	public void User_lands_on_home_page(){
		login.checkHomePage();
	}
		
	
	
	@When("^User gives invalid credentials$")
	public void User_gives_invalid_credentials() {
		login.enterEmail("Admin");
		login.enterPassword("admin12345");
		login.clickLoginButton();
	}
		
	
	
	@Then("^User sees error message$")
	public void User_sees_error_message(){
		String invalid = login.checkErrorMessage();
		Assert.assertEquals(invalid, "Invalid credentialsss");
	}
	
	@When ("^User clicks on forgot password$")
	public void User_clicks_on_forgot_password(){
		login.clickForgottenPassword();
	}
	
	@Then ("^User lands on Reset Password page$")
	public void User_lands_on_Reset_Password_page(){
		String reset = login.checkResetPassword();
		Assert.assertEquals(reset, "Reset Password");
	}
		
	

}

