<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/moduleResources/radiotest/report.js" />

<form:form id="report" modelAttribute="reportModel">
	<spring:nestedPath path="report">
		<h2>Patient</h2>
		<div id="patient">
			<button type="button" class="filterButton">Add Filter</button>
			<spring:nestedPath path="patient">
				<div class="entity">
					<input type="checkbox" class="general">Full Name <br>
					<div hidden>
						<input type="checkbox" value="p.firstName">First Name 
						<form:input path="firstName" cssClass="filter" /><br>
						<input type="checkbox" value="p.middleInitial">Middle Initial 
						<form:input path="middleInitial" cssClass="filter" /><br>
						<input type="checkbox" value="p.lastName">Last Name 
						<form:input path="lastName" cssClass="filter" /><br>
					</div>
				</div>
				<div class="entity">
					<input type="checkbox" class="general">Address <br>
					<div hidden>
						<input type="checkbox" value="p.streetAddress">Street Address 
						<form:input path="streetAddress" cssClass="filter" /><br>
						<input type="checkbox" value="p.barangay">Barangay 
						<form:input path="barangay" cssClass="filter" /><br>
						<input type="checkbox" value="p.city">City 
						<form:input path="city" cssClass="filter" /><br>
						<input type="checkbox" value="p.region">Region 
						<form:input path="region" cssClass="filter" /><br>
					</div>
				</div>
				<input type="checkbox" value="p.caseNumber">Case Number 
				<form:input path="caseNumber" cssClass="filter" /><br>
				<input type="checkbox" value="p.gender">Gender 
				<div class="filter">
					<form:radiobutton path="gender" value="M" />Male
					<form:radiobutton path="gender" value="F" />Female<br>
				</div>
				<input type="checkbox" value="p.civilStatus">Civil Status 
				<div class="filter">
					<form:radiobutton path="civilStatus" value="Single" />Single
					<form:radiobutton path="civilStatus" value="Married" />Married
					<form:radiobutton path="civilStatus" value="Widowed" />Widowed<br>
				</div>
				<input type="checkbox" value="p.institution">Institution 
				<form:input path="institution" cssClass="filter" /><br>
				<input type="checkbox" value="p.philhealth">Philhealth 
				<form:input path="philhealth" cssClass="filter" /><br>
			</spring:nestedPath>
			<spring:nestedPath path="alias">
				<input type="checkbox" value="a.alias">Alias 
				<form:input path="alias" cssClass="filter" /><br>
			</spring:nestedPath>
			<input type="checkbox" value="c.category">Category 
			<form:select path="category" cssClass="filter">
				<option value="0"></option>
				<form:options items="${ categories }" itemLabel="category" itemValue="id" />
			</form:select><br>
			
		</div>
	</spring:nestedPath>
	<button type="button" onclick="processReport()">Generate Report</button>
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>