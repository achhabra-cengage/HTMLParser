import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Seleni {

    public static void main(String[] args) {
        // The name of the file to open.
        String fileName = "/Users/aayush/Desktop/Learn/HTMLParser/dataFile.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                new BufferedReader(fileReader);
            int a = 0;
            
            while ((line = bufferedReader.readLine()) != null) {

                Seleni s = new Seleni();
                if (line.trim().isEmpty())
                	continue;
                s.functionJob(line);


            }
            
            
            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" +
                fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                "Error reading file '" + fileName + "'");
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        System.out.println("Done!!");
    }

    public void functionJob(String line) {
        //HtmlUnitDriver newBrowser = new HtmlUnitDriver();
        FirefoxDriver newBrowser = new FirefoxDriver();
        newBrowser.get("https://translate.google.com/");

        WebElement textArea = newBrowser.findElement(By.id("source"));
        textArea.sendKeys(line);

        WebElement translateToSpanish = newBrowser.findElement(By.xpath(".//*[@id='gt-tl-sugg']/div[2]"));
        translateToSpanish.click();


        WebElement resultBox = newBrowser.findElement(By.xpath(".//*[@id='result_box']"));

        String stringTranslated = resultBox.getText();
        System.out.println(stringTranslated);

        try{

            FileWriter fstream = new FileWriter("/Users/aayush/Desktop/Learn/HTMLParser/x.txt",true);
            BufferedWriter fbw = new BufferedWriter(fstream);
            String stringToAddToTheFile = "\t'" + line + "' : '" + stringTranslated +"',";
            fbw.write(stringToAddToTheFile);
            fbw.newLine();
            fbw.close();
            
            
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        newBrowser.quit();
    }

}