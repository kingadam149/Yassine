import org.example.HomePage;
import org.example.Setup;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class HomeTest extends Setup {
    //go to Amazon.com
    //check 'Top Deal' box at the bottom of the screen or scroll down till it's visible
    //expected result: title text will exactly match 'Top Deal'
    //checking/validating the expected result // comparing actual & expexted result.

    HomePage home;
    @BeforeMethod
    void setUpTest(){
        setupDriver("https://amazon.com");
        home = PageFactory.initElements(driver, HomePage.class);
    }
    @AfterMethod
    void closeBrowser(){
        quitBrowser();
    }
    @Test
    void testDeals(){
        //BeforeMethod
        String actualText = home.dealsText();
        Assert.assertEquals(actualText,"Top Deal");
        //System.out.println(actualText);
    }

    @Test
    void testBasics(){
        home.clickAmazonBasics();
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl,"https://www.amazon.com/stores/node/20648519011?channel=discovbar?field-lbr_brands_browse-bin=AmazonBasics&ref_=nav_cs_amazonbasics");

    }

    @Test
    void testHeaderLinks(){
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Today's Deals");
        expected.add("Music");
        expected.add("Books");
        expected.add("Registry");

        ArrayList<String> actual = home.headerText();
        Assert.assertEquals(actual, expected);
    }

    @Test
    void validateAccount(){
        String actualText = home.accountText();
        Assert.assertEquals(actualText,"Account, Sign in");
    }
    @Test
    void validateSignIn(){
        home.loginPage();
        Assert.assertTrue(home.logInCheck());
    }

    @Test
    void testSignText(){
        home.loginPage();
        String actual = home.signInText();
        Assert.assertEquals(actual,"Sign in");
    }

}
