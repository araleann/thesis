/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.radiotest.extension.html;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

/**
 * This class defines the links that will appear on the administration page under the
 * "radiotest.title" heading. 
 */
public class AdminList extends AdministrationSectionExt {
	
	/**
	 * @see AdministrationSectionExt#getMediaType()
	 */
	public Extension.MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;
	}
	
	/**
	 * @see AdministrationSectionExt#getTitle()
	 */
	public String getTitle() {
		return "radiotest.title";
	}
	
	/**
	 * @see AdministrationSectionExt#getLinks()
	 */
	public Map<String, String> getLinks() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("/module/radiotest/manage.form", "radiotest.manage");
		map.put("/module/radiotest/categoryForm.form", "Manage Patient Categories");
		map.put("/module/radiotest/examTypeForm.form", "Manage Exam Types");
		map.put("/module/radiotest/noteTypeForm.form", "Manage Note Types");
		map.put("/module/radiotest/feeTypeForm.form", "Manage Fee Types");
		map.put("/module/radiotest/examForm.form", "Manage Exams");
		map.put("/module/radiotest/itemTypeForm.form", "Manage Item Types");
		map.put("/module/radiotest/itemForm.form", "Manage Items");
		map.put("/module/radiotest/stock.form", "Add Stock");
		
		
		map.put("/module/radiotest/searchPatientForm.form", "Search Patient Form");
		map.put("/module/radiotest/transExamForm.form", "Transaction Form");
		map.put("/module/radiotest/transactions.htm", "Transactions Page");
		map.put("/module/radiotest/results.htm", "Results Page");
		map.put("/module/radiotest/home.htm", "Home");
		map.put("/module/radiotest/inventory.htm", "Inventory");
		map.put("/module/radiotest/listings.htm", "Stock Listings");
		map.put("/module/radiotest/report.htm", "Reports");
		
		return map;
	}
	
}