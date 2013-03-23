package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

public class RadioBooleanPropertyEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws IllegalArgumentException{
		if(text.isEmpty()){
			setValue(null);
		} else {
			setValue(Boolean.valueOf(text));
		}
	}
}
