package nopcommerceautomation;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class FeatureProducts extends Utils {
    SoftAssert softAssert = new SoftAssert();
    // LoadProps loadProps = new LoadProps();

    @BeforeMethod
    public static void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\Resource\\BrowserDriver\\chromedriver.exe");
        openchromeBrowser();
        driver.get("https://demo.nopcommerce.com/");
    }

    @AfterMethod
    public void quit() {
        //  driver.quit();
    }

    @Test
    public void UserShouldAbleToAddtocomparelist() {
        int count = 0, count1 = 0;
        //click on first product on Add to compare list
        driver.findElement(By.xpath("//input[@onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/1\"),!1']")).click();
        //checking the link of compared product list
        //WebDriverWait wait1 = new WebDriverWait(driver, 10);
        softAssert.assertTrue(driver.findElement(By.linkText("product comparison")).isDisplayed());
       // driver.findElement(By.xpath("//input[@onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/4\"),!1']")).click();
        //click on second product on add to compare list
        driver.findElement(By.xpath("//input[@onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/18\"),!1']")).click();
        //checking the compare
        softAssert.assertTrue(driver.findElement(By.linkText("product comparison")).isDisplayed());
        // driver.findElement(By.linkText("product comparison")).click();
        //waitForClickable(By.xpath("//input[@onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/18\"),!1']"), 10);
        // waitForClickable(By.linkText("Compare products list"), 20);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.linkText("Compare products list")).click();

        List<WebElement> compareproduct = driver.findElements(By.xpath("//tr[@class=\"product-name\"]//td[@style]"));
        //compareproduct.size();
        System.out.println(compareproduct.size());
        for (WebElement e : compareproduct) {
            if (e.getText().contains("Build your own computer")) {
                System.out.println("Build your own computer");
                count++;
            } else if (e.getText().contains("HTC One M8 Android L 5.0 Lollipop")) {
                System.out.println("HTC One M8 Android L 5.0 Lollipop");
                count++;
            } else {
                count1++;
            }

        }
        System.out.println(count);
        System.out.println(count1);

        softAssert.assertEquals(compareproduct.size(), count);
        driver.findElement(By.xpath("//a[text()='Clear list']")).click();
        String expectedClearList = "You have no items to compare.";
        String actualClearList = driver.findElement(By.className("no-data")).getText();
        softAssert.assertAll();

    }
        // comparing the right product
        //  String expectedproduct1 = "HTC One M8 Android L 5.0 Lollipop";
        //  String actualproduct1 = driver.findElement(By.linkText("HTC One M8 Android L 5.0 Lollipop")).getText();
        //softAssert.assertEquals(expectedproduct1, actualproduct1);

        //  String expectedproduct2 = "Build your own computer";
        //String actualproduct2 = driver.findElement(By.linkText("Build your own computer")).getText();
        //softAssert.assertEquals(expectedproduct2, actualproduct2);

       // softAssert.assertEquals(expectedClearList, actualClearList);



    @Test
    public void UserShouldAbleToAddNewComments() {
        //
        driver.findElement(By.xpath("//a[contains(@href,\"/new-online-store-is-open\")and contains(@class,\"read-more\")]")).click();
        driver.findElement(By.cssSelector("input.enter-comment-title")).sendKeys("commercial");
        driver.findElement(By.cssSelector("textarea#AddNewComment_CommentText")).sendKeys("aaaaasssssdddd");
        driver.findElement(By.name("add-comment")).click();
         softAssert.assertTrue(driver.findElement(By.xpath("//div[text()='News comment is successfully added.']")).isDisplayed());
       //use of list
        List<WebElement> commentList=driver.findElements(By.xpath("//div[@class='comment news-comment']"));
        System.out.println("total comments "+commentList.size());
            WebElement lastelement=commentList.get(commentList.size()-1);
            String lastelementText=(lastelement.getText());
            System.out.println(lastelementText);
            if(lastelement.getAttribute("outerHTML").contains("comment-time"))
            {
                System.out.println("last elemment details");
            }
            softAssert.assertTrue(lastelement.getAttribute("outerHTML").contains("comment-time"));
            softAssert.assertAll();

        }


    @Test
    public void UserShouldAbleToSearchTheProduct() { //enter the keyword work in search box
        int count = 0, count1 = 0;
        driver.findElement(By.id("small-searchterms")).sendKeys("Nike");
        //click on search button
        driver.findElement(By.className("search-box-button")).click();
        List<WebElement> producttitle = driver.findElements(By.xpath("//div[@class=\"item-grid\"] //h2"));
        System.out.println(producttitle.size());


        for (WebElement e : producttitle) {
            if (e.getText().contains("Nike")) {
                // System.out.println(e.getText());
                String product = e.getText();
                System.out.println("Products on the page are " + product);
                count++;
            } else {
                System.out.println("Items not found");
                count1++;
            }

        }
        if (count == producttitle.size()) {

            //didnt know what to keep in assert.
           //softAssert.assertTrue(  true );
           softAssert.assertAll();
        }
    }
}