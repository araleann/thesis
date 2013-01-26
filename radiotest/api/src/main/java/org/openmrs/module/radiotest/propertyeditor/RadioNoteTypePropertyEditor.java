package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioNoteType;
import org.openmrs.module.radiotest.api.RadioTransactionService;

public class RadioNoteTypePropertyEditor extends PropertyEditorSupport{
	
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.isEmpty()){
			setValue(null);
		} else {
			if (Integer.parseInt(text) == 0){
				setValue(new RadioNoteType());
			} else {
				Integer tid = Integer.valueOf(text);
				setValue(Context.getService(RadioTransactionService.class).getNoteType(tid));
			}
		}
	}

}
