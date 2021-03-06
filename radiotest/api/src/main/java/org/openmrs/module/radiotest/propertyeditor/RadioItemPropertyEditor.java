package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.api.RadioInventoryService;

public class RadioItemPropertyEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws IllegalArgumentException {
		Integer iid = Integer.valueOf(text);
		setValue(Context.getService(RadioInventoryService.class).getItem(iid));
	}
	
}
