package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioExamType;
import org.openmrs.module.radiotest.api.RadioExamService;

public class RadioExamTypePropertyEditor extends PropertyEditorSupport{

	public void setAsText(String text) throws IllegalArgumentException {
		Integer tid = Integer.valueOf(text);
		RadioExamType type;
		if(tid == 0){
			type = new RadioExamType();
			type.setType("");
		} else {
			type = Context.getService(RadioExamService.class).getExamType(tid);
		}
		
		setValue(type);
	}
}
