<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<form:form method="post" modelAttribute="examType">
	<form:label path="type">Exam Type</form:label>
	<form:input path="type" />
	<br>
	<input type="submit" value="Save" />
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>