package demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class TestCases {
    ChromeDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void createDriver() {
        System.out.println("Creating Driver and Starting Test Execution...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(enabled = true, priority = 1)
    public void testCase01() throws InterruptedException {
        System.out.println("Start Test Case: testCase01");
        driver.get("https://www.youtube.com/");
        
        // Check if the url contains "youtube" 
        Assert.assertTrue(driver.getCurrentUrl().contains("youtube"));

        // Click on the menu button and wait until about link is visible
        WrapperMethods.wrap_guideMenu(driver, By.xpath("//div[@id='footer']//a[text()='About']"));

        WebElement aboutLink=WrapperMethods.wrap_findElement(driver, By.xpath("//div[@id='footer']//a[text()='About']"));
        WrapperMethods.wrap_scrollToElement(driver, aboutLink);

        // Click on the about link and wait until the about content is visible
        WrapperMethods.wrap_advancedClick(driver, aboutLink, By.cssSelector("section.ytabout__content h1"));
                
        // Store the list of content present on the About YouTube page
        List<WebElement> aboutContent=WrapperMethods.wrap_findElements(driver, By.cssSelector("section.ytabout__content p"));
        
        // Print the content shown on the About YouTube page
        for(WebElement e:aboutContent) {
            WrapperMethods.wrap_printMessage(e.getText());
        }

        // Navigate back to yt home page
        driver.navigate().back();
        
        System.out.println("End Test Case: testCase01");
    }

    @Test(enabled = true, priority = 2)
    public void testCase02() throws InterruptedException {
        System.out.println("Start Test Case: testCase02");
        driver.get("https://www.youtube.com/");
        
        // Click on the menu button and wait until films link is visible
        WrapperMethods.wrap_guideMenu(driver, By.xpath("//div[@id='sections']/ytd-guide-section-renderer[3]/div//yt-formatted-string[text()='Films']/../.."));

        // Find films link and scroll to films link
        WebElement filmsLink=WrapperMethods.wrap_findElement(driver, By.xpath("//div[@id='sections']/ytd-guide-section-renderer[3]/div//yt-formatted-string[text()='Films']/../.."));
        WrapperMethods.wrap_scrollToElement(driver, filmsLink);

        // Click on the films link and wait until the next button is visible for Top selling movies
        WrapperMethods.wrap_advancedClick(driver, filmsLink, By.xpath("//span[text()='Top selling']/../../../../../../../..//div[@id='contents']//button[@aria-label='Next']"));

        // Get the rating and genre of the last movie from the Top selling section
        String[] arr=WrapperMethods.wrap_tc02_getDetailsOfLastMovie(driver);

        // Soft asserts for checking expected rating and genre
        SoftAssert sAssert=new SoftAssert();
        sAssert.assertEquals(arr[0], "A", "The movie's rating is: "+arr[0]);
        sAssert.assertTrue(arr[1].contains("Comedy") || arr[1].contains("Animation"), "The movie's genre is: "+arr[1].split(" ")[0]);
        sAssert.assertAll();

        System.out.println("End Test Case: testCase02");
    }

    @Test(enabled = true, priority = 3)
    public void testCase03() throws InterruptedException {
        System.out.println("Start Test Case: testCase03");
        driver.get("https://www.youtube.com/");
        
        // Click on the menu button and wait until music link is visible
        WrapperMethods.wrap_guideMenu(driver, By.xpath("//div[@id='sections']/ytd-guide-section-renderer[3]/div//yt-formatted-string[text()='Music']/../.."));

        // Find music link and scroll to music link
        WebElement musicLink=WrapperMethods.wrap_findElement(driver, By.xpath("//div[@id='sections']/ytd-guide-section-renderer[3]/div//yt-formatted-string[text()='Music']/../.."));
        WrapperMethods.wrap_scrollToElement(driver, musicLink);

        // Click on the music link and wait until the next button is visible for the first section
        WrapperMethods.wrap_advancedClick(driver, musicLink, By.xpath("//div[@id='primary']/ytd-section-list-renderer[@page-subtype='channels']//ytd-item-section-renderer[1]//button[@aria-label='Next']"));

        // Get the name and the number of tracks of the last playlist from the first section
        String[] arr=WrapperMethods.wrap_tc03_getDetailsOfLastMusicAlbum(driver);

        // Print the name of the playlist
        WrapperMethods.wrap_printMessage("Title: "+arr[0]);

        // Soft assert for checking number of tracks <=50
        SoftAssert sAssert=new SoftAssert();
        sAssert.assertTrue(Integer.parseInt(arr[1].split(" ")[0]) <= 50, "Total number of tracks in the album: "+arr[1].split(" ")[0]);;
        sAssert.assertAll();

        System.out.println("End Test Case: testCase03");
    }

    @Test(enabled = true, priority = 4)
    public void testCase04() throws InterruptedException {
        System.out.println("Start Test Case: testCase04");
        driver.get("https://www.youtube.com/");

        // Click on the menu button and wait until news link is visible
        WrapperMethods.wrap_guideMenu(driver, By.xpath("//div[@id='sections']/ytd-guide-section-renderer[3]/div//yt-formatted-string[text()='News']/../.."));

        // Find news link and scroll to news link
        WebElement newsLink=WrapperMethods.wrap_findElement(driver, By.xpath("//div[@id='sections']/ytd-guide-section-renderer[3]/div//yt-formatted-string[text()='News']/../.."));
        WrapperMethods.wrap_scrollToElement(driver, newsLink);

        // Click on the news link and wait until the show more button is visible for the Latest news posts section
        WrapperMethods.wrap_advancedClick(driver, newsLink, By.xpath("//span[contains(text(), 'Latest')]/../../../../../..//button[@aria-label='Show more']"));

        // Get a list of 3 news from the Latest news posts section, print the headline and body of each news post and print sum total of likes for these news post
        WrapperMethods.wrap_tc04_getDetailsOfLatestNews(driver);

        System.out.println("End Test Case: testCase04");
    }

    @Test(enabled = true, dataProvider = "ytTestData", dataProviderClass = DataHandlingHelper.class, priority = 5)
    public void testCase05(String searchText) throws InterruptedException {
        System.out.println("Start Test Case: testCase05");
        driver.get("https://www.youtube.com/");
        
        // Get the search text from excel using data provider class, send the search text to search box and click on search icon
        // Wait for the search to complete
        WrapperMethods.wrap_searchOnYT(driver, searchText);

        // Get the sum of views of all result items until the total sum >= 10 crores and print the final sum of views
        WrapperMethods.wrap_tc05_getViewsUsingExcelData(driver);
        
        System.out.println("End Test Case: testCase05");
    }

    @AfterSuite
    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.quit();
    }

}