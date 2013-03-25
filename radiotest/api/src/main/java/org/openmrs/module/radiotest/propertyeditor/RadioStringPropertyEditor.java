package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

public class RadioStringPropertyEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws IllegalArgumentException{
		if(text.equals("*")){
			setValue(null);
		} else {
			setValue(text);
		}
	}
	
	public String getAsText() {
		return (String) (getValue() == null? "" : getValue());
	}
}
