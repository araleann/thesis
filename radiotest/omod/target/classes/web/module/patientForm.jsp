<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<form:form method="post" modelAttribute="patientModel">
	<h3>Basic Information</h3>
	<div class="info">
	<p><label for="name">Last Name</label> 
	<form:input cssClass="patientinput" path="patient.firstName" />
	</p>
	<p><label for="name">First Name</label>
	<form:input cssClass="patientinput" path="patient.middleInitial" />
	</p>
	<p><label for="name">Middle Name</label>
	<form:input cssClass="patientinput" path="patient.lastName" />
	</p>
	
	<p><label for="alias">Alias</label> 
	<form:input cssClass="patientinput" path="alias.alias" />
	</p>
	<p><label for="alias">Type</label> 
	<form:select cssClass="patientinput" path="alias.category">
		<form:options items="${ categories }" itemLabel="category" itemValue="id" />
	</form:select>
	</p>
	
	<p><label for="name">PhilHealth no.</label> 
	<form:input cssClass="patientinput" path="patient.philhealth" />
	</p>
	
	<p><label for="sex">Sex</label> 
	<form:radiobutton cssClass="regular-radio" path="patient.gender" value="M" />Male
	<form:radiobutton cssClass="regular-radio" path="patient.gender" value="F" />Female
	</p>
	
	<p><label for="civilstatus">Civil Status</label> 
	<form:radiobutton cssClass="regular-radio" path="patient.civilStatus" value="Single" />Single
	<form:radiobutton cssClass="regular-radio" path="patient.civilStatus" value="Married" />Married
	<form:radiobutton cssClass="regular-radio" path="patient.civilStatus" value="Widowed" />Widowed
	</p>
	</div>
	
	<h3>Contact details</h3>
	<div class="info">
	<p><label for="name">Contact No.</label> 
	<form:input cssClass="patientinput" path="patient.contactNo" />
	</p>
	<p><label for="name">Office/School</label> 
	<form:input cssClass="patientinput" path="patient.institution" />
	</p>
	</div>
	
	<h3>Housing</h3>
	<div class="info">
	<p><label for="alias">Region</label> 
	<form:input cssClass="patientinput" path="patient.region" />
	</p>
	<p><label for="alias">City</label>
	<form:input cssClass="patientinput" path="patient.city" />
	</p>
	<p><label for="alias">Barangay/Municipality</label>
	<form:input cssClass="patientinput" path="patient.barangay" />
	</p>
	<p><label for="alias">Street Address</label>
	<form:input cssClass="patientinput" path="patient.streetAddress" />
	</p>
	</div>
	
	<br>
	<input class="buttondesign" type="submit" value="Save" />
</form:form>
