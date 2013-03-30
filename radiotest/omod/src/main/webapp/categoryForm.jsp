<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/moduleResources/radiotest/GeneralUtils.js" />
<openmrs:htmlInclude file="/moduleResources/radiotest/types.js" />

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
		
<br>
<h2>Add Category</h2>
<br>
<form:form method="post" modelAttribute="category" id="catForm" action="javascript:saveCategory()">
	<form:hidden path="id" />
	<form:input id="category" cssClass="patientinput" path="category" /><br><br>
	<button class="buttondesign" type="button" onclick="saveCategory()">Save</button>
	<button class="buttondesign" type="button" onclick="clearForm()">Clear</button>
</form:form>
<br>
<hr>
<br>
<h2>Categories</h2>
<br>
<div id="categories">
	<c:forEach var="cat" items="${ categories }">
		<c:set var="id" value="${ cat.id }" />
		<div id="category${ id }">
			<font size="4"><b>${ cat.category }</b></font> <br>
			Voided:
			<c:choose>
				<c:when test="${ cat.voided }">
					YES
				</c:when>
				<c:otherwise>
					NO
				</c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when test="${ cat.voided }">
					<button type="button" onclick="voidCategory(${ id })" class="buttondesignvoid">Unvoid</button>
				</c:when>
				<c:otherwise>
					<button type="button" onclick="voidCategory(${ id })" class="buttondesignvoid">Void</button>
				</c:otherwise>
			</c:choose>					
			<button type="button" onclick="deleteCategory(${ id })" class="buttondesignsmall">Delete</button>
			<button type="button" onclick="editCategory(${ id })" class="buttondesignsmall">Edit</button>
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
