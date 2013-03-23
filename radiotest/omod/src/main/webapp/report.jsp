<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/moduleResources/radiotest/GeneralUtils.js" />
<openmrs:htmlInclude file="/moduleResources/radiotest/report.js" />
<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
<br>
<form:form id="report" modelAttribute="report">
	Date from <form:input path="startDate" cssClass="date" /> to <form:input path="endDate" cssClass="date" />
	<h2>Patient</h2>
	<div id="patient">
		<button type="button" class="filterButton">Add Filter</button>
		<spring:nestedPath path="patient">
			<div class="entity">
				<input type="checkbox" class="general">Full Name <br>
				<div hidden>
					<input type="checkbox">First Name 
					<form:input path="firstName" cssClass="filter" /><br>
					<input type="checkbox">Middle Initial 
					<form:input path="middleInitial" cssClass="filter" /><br>
					<input type="checkbox">Last Name 
					<form:input path="lastName" cssClass="filter" /><br>
				</div>
			</div>
			<div class="entity">
				<input type="checkbox" class="general">Address <br>
				<div hidden>
					<input type="checkbox">Street Address 
					<form:input path="streetAddress" cssClass="filter" /><br>
					<input type="checkbox">Barangay 
					<form:input path="barangay" cssClass="filter" /><br>
					<input type="checkbox">City 
					<form:input path="city" cssClass="filter" /><br>
					<input type="checkbox">Region 
					<form:input path="region" cssClass="filter" /><br>
				</div>
			</div>
			<input type="checkbox">Case Number 
			<form:input path="caseNumber" cssClass="filter" /><br>
			<input type="checkbox">Gender 
			<div class="filter">
				<form:select path="gender">
					<option value=""></option>
					<option value="M">Male</option>
					<option value="F">Female</option>
				</form:select>
			</div>
			<br>
			<input type="checkbox">Civil Status 
			<div class="filter">
				<form:select path="civilStatus">
					<option value=""></option>
					<option value="Single">Single</option>
					<option value="Married">Married</option>
					<option value="Widowed">Widowed</option>
				</form:select>
			</div>
			<br>
			<input type="checkbox">Institution 
			<form:input path="institution" cssClass="filter" /><br>
			<input type="checkbox">Philhealth 
			<form:input path="philhealth" cssClass="filter" /><br>
		</spring:nestedPath>
		<spring:nestedPath path="alias">
			<input type="checkbox">Alias 
			<form:input path="alias" cssClass="filter" /><br>
		</spring:nestedPath>
		<input type="checkbox">Category 
		<form:select path="category" cssClass="filter">
			<option value="0"></option>
			<form:options items="${ categories }" itemLabel="category" itemValue="id" />
		</form:select>
	</div>
	<h2>Exam</h2>
	<div id="exam">
		<button type="button" class="filterButton">Add Filter</button> <br>
		<input type="checkbox">Exam Type
		<form:select path="examType" cssClass="filter">
			<option value="0"></option>
			<form:options items="${ examTypes }" itemLabel="type" itemValue="id" />
		</form:select>
		<br>
		<input type="checkbox">Exam Name
		<form:select path="exam" cssClass="filter">
			<option value="0"></option>
			<form:options items="${ exams }" itemLabel="name" itemValue="id" />
		</form:select>
		<br>
	</div>
	<h2>Transaction</h2>
	<div id="transaction">
		<button type="button" class="filterButton">Add Filter</button> <br>
		<spring:nestedPath path="transaction">
			<input type="checkbox">Visit Date <br>
			<input type="checkbox">Visit Time <br>
			<input type="checkbox">Purpose
			<form:input path="purpose" cssClass="filter" /><br>
			<input type="checkbox">Paid
			<div class="filter">
				<form:radiobutton path="paid" value="true" />Paid
				<form:select path="orNumber">
					<option value=""></option>
					<option value="_%">w/ OR Number</option>
					<option value="_">w/out OR Number</option>
				</form:select>
				<form:radiobutton path="paid" value="false" />Not Paid
				<form:radiobutton path="paid" value="" />All
			</div>
			<br>
			<input type="checkbox">Pending
			<div class="filter">
				<form:select path="pending">
					<option value=""></option>
					<option value="false">Done</option>
					<option value="true">Pending</option>
				</form:select>
			</div>
			<br>
			<input type="checkbox">Claimed
			<div class="filter">
				<form:select path="claimed">
					<option value=""></option>
					<option value="true">Claimed</option>
					<option value="false">Not Claimed</option>
				</form:select>
			</div>
			<input type="checkbox">Total
			
			<br>
		</spring:nestedPath>
	</div>
	<button type="submit">Generate Report</button>
</form:form>
</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>