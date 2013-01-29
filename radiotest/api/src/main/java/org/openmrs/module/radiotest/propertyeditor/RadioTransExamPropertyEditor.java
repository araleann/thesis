package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.api.RadioTransactionService;

public class RadioTransExamPropertyEditor extends PropertyEditorSupport {
	
	public void setAsText(String text) throws IllegalArgumentException {
		Integer id = Integer.valueOf(text);
		setValue(Context.getService(RadioTransactionService.class).getTransExam(id));
	}

}
