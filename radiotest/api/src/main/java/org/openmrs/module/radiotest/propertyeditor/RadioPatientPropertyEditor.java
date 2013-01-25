package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.api.RadioPatientService;

public class RadioPatientPropertyEditor extends PropertyEditorSupport {
	
	public void setAsText(String text) throws IllegalArgumentException {
		Integer pid = Integer.valueOf(text);
		setValue(Context.getService(RadioPatientService.class).getPatient(pid));
	}

}
