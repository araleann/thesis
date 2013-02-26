<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var nullPath = modulePath + "/nullItem.htm";

function post(divId, obj, path, callback){
	$j.post(path, obj, function(data){
		var $divElem = $j(divId);
		var $updatedDiv = $j(divId, $j(data));
		if ($updatedDiv.length){
			$divElem.replaceWith($updatedDiv);
		} else {
			$divElem.remove();
		}
		if (callback){
			callback();
		}
	});
}

function saveItem(){
	var divId = "#items";
	var $form = $j("#itemForm");
	var obj = $form.serialize();
	var path = $form.attr("action");
	
	function focus(){
		$j("input[type=text]")
			.val("")
			focus();
	}
	
	post(divId, obj, path, focus);
}

function voidItem(id){
	var divId = "#item" + id;
	var obj = {
		iid : id,
		action : "void"
	};
	
	post(divId, obj, nullPath);
}

function deleteItem(id){
	var divId = "#item" + id;
	var obj = {
		iid : id,
		action : "delete"
	};
	
	function clean(){
		$j("div h4:only-child")
			.parent()
			.remove();
	}
	
	if (confirm("Are you sure you want to delete?")){
		post(divId, obj, nullPath, clean);
	}
}
//-->
</script>

<h2>Items</h2>

<form:form method="post" modelAttribute="item" id="itemForm">
	<p>
	<label>Item Type</label>
	<form:select path="type">
		<option value="0"></option>
		<form:options items="${ itemTypes }" itemLabel="name" itemValue="id" />
	</form:select>
	</p>
	
	<p>
	<label>Item Name</label>
	<form:input path="name" />
	</p>
	
	<p>
	<label>Unit</label>
	<form:input path="unit" />
	</p>
	
	<p>
	<label>Threshold</label>
	<input type="text" name="limit">
	<select name="limitType">
		<option value="1">units</option>
		<option value="2">%</option>
	</select>
	</p>
	<button type="button" onclick="saveItem()">Save</button>
</form:form>

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
					Name: ${ i.name } <br>
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
					<button type="button" onclick="voidItem(${ id })"></button>
					<br>
					<button type="button" onclick="deleteItem(${ id })">Delete</button>
				</div>
			</c:forEach>
		</div>
		
	</c:forEach>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>