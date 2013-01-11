package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.api.RadioTestService;

public class RadioExamPropertyEditor extends PropertyEditorSupport{

	public void setAsText(String text) throws IllegalArgumentException {
		Integer eid = Integer.valueOf(text);
		setValue(Context.getService(RadioTestService.class).getExam(eid));
	}
}
