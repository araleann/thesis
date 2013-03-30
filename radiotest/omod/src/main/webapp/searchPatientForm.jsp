<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/patient.js" />

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>	
<h2>Search Patient</h2>
<br>
<form method="post" id="search" onsubmit="javascript: return search()">
	<input type="text" class="patientinput" placeholder="Enter Patient Name or ID" name="searchText">
	<button type="button" onclick="search()"class="buttondesign" >Search</button>
</form>
<br>

<form:form method="post" id="patient">
	<input type="hidden" name="patientId" id="patientId">
</form:form>

<div id="patientList">

</div>

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
</div></div>