<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<form:form method="post" modelAttribute="noteType">
	<form:input path="name" />
	<button type="submit">Save</button>
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>