<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<form:form modelAttribute="transModel">
	<spring:nestedPath path="exams[${ index }]">
		<form:select path="exam.type">
			<option value></option>
			<form:options items="${ examTypes }" itemLabel="type" itemValue="id" />
		</form:select>
		<form:select path="exam">
			<option value></option>
			<form:options items="${ exam }" itemLabel="name" itemValue="id" />
		</form:select>
	</spring:nestedPath>
</form:form>