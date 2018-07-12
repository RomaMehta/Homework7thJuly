package mavenProject02;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


import static mavenProject02.Utils.*;

public class TestSuite extends BaseTest {

    // to call the LoadProp class, and to create loadProp object - so we can use it to import test data from the config file.
    LoadProp loadProp = new LoadProp();


    @Test (priority = 3)
    public void verifyUserIsAbleToCompleteTheJewelleryOrderSuccessfully(){

        // to click on jewelery in the categories bar
        clickonElement(By.xpath("//*[@class ='header-menu']/ul/li[6]/a"));
        // to click on the Flower Girl Bracelet
        clickonElement(By.xpath("//*[@class='item-grid']/div[2]/div/div[2]/div[3]/div[2]/input[1]"));
        //to wait for the green flash bar to disappear -
        waitForElementToBeInvisible(By.linkText("shopping cart"),10);
        // to click on the vintage engagement ring
        clickonElement(By.xpath("//*[@class='item-grid']/div[3]/div/div[2]/div[3]/div[2]/input[1]"));
        // to wait fo the green flash bar to appear
        waitForTheElementToBeVisible(By.linkText("shopping cart"),10);
        //clickonElement(By.partialLinkText("shopping cart"));
        // to click on the shopping cart link on the flash bar
        clickonElement(By.linkText("shopping cart"));
        //tochange the quantity
        clearEnterText(By.xpath("//*[@class='quantity']/input[1]"),"2");
        // to click on the update cart button
        clickonElement(By.name("updatecart"));
        // to click on checkout button
        clickonElement(By.id("checkout"));

        // to verify the pop-up window od terms of service
        String expectedMessage = "Please accept the terms of service before the next step.";
        Assert.assertEquals(toGetText(By.id("terms-of-service-warning-box")),expectedMessage);

        // to click on the cross for terms of service
       clickonElement(By.xpath("//div[7]/div[1]/button"));
       // to click on the tick box
       clickonElement(By.id("termsofservice"));
       // to click on the checkout button
       clickonElement(By.id("checkout"));
       // to click on checkout as guest button
       clickonElement(By.xpath("//*[@class='page-body']/div/div/div[3]/input[1]"));


       // to fil the details in the form - using the test data from the test data file - usin the loadProp object
       toEnterText(By.id("BillingNewAddress_FirstName"),loadProp.getProperty("Firstname"));
       toEnterText(By.id("BillingNewAddress_LastName"),loadProp.getProperty("Lastname"));
       toEnterText(By.id("BillingNewAddress_Email"),loadProp.getProperty("Email"));
       toEnterText(By.id("BillingNewAddress_Company"),loadProp.getProperty("Company"));
       selectByVisibletext(By.id("BillingNewAddress_CountryId"),loadProp.getProperty("Country"));
       waitForElementToLoad(By.id("BillingNewAddress_StateProvinceId"),10);
       selectByVisibletext(By.id("BillingNewAddress_StateProvinceId"),loadProp.getProperty("State"));
       toEnterText(By.id("BillingNewAddress_City"),loadProp.getProperty("City"));
       toEnterText(By.id("BillingNewAddress_Address1"),loadProp.getProperty("Address1"));
       toEnterText(By.id("BillingNewAddress_Address2"),loadProp.getProperty("Address2"));
       toEnterText(By.id("BillingNewAddress_ZipPostalCode"),loadProp.getProperty("PostalCode"));
       toEnterText(By.id("BillingNewAddress_PhoneNumber"),loadProp.getProperty("Phonenumber"));
       toEnterText(By.id("BillingNewAddress_FaxNumber"),loadProp.getProperty("Faxnumber"));
       clickonElement(By.xpath("//*[@id='billing-buttons-container']/input[1]"));

       // to click on the middle delivery option
       clickonElement(By.id("shippingoption_1"));
       clickonElement(By.xpath("//*[@id='shipping-method-buttons-container']/input[1]"));

       // to select the payment method - credit card
       clickonElement(By.id("paymentmethod_1"));
       clickonElement(By.xpath("//*[@id='payment-method-buttons-container']/input[1]"));

       // to fill the details for the card payment - using data from the test data file
        // fake card generated using the fake card generator
       selectByVisibletext(By.id("CreditCardType"),"Visa");
       toEnterText(By.id("CardholderName"),loadProp.getProperty("CardHolderName"));
       toEnterText(By.id("CardNumber"),loadProp.getProperty("CreditCardno"));
       selectByIndex(By.id("ExpireMonth"),8);
       selectByValue(By.id("ExpireYear"),"2019");
       toEnterText(By.id("CardCode"),loadProp.getProperty("CardCode"));
       clickonElement(By.xpath("//*[@id='payment-info-buttons-container']/input[1]"));
       // need to wait for the button ot be enabled - it takes few seconds to be activated
       waitForElementToBeClickable(By.xpath("//*[@id='confirm-order-buttons-container']/input"),10);
       clickonElement(By.xpath("//*[@id='confirm-order-buttons-container']/input"));


       // to verify the message on sucessful completion
       String expectedText = "Your order has been successfully processed!";
       Assert.assertEquals(toGetText(By.xpath("//*[@class='master-column-wrapper']/div/div/div[2]/div/div")),expectedText);

       //Assert Continue button - using the isDisplayed in built method
        Boolean continueButton = driver.findElement(By.xpath("//*[@class='master-column-wrapper']/div/div/div[2]/div/div[3]/input")).isDisplayed();
        Assert.assertTrue(continueButton);
        System.out.println(continueButton);

        // to verify the url - url box does not have a locator
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://demo.nopcommerce.com/checkout/completed/" );
        System.out.println(URL);
       }

       @Test (priority = 2)
    public void verifyThatUserIsAbleToChangeCurrency(){
        // to select Euro
        selectByVisibletext(By.id("customerCurrency"),"Euro");
        // to wait for the page to load with the currency changes
        waitForTheElementToBeVisible(By.xpath("//*[@class='add-info']/div/span"),10);
        // to verify the currency change
        String expectedCurrency = "€1140.00";
        Assert.assertEquals(toGetText(By.xpath("//*[@class='add-info']/div/span")),expectedCurrency);
        // to print the value
           System.out.println(toGetText(By.xpath("//*[@class='add-info']/div/span")));
       }

       @Test (priority = 1)
    public void verifyThatNotRegisteredUserIsUnableTOUseEmailAFriendFeature(){
        // to click on the gift card
        clickonElement(By.linkText("$25 Virtual Gift Card"));
        // to fill the details using test data file
        toEnterText(By.id("giftcard_43_RecipientName"),loadProp.getProperty("RecipientsName"));
        toEnterText(By.id("giftcard_43_RecipientEmail"),loadProp.getProperty("RecipientsEmail"));
        toEnterText(By.id("giftcard_43_SenderName"),loadProp.getProperty("SendersName"));
        toEnterText(By.id("giftcard_43_SenderEmail"),loadProp.getProperty("SendersEmail"));
        toEnterText(By.id("giftcard_43_Message"),loadProp.getProperty("Message"));
        // to click on email a friend
        clickonElement(By.xpath("//*[@id=\"product-details-form\"]/div/div[1]/div[2]/div[8]/div[3]/input"));
        // to fill the details from the test data file
        toEnterText(By.id("FriendEmail"),loadProp.getProperty("RecipientsEmail"));
        toEnterText(By.id("YourEmailAddress"),loadProp.getProperty("SendersEmail"));
        toEnterText(By.id("PersonalMessage"),loadProp.getProperty("Message"));
        // to click on send email button
        clickonElement(By.xpath("//*[@method='post']/div[2]/input[1]"));

        // to verify that user is unable to send the email
        String expectedMessage = "Only registered customers can use email a friend feature";
        Assert.assertEquals(toGetText(By.xpath("//*[@method='post']/div/ul/li")),expectedMessage);



       }
}
