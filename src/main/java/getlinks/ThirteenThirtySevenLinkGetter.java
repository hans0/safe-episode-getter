package getlinks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ThirteenThirtySevenLinkGetter implements LinkGetter {


	private String destFile;
	private File outputFile;

	public String getDestFile() {
		return destFile;
	}

	public void setDestFile(String destFile) {
		this.destFile = destFile;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}


	
	public ThirteenThirtySevenLinkGetter() {
		this.destFile = "D:\\ScheduledDownloads";
	}
	
	public boolean getLinks(String query) throws IOException {
		String [] destFilename = query.split("\\s+");
		StringBuilder sb = new StringBuilder();
		for (String s: destFilename) {
			//query += s;
			sb.append(s);
		}
		// query = DateTimeFormatter.ofPattern("uuuuMMdd_HHmm").toString() + query;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		sb.insert(0, dtf.format(now) + "_");
		sb.append(".txt");
		System.out.println(sb.toString());
		
		
		System.setProperty("webdriver.chrome.driver","C:\\gecko\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		String baseUrl = "https://1337x.to/";
        
        
        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);

       
        WebElement searchBox = driver.findElement(By.name("search"));
        
        //searchBox.sendKeys("star wars clone wars s07e12");
        searchBox.sendKeys(query);

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
        
        this.destFile = "D:\\ScheduledDownloads\\" + sb.toString();
        this.outputFile = new File(destFile);
        FileWriter fw = null;
		
		fw = new FileWriter(outputFile);
		
        for (String s: magnetURIs) {
        	System.out.println(s);
        	if (s.contains("1080p")) {
				fw.write(s + '\n');
        	}
        }
        fw.close();

        System.out.println(magnetURIs);
      
        
        
        driver.close();
        
		
		return true;
	}
	
	
	
	public static void main(String [] args) {
		try {
			new ThirteenThirtySevenLinkGetter().getLinks("rick morty s04e08");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
