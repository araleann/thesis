package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.openmrs.module.radiotest.RadioAlias;
import org.springframework.util.StringUtils;

public class RadioAliasPropertyEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			setValue(new RadioAlias(text));
		} else
			setValue(null);
	}
}
