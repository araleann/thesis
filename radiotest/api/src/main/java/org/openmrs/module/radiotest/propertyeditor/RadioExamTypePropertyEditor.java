package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.api.RadioTestService;

public class RadioExamTypePropertyEditor extends PropertyEditorSupport{

	public void setAsText(String text) throws IllegalArgumentException {
		Integer tid = Integer.valueOf(text);
		setValue(Context.getService(RadioTestService.class).getExamType(tid));
	}
}
