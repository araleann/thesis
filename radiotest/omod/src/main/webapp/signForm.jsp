<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/moduleResources/radiotest/GeneralUtils.js" />
<openmrs:htmlInclude file="/moduleResources/radiotest/types.js" />
<script>
	$j(function(){
		var inputPlaceholders = {
			name: "Doctor's Name",
			position: "Position",
		};
		
		GeneralUtils.addPlaceholderById(inputPlaceholders);
	});
</script>

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
		
<br>
<h2>Add Signatures</h2>
<br>
<form:form method="post" modelAttribute="signature" id="signForm" action="javascript:saveSignature()">
	<form:hidden path="id" />
	<form:input path="name" id="name" cssClass="patientinput" /><br><br>
	<form:input path="position" id="position" cssClass="patientinput" />
	<br><br>
	<button type="button" class="buttondesign" onclick="saveSignature()">Save</button>
	<button type="button" class="buttondesign" onclick="clearForm()">Clear</button>
</form:form>
<br><hr>
<h2>Existing Signatures</h2>
<div id="signatures">
	<c:forEach var="sign" items="${ signatures }">
		<c:set var="id" value="${ sign.id }" />
		<div id="sign${ id }">
			<font size="4"><b>${ sign.name }</b></font><br>
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
					<button type="button" class="buttondesignvoid" onclick="voidSignature(${ id })">Unvoid</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="buttondesignvoid" onclick="voidSignature(${ id })">Void</button>
				</c:otherwise>
			</c:choose>
			<button type="button" class="buttondesignsmall" onclick="deleteSignature(${ id })">Delete</button>
			<button type="button" class="buttondesignsmall" onclick="editSignature(${ id })">Edit</button>
		</div>
	</c:forEach>
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