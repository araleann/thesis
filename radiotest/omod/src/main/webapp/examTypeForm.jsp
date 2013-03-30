<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/moduleResources/radiotest/GeneralUtils.js" />
<openmrs:htmlInclude file="/moduleResources/radiotest/types.js" />

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<h2>Add Exam Type</h2>
<form:form method="post" modelAttribute="examType" id="typeForm" action="javascript:saveExamType()">
	<form:hidden path="id" />
	<form:input path="type" id="examtype" cssClass="patientinput" />
	<br>
	<br>
	<form:textarea id="template" path="template" cssClass="patientinputtextarea1"/>
	<br>
	<button type="button" onclick="saveExamType()" class="buttondesign">Save</button>
	<button type="button" onclick="clearForm()" class="buttondesign">Clear</button>
</form:form>
<br>
<hr>
<br>
<h2>Existing Exam Types</h2>
<div id="examTypes">
	<c:forEach var="type" items="${ examTypes }">
		<c:set var="id" value="${ type.id }" />
		<div id="type${ id }">
			${ type.type } <br>
			<c:if test="${ not empty type.template }">
				<div id="template${ id }" class="template">
					<i>"${ type.template }"</i>
				</div>
			</c:if>
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
					<button type="button" onclick="voidExamType(${ id })" class="buttondesignvoid">Unvoid</button>
				</c:when>
				<c:otherwise>
					<button type="button" onclick="voidExamType(${ id })" class="buttondesignvoid">Void</button>
				</c:otherwise>
			</c:choose>
			
			<button type="button" onclick="deleteExamType(${ id })" class="buttondesignsmall">Delete</button>
			<button type="button" onclick="editExamType(${ id })" class="buttondesignsmall">Edit</button>
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