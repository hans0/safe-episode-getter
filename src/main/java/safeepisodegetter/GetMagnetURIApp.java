package safeepisodegetter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// just getting code to work here
public class GetMagnetURIApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	/*
		System.setProperty("webdriver.gecko.driver","C:\\gecko\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		*/
		
		System.setProperty("webdriver.chrome.driver","C:\\gecko\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		String baseUrl = "https://1337x.to/";
        
        
        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);

       
        WebElement searchBox = driver.findElement(By.name("search"));
        
        //searchBox.sendKeys("star wars clone wars s07e12");
        searchBox.sendKeys("rick morty s04e06");

        searchBox.sendKeys(Keys.ENTER);
       
        //WebElement result = driver.findElement(By.tagName("td"));
        WebElement result = null;
        
        
        List<WebElement> anchors = driver.findElements(By.tagName("a"));
        Iterator<WebElement> i = anchors.iterator();

        List<String> torrentLinks = new ArrayList<String>(10);

        
        while(i.hasNext()) {
            WebElement anchor = i.next();
            if (anchor.getAttribute("href").toString().contains("1337x.to/torrent")) {
                torrentLinks.add(anchor.getAttribute("href"));
            	//System.out.println(anchor.getAttribute("href"));
                
            }
        }
        
        System.out.println(torrentLinks);
        
        List<String> magnetURIs = new ArrayList<String>(10);
        
        for (String s: torrentLinks) {
        	driver.get(s);
        	anchors = driver.findElements(By.tagName("a"));
        	i = anchors.iterator();
        	
        	while(i.hasNext()) {
        		WebElement anchor = i.next();
        		//System.out.println(anchor.toString());
                if (anchor.getAttribute("href").toString().contains("magnet")) {
                    magnetURIs.add(anchor.getAttribute("href"));
                	//System.out.println(anchor.getAttribute("href"));
                    
                }
        	}
        }
        
        /* GET MAGNET LINK 
        driver.get(torrentLinks.get(0));
        
        anchors = driver.findElements(By.tagName("a"));
        i = anchors.iterator();
        while(i.hasNext()) {
            WebElement anchor = i.next();
            System.out.println(anchor.toString());
            if (anchor.getAttribute("href").toString().contains("magnet")) {
                magnetURIs.add(anchor.getAttribute("href"));
            	//System.out.println(anchor.getAttribute("href"));
                
            }
        }
        */
        
        for (String s: magnetURIs) {
        	System.out.println(s);
        	
        }
        System.out.println(magnetURIs);
        
        /* GOING THROUGH ALL LINKS
        for (String s: torrentLinks) {
        	driver.get(s);
        	
        	WebElement magnetLink = driver.findElement(By.name("a"));
        	System.out.println(magnetLink.getAttribute("href"));
        	
        }
        */
        
        
        driver.close();
        /*
        try {
        	//result = driver.findElement(By.className("coll1"));
        	result = driver.findElement(By.className("table-list"))
        			.findElement(By.className("name"))
        			.findElement(By.tagName("a"));
            System.out.println(result.getText());

        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	driver.close();
        	
        }
        */
        
        
        
        /*
        List<WebElement> results = driver.findElements(By.className("td"));
        
        for (WebElement we: results) {
        	System.out.println(we);
        }
       */
        /*
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //close Fire fox
        driver.close();
        */
	}

}
