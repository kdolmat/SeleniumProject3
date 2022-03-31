import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Selenium3 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Inna\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://carfax.com");
        driver.manage().window().maximize();


        driver.findElement(By.xpath("//*[.='Find a Used Car']")).click();
        String title = driver.getTitle();
        if (title.contains("Used Cars")){
            System.out.println("contains used cars");
        }
        Thread.sleep(2000);
        new Select(driver.findElement(By.xpath("//select[@name='make']"))).selectByValue("Tesla");
        List<String> expected = Arrays.asList("Model 3", "Model S","Model X","Model Y");
        List<WebElement> list = new Select(driver.findElement(By.xpath("//select[@class='form-control search-model  search-model--lp']"))).getOptions();
        List<String> models = new ArrayList<>();


        for (WebElement l:list) {
            models.add(l.getText());
        }
        boolean contains = false;
        for(String m : models) {

            for(String exp : expected) {
                if(m.contains(exp)) {
                    contains=true;
                }
            }
        }
        if(contains){
            System.out.println("contains models");
        }


        new Select(driver.findElement(By.xpath("//select[@class='form-control search-model  search-model--lp']"))).selectByValue("Model S");

        driver.findElement(By.xpath("//input[@name='zip']")).clear();
        driver.findElement(By.xpath("//input[@name='zip']")).sendKeys("22182");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        List<WebElement> checkBoxes = driver.findElements(By.xpath("//span[@class='checkbox-list-item--fancyCbx']"));

        for (int i=0;i<checkBoxes.size();i++){
            driver.findElement(By.xpath("//span[@class='checkbox-list-item--fancyCbx']")).click();
        }
        Thread.sleep(3000);
        //I put it here because it gets stuck on the “Show me x Results” button after checking all boxes

        String count = driver.findElement(By.xpath("//span[@class='totalRecordsText']")).getText();

        driver.findElement(By.xpath("//span[@class='totalRecordsText']")).click();
       // Integer ct = Integer.parseInt(count);



        List<WebElement> optionsOfCars = driver.findElements(By.xpath("//div[@class='listing-header']"));
       // List<WebElement> options1 = driver.findElements(By.xpath("//h4[@class='srp-list-item-basic-info-model']"));
        List<String> opt = new ArrayList<>();
        List<String> exp = Arrays.asList(" Tesla Model S ");


        Assert.assertEquals(count,""+optionsOfCars.size());
      //  sometimes gives me an error that it contains 25 elements, but for the most time, doesn't give an error. is that because of the carfax website issue?

        for (WebElement o:optionsOfCars) {
            opt.add(o.getText());
        }



        contains = false;
        for(String o : opt) {
            for(String e : exp) {
                if(o.contains(e)) {
                    contains=true;
                }
            }
        }
        if(contains){
            System.out.println("contains teslas1");
        }





        List<WebElement> prices = driver.findElements(By.xpath("//span[@class='srp-list-item-price' and @class!='price-text']"));
        //couldn't figure out how to remove the word "price"
        List<String> pr = new ArrayList<>();
        for (WebElement p:prices) {
            pr.add(p.getText());
            if(pr.contains("Call for price")){
                pr.remove(p);
            }

        }
        System.out.println(pr);


        new Select(driver.findElement(By.xpath("//select[@aria-label='SelectSort']"))).selectByValue("PRICE_DESC");
       // System.out.println((driver.findElement(By.xpath("//select[@aria-label='SelectSort']")).getText()));
        if((driver.findElement(By.xpath("//select[@aria-label='SelectSort']")).getText()).contains("Price - High to Low")){
            System.out.println("Price - High to Low correct");
        }

        new Select(driver.findElement(By.xpath("//select[@aria-label='SelectSort']"))).selectByValue("MILEAGE_ASC");
        if((driver.findElement(By.xpath("//select[@aria-label='SelectSort']")).getText()).contains("Mileage - Low to High")){
            System.out.println("Mileage - Low to High correct");
        }

        new Select(driver.findElement(By.xpath("//select[@aria-label='SelectSort']"))).selectByValue("YEAR_DESC");
        if((driver.findElement(By.xpath("//select[@aria-label='SelectSort']")).getText()).contains("Year - New to Old")){
            System.out.println("Year - New to Old correct");
        }
//the verification process is the same regardless of value we're searching for and whether the option is changed, the source code stays the same
// is that how it was supposed to be done?






    }
}
