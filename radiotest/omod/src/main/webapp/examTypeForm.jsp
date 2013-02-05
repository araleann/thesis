<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>

<script>
var modulePath = openmrsContextPath + "/module/radiotest";
var nullPath = modulePath + "/nullExamType.htm";

function loadExamTypes(){
	var $form = $j("#typeForm"); 
	$j.post($form.attr("action"), $form.serialize(), function(data){
		var $types = $j("#examTypes", $j(data));
		$j("#examTypes").replaceWith($types);
		$j("input[type=text]")
			.val("")
			.focus();
	});
}

function voidExamType(id){
	var obj = {
		eid : id,
		action : "void"
	}
	post(id, obj);
}

function deleteExamType(id){
	var obj = {
		eid : id,
		action : "delete"
	}
	if(confirm("Are you sure you want to delete?")){
		post(id, obj);
	}
}

function post(id, obj){
	var typeId = "#type" + id;
	$j.post(nullPath, obj, function(data){
		var $type = $j(typeId, $j(data));
		if($type.length){
			$j(typeId).replaceWith($type);
		} else {
			$j(typeId).remove();
		}
	})
}

$j(function(){
	GeneralUtils.addPlaceholderByName("type", "Exam Type");
});
</script>

<h2>Add Exam Type</h2>
<form:form method="post" modelAttribute="examType" id="typeForm">
	<form:label path="type">Exam Type</form:label>
	<form:input path="type" />
	<br>
	<button type="button" onclick="loadExamTypes()">Save</button>
</form:form>
<br>

<h2>Exam Types</h2>
<div id="examTypes">
	<c:forEach var="type" items="${ examTypes }">
		<br>
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
			<button type="button" onclick="voidExamType(${ id })"></button>
			<br>
			<button type="button" onclick="deleteExamType(${ id })">Delete</button>
		</div>
		<br>
	</c:forEach>
</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>