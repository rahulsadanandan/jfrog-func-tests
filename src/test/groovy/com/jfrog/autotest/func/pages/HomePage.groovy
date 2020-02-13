package com.jfrog.autotest.func.pages

import geb.Page
import com.jfrog.autotest.func.modules.QuickSearchModule

class HomePage extends Page {
	
	static url = ""
	static content = {
		quickSearchTextbox{ $('#quick') }
		quickSearchModule { module QuickSearchModule }
	}

	static at = {
		
		waitFor{ quickSearchTextbox.isDisplayed() }
		
	}


}