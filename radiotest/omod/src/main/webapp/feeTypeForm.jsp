<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var nullFeePath = modulePath + "/nullFeeType";

function voidFeeType(id){
	var obj = {
		tid : id,
		action : "void"
	};
	post(id, obj);
}

function deleteFeeType(id){
	var obj = {
		tid : id,
		action : "delete"
	};
	
	if (confirm("Are you sure you want to delete?")){
		post(id, obj);
	}
}

function post(id, obj){
	var divId = "#type" + id;
	var $divElem = $j(divId);
	$j.post(nullFeePath, obj, function(data){
		var $updatedDiv = $j(divId, data);
		if ($updatedDiv.length){
			$divElem.replaceWith($updatedDiv);
		} else {
			$divElem.remove();
		}
	});
}

//-->
</script>


<h2>Add Fee Type</h2>
<form:form method="post" modelAttribute="feeType">
	<form:input path="name" />
	<button type="button">Save</button>
</form:form>

<h2>Existing Fee Types</h2>
<div id="feeTypes">
	<c:forEach var="type" items="${ examTypes }">
		<c:set var="id" value="${ type.id }" />
		<div id="type${ id }">
			${ type.type } <br>
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