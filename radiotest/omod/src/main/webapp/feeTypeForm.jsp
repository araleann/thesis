<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var nullFeePath = modulePath + "/nullFeeType.htm";

function loadFeeTypes(){
	var divId = "#feeTypes";
	var $form = $j("#feeForm");
	var obj = $form.serialize();
	var path = $form.attr("action");
	
	function focus(){
		$j("input[type=text]")
			.val("")
			.focus();
	}
	
	post(divId, obj, path, focus);
}

function voidFeeType(id){
	var divId = "#type" + id;
	var obj = {
		tid : id,
		action : "void"
	};
	
	post(divId, obj, nullFeePath);
}

function deleteFeeType(id){
	var divId  = "#type" + id;
	var obj = {
		tid : id,
		action : "delete"
	};
	
	if (confirm("Are you sure you want to delete?")){
		post(divId, obj, nullFeePath);
	}
}

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

//-->
</script>


<h2>Add Fee Type</h2>
<form:form method="post" modelAttribute="feeType" id="feeForm">
	<form:input path="name" />
	<button type="button" onclick="loadFeeTypes()">Save</button>
</form:form>

<h2>Existing Fee Types</h2>
<div id="feeTypes">
	<c:forEach var="type" items="${ feeTypes }">
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
			<button type="button" onclick="voidFeeType(${ id })" class="buttondesignvoid">Void</button>
			<br>
			<button type="button" onclick="deleteFeeType(${ id })" class="buttondesignsmall">Delete</button>
		</div>
		<br>
	</c:forEach>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>