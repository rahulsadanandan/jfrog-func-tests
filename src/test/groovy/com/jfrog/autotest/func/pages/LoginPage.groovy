package com.jfrog.autotest.func.pages

import geb.Page

class LoginPage extends Page {
	
	static url = ""
	static content = {
		usernameTextbox{ $('#login-form input[name="user"]') }
		passwordTextbox{ $('#login-form input[name="password"]') }
		loginButton{ $('#login-form #login') }
		formErrorsDiv(wait: true) { $('div .jf-form-errors') }
	}

	static at = {
		 waitFor{ usernameTextbox.isDisplayed() }
	}

	def enterUsernameAndPassword(username,password){
		usernameTextbox.value(username)
		passwordTextbox.value(password)
	}
	
	def clickOnLoginButton() {
		loginButton.click()
	}
	
	def verifyErrorMessage() {
		assert formErrorsDiv.text().contains("Username or password is incorrect")
		true
	}
}