<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<div id="patientList">
	<c:forEach var="p" items="${ list }">
		Name: ${ p.fullName }
		<button type="button" onclick="getPatient(${ p.id })"></button>
		<br>
		Category: ${ p.category.category }
		<br>
		Alias: ${ p.alias.alias }
		<br>
		<br>
	</c:forEach>
</div>