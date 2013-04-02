package org.openmrs.module.radiotest;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.module.radiotest.utils.RadioSecurity;

public class RadioPatient extends BaseOpenmrsData {
	
	private Integer id;
	private String patientId;
	
	private String firstName;
	private String middleInitial;
	private String lastName;
	
	private String gender;
	private String civilStatus;
	private Date birthday;
	private String streetAddress;
	private String region;
	private String city;
	private String barangay;
	private String contactNo;
	private String institution;
	private String philhealth;
	private String caseNumber;
	
	private Date updateDate;
	
	private Set<RadioAlias> aliases;
	private Set<RadioTransaction> transactions;
	
	private RadioIndex index;
	
	private Boolean voided;
	
	// not saved to the database
	private RadioAlias alias;
	private RadioCategory category;
	
	public RadioPatient(){
		aliases = new LinkedHashSet<RadioAlias>();
		transactions = new LinkedHashSet<RadioTransaction>();
		
		voided = false;
	}
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCivilStatus() {
		return civilStatus;
	}

	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getBarangay() {
		return barangay;
	}

	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}
	
	public String getPhilhealth() {
		return philhealth;
	}

	public void setPhilhealth(String philhealth) {
		this.philhealth = philhealth;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Set<RadioAlias> getAliases() {
		if(aliases == null){
			aliases = new LinkedHashSet<RadioAlias>();
		}
		
		return aliases;
	}

	public void setAliases(Set<RadioAlias> aliases) {
		this.aliases = aliases;
	}
	
	public Set<RadioTransaction> getTransactions() {
		if(transactions == null){
			transactions = new LinkedHashSet<RadioTransaction>();
		}
		
		return transactions;
	}

	public void setTransactions(Set<RadioTransaction> transactions) {
		this.transactions = transactions;
	}

	public RadioIndex getIndex() {
		return index;
	}

	public void setIndex(RadioIndex index) {
		this.index = index;
	}

	public Boolean getVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}
	
	// CUSTOM FUNCTIONS
	public String getFullName(){
		return firstName + " " +
				middleInitial + " " +
				lastName;
	}
	
	public boolean addAlias(RadioAlias alias){
		if (aliases.contains(alias))
			return false;
		
		for(RadioAlias a : aliases){
			if (!a.getVoided().booleanValue()){
				a.setEndDate(new Date());
				a.setVoided(Boolean.TRUE);
			}
		}
		aliases.add(alias);
		return true;
	}
	
	public RadioAlias getAlias(){
		for(RadioAlias a : aliases){
			if (!a.getVoided().booleanValue()){
				alias = a;
				category = a.getCategory();
				break;
			}
		}
		return alias;
	}
	
	public RadioCategory getCategory(){
		if (category == null){
			getAlias();
		}
		return category;
	}
	
	public void updateCaseNumber(){
		caseNumber = caseNumber == null? "" : caseNumber;
		RadioCounter c = RadioCounter.getInstance();
		
		if(!c.isValid(caseNumber)){
			caseNumber = c.getNewCaseNumber();
		}
	}
	
	public int getAge(){
		Calendar today = Calendar.getInstance();
	    Calendar birthDate = Calendar.getInstance();

	    int age = 0;

	    birthDate.setTime(birthday);
	    if (birthDate.after(today)) {
	        throw new IllegalArgumentException("Can't be born in the future");
	    }

	    age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

	    // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year   
	    if ( (birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
	            (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH ))){
	        age--;

	     // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
	    }else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH )) &&
	              (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH ))){
	        age--;
	    }

	    return age;
	}
	
	public void decryptName(){
		try {
			firstName = RadioSecurity.decrypt(firstName);
			middleInitial = RadioSecurity.decrypt(middleInitial);
			lastName = RadioSecurity.decrypt(lastName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void processIndex(){
		if(index == null){
			index = new RadioIndex();
		}
		
		index.setPatientId(id.toString());
		index.setName(this.getFullName());
		index.setAlias(this.getAlias().getAlias());
	}
}
