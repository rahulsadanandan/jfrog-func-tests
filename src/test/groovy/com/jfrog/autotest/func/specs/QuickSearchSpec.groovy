package com.jfrog.autotest.func.specs

import geb.spock.GebReportingSpec
import spock.lang.Shared
import com.jfrog.autotest.func.config.ConfigReader
import com.jfrog.autotest.func.pages.LoginPage
import com.jfrog.autotest.func.pages.HomePage
import com.jfrog.autotest.func.pages.SearchPage

class QuickSearchSpec extends GebReportingSpec{

	@Shared cfg
	
	def setupSpec() {
		cfg = ConfigReader.getConfiguration()
		reportHeader "<h2>Browser: ${driver.capabilities['browserName']}</h2>"
	}

	def setup() {
		baseUrl= cfg.url
	}

	def "Perform some good quick searches"(){
		given: "I opened the artifactory site"
		to LoginPage
		report("Artifactory Login Page")
		LoginPage loginPage = at LoginPage;

		when: "Enter valid Username & Password and submit"
		loginPage.enterUsernameAndPassword(cfg.username, cfg.password)
		loginPage.clickOnLoginButton()

		then: "The user should redirect to Homepage"
		report("Artifactory Home Page")
		HomePage homePage = at HomePage

		when: "Enter a good search term in quick search and submit"
		homePage.quickSearchModule.enterQuickSearchTerm(goodSearchTerm)
		homePage.quickSearchModule.clickQuickSearchButton()

		then: "Should be redirected to search results page with good results"
		report("Artifactory Search Page")
		SearchPage searchPage = at SearchPage
		searchPage.verifyGoodSearchResults()

		where:
		goodSearchTerm| _
		"fi"    | _
		"fil"   | _
		"file"  | _
	}

	def "Perform some bad quick searches"(){
		given: "I opened the artifactory site"
		to LoginPage
		report("Artifactory Login Page")
		LoginPage loginPage = at LoginPage;

		when: "Enter valid Username & Password and submit"
		loginPage.enterUsernameAndPassword(cfg.username, cfg.password)
		loginPage.clickOnLoginButton()

		then: "The user should redirect to Homepage"
		report("Artifactory Home Page")
		HomePage homePage = at HomePage

		when: "Enter a bad search term in quick search and submit"
		homePage.quickSearchModule.enterQuickSearchTerm(badSearchTerm)
		homePage.quickSearchModule.clickQuickSearchButton()

		then: "Should show error message in toast and redirect to search results page with no results"
		report("Artifactory Search Page")
		SearchPage searchPage = at SearchPage
		searchPage.verifyToastMessage(errorMessage)
		searchPage.verifyBadSearchResults()

		where:
		badSearchTerm      | errorMessage
		"123"              | "No artifacts found"
		",.."              | "No artifacts found"
		"***"              | "Search term empty or containing only wildcards is not permitted"
	}
}