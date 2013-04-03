<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/inventory.js" />

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<h2>Add Stock</h2>
<div id="notif">
		<c:forEach var="i" items="${ items }">
			<c:choose>
				<c:when test="${ i.atLimit }">
					<div class="notif">
						<div id="tab">
						<b> WARNING! </b> ${ i.name }  is at threshold.
						</div>
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
</div>
<br>
<div id="itemDiv">
	<form id="itemsForm">
		<select class="patientinputmedium" id="type" name="type" onchange="getItems()">
			<option value="0">All</option>
			<c:forEach var="type" items="${ itemTypes }">
				<option value="${ type.id }">${ type.name }</option>
			</c:forEach>
		</select>
		<br><br>
		<div class="items">
			<select class="patientinputmediummult" id="items" name="items" size="5" multiple>
				<c:forEach var="item" items="${ items }">
					<c:set var="id" value="${ item.id }" />
					<option value="${ id }" ondblclick="addItems()">${ item.name }</option>
				</c:forEach>
			</select>
		</div>
		<button type="button" class="buttondesign1" onclick="addItems()">Add Item(s)</button>
	</form>
</div>
<br>
<div id="listingDiv">
	<form:form method="post" modelAttribute="stockModel" id="stockForm">
		<div id="listings">
			
		</div>
		<br><button type="button" class="buttondesign" style="width:120px;" onclick="addStock()">Add Stock</button>
	</form:form>
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