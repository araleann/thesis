package org.openmrs.module.radiotest.propertyeditor;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.api.RadioInventoryService;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

public class RadioItemCollectionEditor extends CustomCollectionEditor {

	public RadioItemCollectionEditor(Class<?> collectionType) {
		super(collectionType);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object convertElement(Object element){
		Integer id = Integer.valueOf((String) element);
		return Context.getService(RadioInventoryService.class).getItem(id);
	}
}
