<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<script>

</script>

<div id="patientList">
	<p>
	<button type="button" class="buttondesign" style="width:200px;" onclick="newPatient()">Create New Patient</button>
	<br>
	<table id="patientSearch" class="tbldesign">
		<thead>
			<tr>
				<th>Name</th>
				<th>Category</th>
				<th>Alias</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="p" items="${ list }">
			<tr onclick="getPatient(${ p.id })">
				<td>${ p.fullName }</td>
				<td>${ p.category.category }</td>
				<td>${ p.alias.alias }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</p>
</div>