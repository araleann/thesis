<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<div class="items">
	<select id="items" size="5" multiple>
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
			${ item.name } : <input type="text" placeholder="Qty (${ item.unit })">
			<button type="button" onclick="deleteItem(this)">Delete Item</button>
		</div>
	</c:forEach>
</div>
