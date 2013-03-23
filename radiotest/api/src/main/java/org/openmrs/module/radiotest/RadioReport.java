package org.openmrs.module.radiotest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.util.StringUtils;

public class RadioReport {

	private DetachedCriteria criteria;
	private ProjectionList projectionList;
	
	private List<String> propList;
	private List<String> headers;
	
	private HashMap<Class<?>, String> prefix;
			
	private Date startDate;
	private Date endDate;
	
	private RadioPatient patient;
	private RadioAlias alias;
	private RadioCategory category;
	
	private RadioExamType examType;
	private RadioExam exam;
	
	private RadioTransaction transaction;
	
	public RadioReport(){
		headers = new ArrayList<String>();
		propList = new ArrayList<String>();
		
		prefix = new HashMap<Class<?>, String>();
		prefix.put(RadioPatient.class, "p");
		prefix.put(RadioAlias.class, "a");
		prefix.put(RadioCategory.class, "c");
		prefix.put(RadioExam.class, "e");
		prefix.put(RadioExamType.class, "et");
		prefix.put(RadioTransaction.class, "t");
	}

	public DetachedCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(DetachedCriteria criteria) {
		this.criteria = criteria;
	}

	public ProjectionList getProjectionList() {
		return projectionList;
	}

	public void setProjectionList(ProjectionList projectionList) {
		this.projectionList = projectionList;
	}

	public List<String> getPropList() {
		return propList;
}

	public void setPropList(List<String> propList) {
		this.propList = propList;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public RadioPatient getPatient() {
		return patient;
	}

	public void setPatient(RadioPatient patient) {
		this.patient = patient;
	}

	public RadioAlias getAlias() {
		return alias;
	}

	public void setAlias(RadioAlias alias) {
		this.alias = alias;
	}
	
	public RadioCategory getCategory() {
		return category;
	}

	public void setCategory(RadioCategory category) {
		this.category = category;
	}

	public RadioExamType getExamType() {
		return examType;
	}

	public void setExamType(RadioExamType examType) {
		this.examType = examType;
	}

	public RadioExam getExam() {
		return exam;
	}

	public void setExam(RadioExam exam) {
		this.exam = exam;
	}

	public RadioTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(RadioTransaction transaction) {
		this.transaction = transaction;
	}

	// CUSTOM FUNCTIONS
	
	private Example getExample(Class<?> cls, Object obj){
		try {
			obj = getDetails(cls, obj);
			
			if(obj == null){
				obj = cls.newInstance();
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return Example.create(obj)
					.ignoreCase()
					.excludeZeroes();
	}
	
	private Object getDetails(Class<?> cls, Object obj){
		if(obj == null)
			return obj;
		
		try {
			for(Field fld : cls.getDeclaredFields()){
				fld.setAccessible(true);
				
				String fieldname = fld.getName();
				if(!(fieldname.equals("id") || fieldname.equals("voided"))){
					Object value = fld.get(obj);
					if(value != null){
						Class<?> cs = fld.getType();
						if(value instanceof String){
							String val = (String) value;
							String newVal = StringUtils.hasText(val)? (val.equals("_")? "" : val) : null;
							fld.set(obj, newVal);
						} else if(Collection.class.isAssignableFrom(cs)){
							fld.set(obj, null);
							continue;
						}
						
						propList.add(prefix.get(cls) + "." + fld.getName());
						headers.add(WordUtils.capitalize(fld.getName().replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2")));
					}
				}
				
				fld.setAccessible(false);
			}
		} catch(Exception e){
			
		}
		
		for(String s : propList){
			System.out.println(s);
		}
		
		for(String s : headers){
			System.out.println(s);
		}
		
		return obj;
	}
	
	private ProjectionList createProjectionList(){
		ProjectionList pl = Projections.projectionList();
		
		for(String prop : propList){
			pl.add(Projections.property(prop));
		}
		
		return pl;
	}
	
	public RadioReport generate(){
		criteria = DetachedCriteria
						.forClass(RadioTransExam.class, "te")
							.createCriteria("te.patient", "p")
								.add(getExample(RadioPatient.class, patient))
								.createCriteria("p.aliases", "a")
									.add(getExample(RadioAlias.class, alias))
									.createCriteria("a.category", "c")
										.add(getExample(RadioCategory.class, category))
							.createCriteria("te.exam", "e")
								.add(getExample(RadioExam.class, exam))
								.createCriteria("e.type", "et")
									.add(getExample(RadioExamType.class, examType))
							.createCriteria("te.transaction", "t")
								.add(getExample(RadioTransaction.class, transaction));
		
		criteria.setProjection(createProjectionList());
		return this;
	}

}
