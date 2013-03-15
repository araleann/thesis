<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<script>
</script>

<div id="patientList">
	<p>
	<button type="button" class="buttondesignsmalllong" onclick="newPatient()">Create New Patient</button>
	<br>
	<table id="patient">
	<tr>
	<th>Name</th>
	<th>Category</th>
	<th>Alias</th>
	</tr>
	<c:forEach var="p" items="${ list }">
		<tr onclick="getPatient(${ p.id })">
		<td>${ p.fullName }</td>
		<td>${ p.category.category }</td>
		<td>${ p.alias.alias }</td>
		</tr>
	</c:forEach>
	</table>
	</p>
</div>