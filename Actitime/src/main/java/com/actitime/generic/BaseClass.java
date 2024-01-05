package com.actitime.generic;

public class BaseClass {

	import java.io.IOException;
	import java.time.Duration;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Reporter;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeTest;

	import com.actitime.pom.HomePage1;
	import com.actitime.pom.LoginPage1;

	public class BaseClass {
		public static WebDriver driver;
		@BeforeTest
		public void openBrowser() {
			Reporter.log("openBrowser", true);
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}

		@AfterTest
		public void closeBrowser() {
			Reporter.log("closeBrowser",true);
			driver.close();
		}

		@BeforeMethod
		public void login() throws IOException {
			Reporter.log("login", true);
			FileLib f=new FileLib();
			String url = f.getProperData("url");
			String un = f.getProperData("username");
			String pw = f.getProperData("password");
			driver.get(url);
			LoginPage1 l = new LoginPage1(driver);
			l.setLogin(un, pw);
		}
		
		@AfterMethod
		public void logout() {
			Reporter.log("logout", true);
			HomePage1 h = new HomePage1(driver);
			h.setLogout();
		}
	}

}
