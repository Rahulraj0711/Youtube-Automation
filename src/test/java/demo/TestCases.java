package demo;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class TestCases {
    ChromeDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void createDriver() {
        System.out.println("Creating Driver and Starting Test Execution...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com/");
    }

    @Test(enabled = true)
    public void testCase01() {
        System.out.println("Start Test Case: testCase01");

        
        
        System.out.println("End Test Case: testCase01");
    }

    @AfterSuite
    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

}
