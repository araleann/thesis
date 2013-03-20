package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

public class RadioStringPropertyEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws IllegalArgumentException{
		if(text.isEmpty())
			setValue(null);
		else if(text.equals(" "))
			setValue("");
		else
			setValue(text);
	}
	
	public String getAsText() {
		return (String) (getValue() == null? "" : getValue());
	}
}
