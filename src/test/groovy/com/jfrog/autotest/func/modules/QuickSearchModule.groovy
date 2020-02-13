package com.jfrog.autotest.func.modules

import geb.Module

class QuickSearchModule extends Module {
	
	static content = {
		quickSearchTextbox{ $('#quick') }
		quickSearchButton{ $('.quick-search .icon-search') }
	}
	
	
	def enterQuickSearchTerm(searchTerm) {
		quickSearchTextbox.value(searchTerm)
	}
	
	def clickQuickSearchButton() {
		quickSearchButton.click()
	}
	
	
}