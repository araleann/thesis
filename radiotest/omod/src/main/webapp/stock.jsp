<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var itemPath = modulePath + "/getItems.htm";
var itemIndex = 0;

function addType(){
	var $select = $j("#type");
	var obj = {
		index : itemIndex,
		type : $select.val()
	};
			
	$j.post(itemPath, obj, function(data){
		var $type = $j(".field", data);
		$j("#listings").append($type);
		itemIndex++;
		
		$select
		.find("option:selected")
			.attr("disabled", true)
			.siblings("option[value=0]")
				.attr("selected", true);
	});
}

function addItem(ind){
	var divId = "#type" + ind;
	var $input = $j(divId)
					.find("input[name=type]");
	
	$j.post(itemPath, $input.serialize(), function(data){
		var $item = $j(".item", data);
		$j(divId).append($item);
	});
}

function addStock(){
	$j("#listings .item").each(function(i){
		var listing = "listings[" + i + "]";
		$j("select", this).attr("name", listing +".item");
		$j("input", this).attr("name", listing + ".quantity");
	});
	
	$j("#stockForm").submit();
}
//-->
</script>

<h2>Add Stock</h2>

<form:form method="post" modelAttribute="stockModel" id="stockForm">
	<select name="type" id="type">
		<option value="0"></option>
		<c:forEach var="type" items="${ itemTypes }">
			<option value="${ type.id }">${ type.name }</option>
		</c:forEach>
	</select>
	<button type="button" onclick="addType()">Add Item Type</button>
	<br>
	<br>
	<button type="button" onclick="addStock()">Add Stock</button>
	<div id="listings">
	
	</div>
	
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>