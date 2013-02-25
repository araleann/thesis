package org.openmrs.module.radiotest.propertyeditor;

import java.util.Comparator;

import org.openmrs.OpenmrsObject;

public class RadioComparator implements Comparator<OpenmrsObject>{

	@Override
	public int compare(OpenmrsObject o1, OpenmrsObject o2) {
		// TODO Auto-generated method stub
		Integer id1 = o1.getId();
		Integer id2 = o2.getId();
		
		return (id1 < id2? -1 : (id1 == id2? 0 : 1));
	}

}
