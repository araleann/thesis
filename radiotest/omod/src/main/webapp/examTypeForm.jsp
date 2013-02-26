<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>

<script>
var modulePath = openmrsContextPath + "/module/radiotest";
var nullPath = modulePath + "/nullExamType.htm";
var editPath = modulePath + "/editExamType.htm";

function loadExamTypes(){
	var $form = $j("#typeForm");
	
	$j.post($form.attr("action"), $form.serialize(), function(data){
		var $types = $j("#examTypes", $j(data));
		$j("#examTypes").replaceWith($types);
		$j("#template").val("");
		$j("input[type=text]")
			.val("")
			.focus();
	});
}

function editExamType(id){
	$j.post(editPath, { eid : id }, function(data){
		var formId = "#typeForm";
		$j(formId).replaceWith($j(formId, data));
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
	var placeholders = {
		type : "Exam Type",
		template : "Template for Negative Results"
	}
	GeneralUtils.addPlaceholderByName(placeholders);
});
</script>

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
			<!-- Column 1 start -->
			<!-- Column 1 end -->
<br>
<h2>Add Exam Type</h2>
<form:form method="post" modelAttribute="examType" id="typeForm">
	<form:input path="type" cssClass="patientinput" />
	<br>
	<br>
	<form:textarea path="template" />
	<br>
	<button type="button" onclick="loadExamTypes()" class="buttondesign">Save</button>
</form:form>
<br>

<h2>Exam Types</h2>
<div id="examTypes">
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
			<button type="button" onclick="voidExamType(${ id })" class="buttondesignvoid">Void</button>
			<br>
			<c:if test="${ not empty type.template }">
				<div id="template${ id }" class="template">
					${ type.template }
				</div>
				<br>
			</c:if>
			<button type="button" onclick="deleteExamType(${ id })" class="buttondesignsmall">Delete</button>
			<button type="button" onclick="editExamType(${ id })" class="buttondesignsmall">Edit</button>
		</div>
		<br>
	</c:forEach>
</div>
</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>

<%@ include file="/WEB-INF/template/footer.jsp"%>