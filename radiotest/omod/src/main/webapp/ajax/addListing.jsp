<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<div class="items">
	<select id="items" class="patientinputmediummult" size="5" multiple>
		<c:forEach var="item" items="${ items }">
			<c:set var="id" value="${ item.id }" />
			<option value="${ id }" ondblclick="addItems()">${ item.name }</option>
		</c:forEach>
	</select>
</div>

<div class="listings">
	<c:forEach var="item" items="${ items }">
		<div class="item">
			<input type="hidden" value="${ item.id }">
			<label> ${ item.name } : </label> <input type="text" class="patientinputmedium" placeholder="Qty (${ item.unit })">
			<button type="button" class="buttondesignsmallmedium" onclick="deleteItem(this)">Delete Item</button>
		<br>
		</div>
	</c:forEach>
</div>
