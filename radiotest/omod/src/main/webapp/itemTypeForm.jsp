<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/types.js" />

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>

<h2>Add Item Type</h2>
<br>
<form:form method="post" modelAttribute="itemType" id="typeForm" action="javascript:saveItemType()">
	<form:hidden path="id" />
	<form:input id="itemtype" cssClass="patientinput" path="name" />
	<br><br>
	<button class="buttondesign" type="button" onclick="saveItemType()">Save</button>
	<button class="buttondesign" type="button" onclick="clearForm()">Clear</button>
</form:form>
<br>
<hr>
<br>
<h2>Existing Item Types</h2>
<div id="itemTypes">
	<c:forEach var="type" items="${ itemTypes }">
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
					<button type="button" onclick="voidItemType(${ id })" class="buttondesignvoid">Unvoid</button>
				</c:when>
				<c:otherwise>
					<button type="button" onclick="voidItemType(${ id })" class="buttondesignvoid">Void</button>
				</c:otherwise>
			</c:choose>			
			<button type="button" onclick="deleteItemType(${ id })" class="buttondesignsmall">Delete</button>
			<button type="button" onclick="editItemType(${ id })" class="buttondesignsmall">Edit</button>
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