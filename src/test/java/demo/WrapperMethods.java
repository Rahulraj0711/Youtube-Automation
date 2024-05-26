package demo;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WrapperMethods {

    public static String[] wrap_tc02_getDetailsOfLastMovie(WebDriver driver) throws InterruptedException {
        WebElement nextButton=wrap_findElement(driver, By.xpath("//span[text()='Top selling']/../../../../../../../..//div[@id='contents']//button[@aria-label='Next']"));
        while(nextButton.isDisplayed()) {
            wrap_clickElement(nextButton);
            wrap_wait(2000);
        }
        List<WebElement> moviesList=WrapperMethods.wrap_findElements(driver, By.xpath("//span[text()='Top selling']/../../../../../../../..//div[@id='contents']//ytd-grid-movie-renderer"));
        int last=moviesList.size()-1;
        String rating=wrap_getTextFromElement(moviesList.get(last).findElement(By.xpath("ytd-badge-supported-renderer/div[2]/p")));
        String genre=wrap_getTextFromElement(moviesList.get(last).findElement(By.xpath("a/span")));
        String[] arr={rating, genre};
        return arr;
    }

    public static String[] wrap_tc03_getDetailsOfLastMusicAlbum(WebDriver driver) throws InterruptedException {
        WebElement nextButton=wrap_findElement(driver, By.xpath("//div[@id='primary']/ytd-section-list-renderer[@page-subtype='channels']//ytd-item-section-renderer[1]//button[@aria-label='Next']"));
        while(nextButton.isDisplayed()) {
            wrap_clickElement(nextButton);
            wrap_wait(2000);
        }
        List<WebElement> musicAlbumsList=WrapperMethods.wrap_findElements(driver, By.xpath("//div[@id='primary']/ytd-section-list-renderer[@page-subtype='channels']//ytd-item-section-renderer[1]//ytd-compact-station-renderer"));
        int last=musicAlbumsList.size()-1;
        String playlistName=wrap_getTextFromElement(musicAlbumsList.get(last).findElement(By.xpath("div//h3")));
        String tracksCount=wrap_getTextFromElement(musicAlbumsList.get(last).findElement(By.xpath("div//p[@id='video-count-text']")));
        String[] arr={playlistName, tracksCount};
        return arr;
    }

    public static void wrap_tc04_getDetailsOfLatestNews(WebDriver driver) throws InterruptedException {
        // WebElement showMoreButton=driver.findElement(By.xpath("//span[contains(text(), 'Latest')]/../../../../../..//button[@aria-label='Show more']"));
        // wrap_scrollToElement(driver, showMoreButton);
        // JavascriptExecutor js=(JavascriptExecutor) driver;
        // js.executeScript("arguments[0].click();", showMoreButton);
        List<WebElement> latestNewsList=WrapperMethods.wrap_findElements(driver, By.xpath("//span[contains(text(), 'Latest')]/../../../../../..//ytd-rich-item-renderer"))
            .stream()
            .limit(3)
            .collect(Collectors.toList());
        int totalLikes=0;
        for(int i=0;i<latestNewsList.size();i++) {
            String title=wrap_getTextFromElement(latestNewsList.get(i).findElement(By.xpath("div//a[@id='author-text']//span")));
            String body=wrap_getTextFromElement(latestNewsList.get(i).findElement(By.xpath("div//yt-formatted-string[@id='home-content-text']/span")));
            String likes=wrap_getTextFromElement(latestNewsList.get(i).findElement(By.xpath("div//div[@id='toolbar']/span[@id='vote-count-middle']")));
            totalLikes+=Integer.parseInt(likes);
            wrap_printMessage((i+1)+". "+title+"\n "+body);
        }
        wrap_printMessage("Total number of likes: "+totalLikes);
    }
    
    public static void wrap_tc05_getViewsUsingExcelData(WebDriver driver) {
        long expectedViews=100000000;
        long currentViews=0;
        int x=1;
        while(currentViews<expectedViews) {
            try {
                WebElement viewElement=driver.findElement(By.xpath("(//span[contains(text(), 'views')])["+x+"]"));
                wrap_scrollToElement(driver, viewElement);
                // wrap_wait(3000);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                String viewsString=wrap_getTextFromElement(viewElement).split(" ")[0];
                
                long views;
                if(viewsString.contains("K")) {
                    viewsString=viewsString.replaceAll("[^0-9]", "");
                    views=Long.parseLong(viewsString)*1000;
                }
                else if(viewsString.contains("M")) {
                    viewsString=viewsString.replaceAll("[^0-9]", "");
                    views=Long.parseLong(viewsString)*1000000; 
                }
                else if(viewsString.contains("B")) {
                    viewsString=viewsString.replaceAll("[^0-9]", "");
                    views=Long.parseLong(viewsString)*1000000000;
                }
                else {
                    views=Long.parseLong(viewsString);
                }
                System.out.print(views+" + ");
                currentViews+=views;
                x++;
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
        System.out.println();
        wrap_printMessage("Final sum of views: "+currentViews);
    }

    public static void wrap_searchOnYT(WebDriver driver, String searchText) throws InterruptedException {
        WebElement searchBox=wrap_findElement(driver, By.cssSelector("input#search"));
        searchBox.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        searchBox.sendKeys(searchText);
        wrap_wait(2000);
        WebElement searchIcon=wrap_findElement(driver, By.cssSelector("button#search-icon-legacy"));
        searchIcon.click();
        wrap_wait(5000);
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public static void wrap_guideMenu(WebDriver driver, By waitBy) throws InterruptedException {
        WebElement menuButton=wrap_findElement(driver, By.xpath("//button[@aria-label='Guide']"));
        wrap_clickElement(menuButton);
        wrap_advancedWait(driver, waitBy);
    }

    public static void wrap_scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
    
    public static void wrap_advancedClick(WebDriver driver, WebElement element, By waitBy) throws InterruptedException {
        wrap_clickElement(element);
        wrap_advancedWait(driver, waitBy);
    }

    public static WebElement wrap_findElement(WebDriver driver, By by) {
        WebElement element=driver.findElement(by);
        return element;
    }

    public static List<WebElement> wrap_findElements(WebDriver driver, By by) {
        List<WebElement> elementsList=driver.findElements(by);
        return elementsList;
    }

    public static void wrap_clickElement(WebElement element) {
        element.click();
    }

    public static void wrap_wait(long time) throws InterruptedException {
        Thread.sleep(time);
    }

    public static void wrap_advancedWait(WebDriver driver, By by) throws InterruptedException {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void wrap_printMessage(String message) {
        System.out.println(message);
    }

    public static String wrap_getTextFromElement(WebElement element) {
        return element.getText().trim();
    }

}
