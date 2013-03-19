package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.api.RadioPatientService;

public class RadioCategoryPropertyEditor extends PropertyEditorSupport {
	
	public void setAsText(String text) throws IllegalArgumentException {
		Integer cid = Integer.valueOf(text);
		RadioCategory category = Context.getService(RadioPatientService.class).getCategory(cid);
		setValue(category == null? new RadioCategory() : category);
	}
	
}
