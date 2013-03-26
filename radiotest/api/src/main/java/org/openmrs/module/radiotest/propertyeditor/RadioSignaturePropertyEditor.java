package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.api.RadioTransactionService;

public class RadioSignaturePropertyEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws IllegalArgumentException {
		Integer sid = Integer.valueOf(text);
		setValue(Context.getService(RadioTransactionService.class).getSignature(sid));
	}
}
