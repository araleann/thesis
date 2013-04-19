package org.openmrs.module.radiotest.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.api.RadioExamService;

public class RadioExamPropertyEditor extends PropertyEditorSupport{

	public void setAsText(String text) throws IllegalArgumentException {
		Integer eid = Integer.valueOf(text);
		RadioExam exam;
		
		if(eid == 0){
			exam = new RadioExam();
			exam.setName("");
			exam.setResultTemplate(null);
		} else {
			exam = Context.getService(RadioExamService.class).getExam(eid);
		}
		
		setValue(exam);
	}
}
