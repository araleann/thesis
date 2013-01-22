package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.openmrs.module.radiotest.association.RadioAlias;
import org.springframework.util.StringUtils;

public class RadioAliasPropertyEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			System.out.println(getValue() instanceof RadioAlias);
			setValue(new RadioAlias(text));
		} else
			setValue(null);
	}
}
