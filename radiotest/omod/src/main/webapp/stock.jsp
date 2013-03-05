<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var itemPath = modulePath + "/getItems.htm";
var stockPath = modulePath + "/addListing.htm";

function getItems(){
	$j.post(itemPath, $j("#type").serialize(), function(data){
		var $items = $j(".items", data);
		$j(".items").replaceWith($items);
	});
}

function deleteItem(buttonElem){
	$j(buttonElem)
		.parent()
			.remove();
}

function addItems(){
	$j.post(stockPath, $j("#item").serialize(), function(data){
		var $listings = $j(".listings", data);
		$j("#listings").append($listings.children());
	});
}

function addStock(){
	$j("#listings .item").each(function(i){
		var list = "listings[" + i + "]";
		$j("input:hidden", this)
			.attr("name", list + ".item");
		$j("input:text", this)
			.attr("name", list + ".quantity");
	});
	
	$j("#stockForm").submit();
}
//-->
</script>

<h2>Add Stock</h2>

<div id="itemDiv">
	<select id="type" name="type" onchange="getItems()">
		<option value="0">All</option>
		<c:forEach var="type" items="${ itemTypes }">
			<option value="${ type.id }">${ type.name }</option>
		</c:forEach>
	</select>
	<br>
	<div class="items">
		<select id="item" name="items" size="5" multiple>
			<c:forEach var="item" items="${ items }">
				<c:set var="id" value="${ item.id }" />
				<option value="${ id }" ondblclick="addItems()">${ item.name }</option>
			</c:forEach>
		</select>
		<button type="button" onclick="addItems()">Add Item(s)</button>
	</div>
</div>

<div id="listingDiv">
	<form:form method="post" modelAttribute="stockModel" id="stockForm">
		<div id="listings">
			
		</div>
		<button type="button" onclick="addStock()">Add Stock</button>
	</form:form>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>