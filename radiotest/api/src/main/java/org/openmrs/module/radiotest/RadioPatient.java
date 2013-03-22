package org.openmrs.module.radiotest;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class RadioPatient extends RadioData {
	
	protected Integer id;
	protected String patientId;
	
	protected String firstName;
	protected String middleInitial;
	protected String lastName;
	
	protected String gender;
	protected String civilStatus;
	protected Date birthday;
	protected String streetAddress;
	protected String region;
	protected String city;
	protected String barangay;
	protected String contactNo;
	protected String institution;
	protected String philhealth;
	protected String caseNumber;
	
	protected Date updateDate;
	
	protected Set<RadioAlias> aliases;
	protected Set<RadioTransaction> transactions;
	
	protected Boolean voided;
	
	// not saved to the database
	protected RadioAlias alias;
	protected RadioCategory category;
	
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

	@Override
	public String getPrefix() {
		// TODO Auto-generated method stub
		return "p";
	}
}
