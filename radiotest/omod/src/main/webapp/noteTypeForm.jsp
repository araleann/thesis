<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/types.js" />

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<h2>Add Note Type</h2>
<br>
<form:form method="post" modelAttribute="noteType" id="typeForm" action="javascript:saveNoteType()">
	<form:input id="notetype" path="name" cssClass="patientinput" />
	<br><br>
	<button type="button" onclick="saveNoteType()" class="buttondesign">Save</button>
	<button type="button" onclick="clearForm()" class="buttondesign">Clear</button>
</form:form>
<br>
<hr>
<br>
<h2>Existing Note Types</h2>
<div id="noteTypes">
	<c:forEach var="type" items="${ noteTypes }">
		<c:set var="id" value="${ type.id }" />
		<div id="note${ id }">
			<font size=4><b>${ type.name }</b></font>
			<br>
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
					<button type="button" onclick="voidNoteType(${ id })" class="buttondesignvoid">Unvoid</button>			
				</c:when>
				<c:otherwise>
					<button type="button" onclick="voidNoteType(${ id })" class="buttondesignvoid">Void</button>
				</c:otherwise>
			</c:choose>
			<button type="button" onclick="deleteNoteType(${ id })" class="buttondesignsmall">Delete</button>
			<button type="button" onclick="editNoteType(${ id })" class="buttondesignsmall">Edit</button>
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