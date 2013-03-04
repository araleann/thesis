<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<fieldset class="field">
	<legend>${ itemType.name }</legend>
	<button type="button" onclick="deleteType(${ index })">Delete</button>
	<button type="button" onclick="addItem(${ index })">Add Item</button>
	<br>
	<br>
	<div id="type${ index }">
		<input type="hidden" name="type" value="${ itemType.id }">
		<div class="item">
			<select>
				<option value="0"></option>
				<c:forEach var="item" items="${ items }">
					<option value="${ item.id }">${ item.name } (${ item.unit })</option>
				</c:forEach>
			</select>
			<input type="text" placeholder="Qty">
			<button type="button">Delete Item</button>
		</div>
	</div>
</fieldset>