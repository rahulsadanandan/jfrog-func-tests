package com.jfrog.autotest.func.specs

import geb.spock.GebReportingSpec
import spock.lang.Shared
import com.jfrog.autotest.func.config.ConfigReader
import com.jfrog.autotest.func.pages.LoginPage
import com.jfrog.autotest.func.pages.HomePage


class LoginSpec extends GebReportingSpec {
	@Shared cfg

	def setupSpec() {
		cfg = ConfigReader.getConfiguration()
		reportHeader "<h2>Browser: ${driver.capabilities['browserName']}</h2>"
	}

	def cleanupSpec() {
	}

	def setup() {
		baseUrl = cfg.url
	}


	def "Login with a valid credential"(){
		given: "I opened the artifactory site"
		to LoginPage
		report("Artifactory Login Page")
		LoginPage loginPage = at LoginPage;

		when: "Enter valid Username & Password"
		loginPage.enterUsernameAndPassword(cfg.username, cfg.password)
		report("Artifactory login page with username and password")
		loginPage.clickOnLoginButton()

		then: "Should redirect to Homepage"
		report("Artifactory Home Page")
		at HomePage
	}

	def "Login with an invalid credential"(){
		given: "I opened the artifactory site"
		to LoginPage
		report("Artifactory Login Page")
		LoginPage loginPage = at LoginPage;

		when: "Enter valid Username & Password"
		loginPage.enterUsernameAndPassword(cfg.username, "invalid")
		report("Artifactory login page with username and password")
		loginPage.clickOnLoginButton()

		then: "Invalid Credentials error message should be shown"
		report("Login Page with error message")
		loginPage.verifyErrorMessage()
	}
}