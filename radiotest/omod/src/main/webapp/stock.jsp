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
		$j("#items").addClass("patientinputmediummult");;
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
<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<h2>Add Stock</h2>

<div id="itemDiv">
	<select class="patientinputmedium" id="type" name="type" onchange="getItems()">
		<option value="0">All</option>
		<c:forEach var="type" items="${ itemTypes }">
			<option value="${ type.id }">${ type.name }</option>
		</c:forEach>
	</select>
	<br><br>
	<div class="items">
		<select class="patientinputmediummult" id="item" name="items" size="5" multiple>
			<c:forEach var="item" items="${ items }">
				<c:set var="id" value="${ item.id }" />
				<option value="${ id }" ondblclick="addItems()">${ item.name }</option>
			</c:forEach>
		</select>
	</div>
	<button type="button" class="buttondesign1" onclick="addItems()">Add Item(s)</button>
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