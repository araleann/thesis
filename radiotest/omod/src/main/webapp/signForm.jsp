<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/moduleResources/radiotest/GeneralUtils.js" />
<openmrs:htmlInclude file="/moduleResources/radiotest/types.js" />

<h2>Add Signatures</h2>
<form:form method="post" modelAttribute="signature" id="signForm" action="javascript:saveSignature()">
	<form:hidden path="id" />
	<form:input path="name" /><br>
	<form:input path="position" />
	<br><br>
	<button type="button" onclick="saveSignature()">Save</button>
	<button type="button" onclick="clearForm()">Clear</button>
</form:form>

<h2>Existing Signatures</h2>
<div id="signatures">
	<c:forEach var="sign" items="${ signatures }">
		<c:set var="id" value="${ sign.id }" />
		<div id="sign${ id }">
			${ sign.name } <br>
			${ sign.position } <br>
			<c:set var="voided" value="${ sign.voided }" />
			Voided:
			<c:choose>
				<c:when test="${ voided }">
					YES
				</c:when>
				<c:otherwise>
					NO
				</c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when test="${ voided }">
					<button type="button" onclick="voidSignature(${ id })">Unvoid</button>
				</c:when>
				<c:otherwise>
					<button type="button" onclick="voidSignature(${ id })">Void</button>
				</c:otherwise>
			</c:choose>
			<button type="button" onclick="deleteSignature(${ id })">Delete</button>
			<button type="button" onclick="editSignature(${ id })">Edit</button>
		</div>
	</c:forEach>
</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>