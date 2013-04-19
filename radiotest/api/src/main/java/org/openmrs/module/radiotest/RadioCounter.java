package org.openmrs.module.radiotest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.api.RadioPatientService;

public class RadioCounter {
	
	private Integer id;
	
	private static RadioCounter instance;
	private int count;
	private Calendar nextYear;
	
	private DateFormat format = new SimpleDateFormat("yy");
	
	private RadioCounter(){
		reset();
	}
	
	public static void initialize(){
		instance = Context.getService(RadioPatientService.class).saveCounter(new RadioCounter());
	}

	public static RadioCounter getInstance(){
		if(instance == null){
			instance = Context.getService(RadioPatientService.class).getCounter();
		}
		
		return instance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Calendar getNextYear() {
		return nextYear;
	}

	public void setNextYear(Calendar nextYear) {
		this.nextYear = nextYear;
	}
	
	// CUSTOM FUNCTIONS
	public String getNewCaseNumber(){
		Calendar current = new GregorianCalendar();
		if (nextYear.before(current)){
			reset();
		}
		count++;
		Context.getService(RadioPatientService.class).saveCounter(this);
		return String.format("%ty-%04d", current, count);
	}
	
	public boolean isValid(String caseNumber){
		if(caseNumber == null){
			return false;
		}
		
		if(caseNumber.isEmpty())
			return false;
		
		Date current = null;
		try {
			current = format.parse(caseNumber);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return current.before(nextYear.getTime());
	}
	
	private void reset(){
		Calendar base = new GregorianCalendar();
		base.add(Calendar.YEAR, 1);
		
		nextYear = new GregorianCalendar(base.get(Calendar.YEAR), 0, 1, 0, 0, 0);
		count = 0;
	}
}
