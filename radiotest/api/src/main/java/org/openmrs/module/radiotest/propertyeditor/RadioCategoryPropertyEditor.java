package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.api.RadioPatientService;

public class RadioCategoryPropertyEditor extends PropertyEditorSupport {
	
	public void setAsText(String text) throws IllegalArgumentException {
		Integer cid = Integer.valueOf(text);
		RadioCategory cat;
		if(cid == 0){
			cat = new RadioCategory();
			cat.setCategory("");
		} else {
			cat = Context.getService(RadioPatientService.class).getCategory(cid);
		}
		
		setValue(cat);
	}
	
}
