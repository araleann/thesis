<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/moduleResources/radiotest/GeneralUtils.js" />
<openmrs:htmlInclude file="/moduleResources/radiotest/types.js" />

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
		
<br>
<h2>Add Fee Type</h2>
<br>
<form:form method="post" modelAttribute="feeType" id="feeForm" action="javascript:saveFeeType()">
	<form:hidden path="id" />
	<form:input id="feetype" cssClass="patientinput" path="name" />
	<br><br>
	<button class="buttondesign" type="button" onclick="saveFeeType()">Save</button>
</form:form>

<br>
<hr>
<br>
<h2>Existing Fee Types</h2>
<div id="feeTypes">
	<c:forEach var="type" items="${ feeTypes }">
		<c:set var="id" value="${ type.id }" />
		<div id="type${ id }">
			<b><font size=4>${ type.name }</font></b><br>
			Voided:
			<c:choose>
				<c:when test="${ type.voided }">
					YES
				</c:when>
				<c:otherwise>
					NO	
				</c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when test="${ type.voided }">
					<button type="button" onclick="voidFeeType(${ id })" class="buttondesignvoid">Unvoid</button>
				</c:when>
				<c:otherwise>
					<button type="button" onclick="voidFeeType(${ id })" class="buttondesignvoid">Void</button>	
				</c:otherwise>
			</c:choose>				
			<button type="button" onclick="deleteFeeType(${ id })" class="buttondesignsmall">Delete</button>
			<button type="button" onclick="editFeeType(${ id })" class="buttondesignsmall">Edit</button>
		</div>
		<br>
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