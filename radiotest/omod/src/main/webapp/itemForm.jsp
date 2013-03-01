<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/resources.jsp" %>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var nullPath = modulePath + "/nullItem.htm";

function post(divId, obj, path, callback){
	console.log("post!");
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
	
	if (confirm("Are you sure you want to delete?")){
		post(divId, obj, nullPath);
	}
}

$j(function(){	
	GeneralUtils.addPlaceholderById("itemname", "Enter Item Name");
	GeneralUtils.addPlaceholderById("unit", "Enter the Unit");
});
//-->
</script>
<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
<br>
<h2>Items</h2>
<form:form method="post" modelAttribute="item" id="itemForm">
	<p>
	<label>Item Type</label>
	<form:select cssClass="patientinput" path="type">
		<option value="0"></option>
		<form:options items="${ itemTypes }" itemLabel="name" itemValue="id" />
	</form:select>
	</p>
	
	<p>
	<form:input id="itemname" cssClass="patientinput" path="name" />
	</p>
	
	<p>
	<form:input id="unit" cssClass="patientinput" path="unit" />
	</p>
	<button class="buttondesign" type="button" onclick="saveItem()">Save</button>
</form:form>
<br>
<hr>
<br>
<h2>Existing Items</h2>
<div id="items">
	<c:forEach var="i" items="${ items }">
		<c:set var="id" value="${ i.id }" />
		<div id="item${ id }">
			Type : ${ i.type.name } <br>
			Name : ${ i.name } <br>
			Unit : ${ i.unit } <br>
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
					<button type="button" onclick="voidItem(${ id })">Unvoid</button>
				</c:when>
				<c:otherwise>
					<button type="button" onclick="voidItem(${ id })">Void</button>
				</c:otherwise>
			</c:choose>
			<button type="button" onclick="deleteItem(${ id })">Delete</button>
		</div>
	</c:forEach>
</div>
</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>