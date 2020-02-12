package com.jfrog.autotest.func.pages

import geb.Page


class SearchPage extends Page{
	
	static url = ""
	static content = {
		searchPanelSelector{ $('.search-panel-selector') }
		searchResultsItems(required: false,wait: true){ $('div[ng-if="row.entity.downloadLink"]') }
		toastMessageDiv(wait: true) { $('.toast-message div') }
	}

	static at = {
		waitFor{ searchPanelSelector.isDisplayed() }
	}
	
	def verifyGoodSearchResults() {
		assert searchResultsItems.size() >=1
		true
	}
	
	def verifyBadSearchResults() {
		assert searchResultsItems.size() ==0
		true
	}
	
	def verifyToastMessage(message) {
		assert toastMessageDiv.text().contains(message)
		true
	}
	
}