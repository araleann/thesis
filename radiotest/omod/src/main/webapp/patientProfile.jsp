<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="template/resources.jsp" %>
<%@ include file="template/tables.jsp" %>

<openmrs:htmlInclude file="/moduleResources/radiotest/pages/patient.js" />
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/transaction.js" />
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/result.js" />

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<c:set var="p" value="${ patient }" />
<fieldset>
<legend><h2>${ p.fullName }</h2></legend>
	<jsp:include page="/WEB-INF/view/topnav.jsp"/>
<div id="details">	
<div id="patient">	
	<p>
	Date of Last Update: ${ p.updateDate }
	</p>
	<h3>Basic Information</h3>
	<br><label>Full Name:</label>${ p.fullName }
	<br>
	<br><label>Case No:</label>${ p.caseNumber }
	<br>
	<c:set var="c" value="${ p.category }" />
	<label>Category:</label>${ c.category }
	<br>
	<c:set var="a" value="${ p.alias }" />
	<label>Alias: </label> ${ a.alias } 
	<br><label>Philhealth: </label> ${ p.philhealth }
	<br><label>Sex: </label> ${ p.gender }
	<br><label>Civil Status: </label> ${ p.civilStatus }

	<br><label id="patient">Birthday: </label> <fmt:formatDate type="date" 
            value="${ p.birthday }" />
	
	<br><br>
	<h3>Contact Details</h3>
	<br><label>Contact Number: </label> ${ p.contactNo }
	<br><label id="patient">Institution: </label> ${ p.institution }

	<br><br>
	<h3>Housing</h3>
	<br><label id="patient">Street Address: </label> ${ p.streetAddress }
	<br><label id="patient">Barangay: </label> ${ p.barangay }
	<br><label id="patient">City: </label> ${ p.city }
	<br><label id="patient">Region: </label> ${ p.region }
	<br><br>
	<button type="button" class="buttondesignmedium" onclick="editPatient()">Edit Profile</button>
	<br><br>
</div>
<div id="update">

</div>
</div>
</fieldset>

</div>
</div>
<div class="col2">
<div class="sideholder">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
</div>
</div>
</div>
</div>