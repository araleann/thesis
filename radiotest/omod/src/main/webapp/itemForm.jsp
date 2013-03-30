<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/inventory.js" />

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<h2>Items</h2>
<form:form method="post" modelAttribute="item" id="itemForm">
	<p>
	<form:hidden path="id" />
	<form:select cssClass="patientinput" path="type">
		<option value="0" selected disabled>Choose Item type</option>
		<form:options items="${ itemTypes }" itemLabel="name" itemValue="id" />
	</form:select>
	</p>
	
	<p>
	<form:input id="itemname" cssClass="patientinput" path="name" />
	</p>
	
	<p>
	<form:input id="unit" cssClass="patientinput" path="unit" />
	</p>
	
	<p>
	<b>Threshold:</b><br>
	<c:set var="t" value="${ item.threshold }" />
	<c:set var="pt" value="${ item.percentThreshold }" />
	<c:set var="threshold" value="${ t > pt? t : pt }" />
	<input type="text" class="patientinputshort" name="limit" value="${ threshold }">
	<select name="limitType" class="patientinputshort">
		<option value="1">units</option>
		<option value="2" 
		<c:if test="${ pt > t }">
			selected
		</c:if>
		>%</option>
	</select>
	</p>
	<button class="buttondesign" type="button" onclick="saveItem()">Save</button>
	<button class="buttondesign" type="button" onclick="clearForm()">Clear</button>
</form:form>
<br>
<hr>
<br>
<h2>Existing Items</h2>
<div id="items">
	<c:forEach var="itemMap" items="${ items }">
		<c:set var="type" value="${ itemMap.key }" />
		<c:set var="list" value="${ itemMap.value }" />
		<div id="type${ type.id }">
			<h4>${ type.name }</h4>
			<c:forEach var="i" items="${ list }">
				<c:set var="id" value="${ i.id }" />
				<div id="item${ id }">
					Name: <b> ${ i.name } </b><br>
					Unit: ${ i.unit } <br>
					Threshold: 
					<c:choose>
						<c:when test="${ i.percentThreshold == 0 }">
							${ i.threshold } ${ i.unit }
						</c:when>
						<c:otherwise>
							${ i.percentThreshold }%
						</c:otherwise>
					</c:choose>
					<br>
					Voided: 
					<c:choose>
						<c:when test="${ i.voided }">
							YES
						</c:when>
						<c:otherwise>
							NO
						</c:otherwise>
					</c:choose>
					<br>
					<c:choose>
						<c:when test="${ i.voided }">
							<button type="button" onclick="voidItem(${ id })" class="buttondesignvoid">Unvoid</button>
						</c:when>
						<c:otherwise>
							<button type="button" onclick="voidItem(${ id })" class="buttondesignvoid">Void</button>
						</c:otherwise>
					</c:choose>
					<button type="button" onclick="deleteItem(${ id })" class="buttondesignsmall">Delete</button>
					<button type="button" onclick="editItem(${ id })" class="buttondesignsmall">Edit</button>
				</div>
			</c:forEach>
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