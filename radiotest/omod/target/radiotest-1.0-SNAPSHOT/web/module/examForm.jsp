<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<form:form method="post" modelAttribute="exam">
	<form:label path="type">Exam Type</form:label>
	<form:select path="type">
		<form:options items="${ types }" itemLabel="type" itemValue="id" />
	</form:select>
	<br>
	
	<form:label path="name">Exam Name</form:label>
	<form:input path="name" />
	<br>
	
	<form:label path="studentFee">Student Fee</form:label>
	<form:input path="studentFee" />
	<br>
	
	<form:label path="employeeFee">Faculty/Employee Fee</form:label>
	<form:input path="employeeFee" />
	<br>
	
	<form:label path="dependentFee">Dependent Fee</form:label>
	<form:input path="dependentFee" />
	<br>
	
	<form:label path="outsiderFee">Outsider Fee</form:label>
	<form:input path="outsiderFee" />
	<br>
	
	<form:label path="readingFee">Reading Fee</form:label>
	<form:input path="readingFee" />
	
	<p>
	<h1>Senior Citizen</h1>
	<br>
	
	<form:label path="seniorFee">Fee</form:label>
	<form:input path="seniorFee" />
	<br>
	
	<form:label path="senReadingFee">Reading Fee</form:label>
	<form:input path="senReadingFee" />
	<br>
	
	<input type="submit" value="Save" />
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>