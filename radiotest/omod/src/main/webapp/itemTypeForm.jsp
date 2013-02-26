<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var nullPath = modulePath + "/nullItemType.htm";

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

function saveItemType(){
	var divId = "#itemTypes";
	var $form = $j("#typeForm");
	var obj = $form.serialize();
	var path = $form.attr("action");
	
	function focus(){
		$j("input[type=text]")
			.val("")
			.focus();
	}
	
	post(divId, obj, path, focus);
}

function voidItemType(id){
	var divId = "#type" + id;
	var obj = {
		tid : id,
		action : "void"
	};
	
	post(divId, obj, nullPath);
}

function deleteItemType(id){
	console.log("delete!");
	var divId = "#type" + id;
	var obj = {
		tid : id,
		action : "delete"
	};
	
	if (confirm("Are you sure you want to delete?")){
		post(divId, obj, nullPath);
	}
}
//-->
</script>

<h2>Add Item Type</h2>
<form:form method="post" modelAttribute="itemType" id="typeForm">
	<form:input path="name" />
	<button type="button" onclick="saveItemType()">Save</button>
</form:form>

<h2>Existing Item Types</h2>
<div id="itemTypes">
	<c:forEach var="type" items="${ itemTypes }">
		<c:set var="id" value="${ type.id }" />
		<div id="type${ id }">
			${ type.name } <br>
			Voided:
			<c:choose>
				<c:when test="${ type.voided }">
					YES
				</c:when>
				<c:otherwise>
					NO
				</c:otherwise>
			</c:choose>
			<button type="button" onclick="voidItemType(${ id })"></button> <br>
			<button type="button" onclick="deleteItemType(${ id })">Delete</button>
		</div>
	</c:forEach>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>