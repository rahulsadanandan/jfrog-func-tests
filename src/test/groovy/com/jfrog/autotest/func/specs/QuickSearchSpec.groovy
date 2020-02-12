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

	def "Perform a good quick search"(){
		given: "I opened the artifactory site"
		to LoginPage
		report("Artifactory Login Page")
		LoginPage loginPage = at LoginPage;

		when: "Enter valid Username & Password and submit"
		loginPage.enterUsernameAndPassword(cfg.username, cfg.password)
		report("Artifactory login page with username and password")
		loginPage.clickOnLoginButton()

		then: "The user redirected to Homepage"
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

	def "Perform a bad quick search"(){
		given: "I opened the artifactory site"
		to LoginPage
		report("Artifactory Login Page")
		LoginPage loginPage = at LoginPage;

		when: "Enter valid Username & Password and submit"
		loginPage.enterUsernameAndPassword(cfg.username, cfg.password)
		report("Artifactory login page with username and password")
		loginPage.clickOnLoginButton()

		then: "The user redirected to Homepage"
		report("Artifactory Home Page")
		HomePage homePage = at HomePage

		when: "Enter a good search term in quick search and submit"
		homePage.quickSearchModule.enterQuickSearchTerm(badSearchTerm)
		homePage.quickSearchModule.clickQuickSearchButton()

		then: "Should be redirected to search results page with no results"
		report("Artifactory Search Page")
		SearchPage searchPage = at SearchPage
		searchPage.verifyNoArtifactFoundToastMessage()
		searchPage.verifyBadSearchResults()

		where:
		badSearchTerm      | _
		"asd asd"          | _
		"sdfsdf"           | _
		"hjhj"             | _
	}
}