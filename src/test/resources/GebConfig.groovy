//This is the Geb configuration file.

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver

waiting {
 timeout = 5
}

environments {
 
 chrome {
	 driver = { new ChromeDriver() }
 }

 chromeHeadless {
	 driver = {
		 ChromeOptions o = new ChromeOptions()
		 o.addArguments('headless')
		 new ChromeDriver(o)
	 }
 }
 
 firefox {
	 atCheckWaiting = 1
	 driver = { new FirefoxDriver() }
 }

}

// To run the tests with all browsers just run “./gradlew test”