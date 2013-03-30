<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/transaction.js" />
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/redirect.js" />

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<c:set var="p" value="${ patient }" />
<fieldset>
<legend><h2>${ p.fullName }</h2></legend>
<div id="details">
<div class="transaction">
<br>
<h3>New Transaction</h3>
<br>
<form:form method="post" modelAttribute="transModel" id="transExam">
	<spring:bind path="transaction.patient">
		<input type="hidden" name="${ status.expression }" value="${ patient.id }">
	</spring:bind>
	<spring:nestedPath path="exams[0]">
		<div id="exams0">
			Exam 1
			<div id="type">
				<form:select cssClass="patientinput" path="exam.type">
					<option value="0"></option>
					<form:options items="${ examTypes }" itemLabel="type" itemValue="id" />
				</form:select>
				<button class="adddesignsmall" type="button" id="add" onclick="addNewExam()">+</button>
				<button class="adddesignsmall" type="button" id="delete" onclick="deleteExam()" disabled>-</button>
			</div>
		</div>
	</spring:nestedPath>
</div>
	
	<br>
	<button class="searchbutton" type="submit">Done</button>
	<button class="searchbutton" type="button" onclick="load_profile()">Cancel</button>
	<br><br>
	
</form:form>
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