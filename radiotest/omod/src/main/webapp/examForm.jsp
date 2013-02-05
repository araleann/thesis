<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>

<script>
var modulePath = openmrsContextPath + "/module/radiotest";
var savePath = modulePath + "/examForm.htm";
var loadPath = modulePath + "/loadExam.htm";
var refreshPath = modulePath + "/refreshExam.htm";
var nullPath = modulePath + "/nullExam.htm";

function loadExam(id){
	$j.post(loadPath, { examId : id }, function(data){
		var $form = $j("#examForm", $j(data));
		$j("#examForm").replaceWith($form);
		addPlaceholders();
	});
}

function addPlaceholders(){
	GeneralUtils.addPlaceholderByName("examFee", "Exam Fee");
	GeneralUtils.addPlaceholderByName("readingFee", "Reading Fee");
}

function saveExam(){
	$j("#form")
		.attr("action", savePath)
		.submit();
}

function clear(){
	$j.get(refreshPath, function(data){
		var $form = $j("#examForm", $j(data));
		$j("#examForm").replaceWith($form);
	});
}

function voidExam(id){
	var obj = {
		eid : id,
		action : "void"
	}
	post(id, obj);
}

function deleteExam(id){
	var obj = {
		eid : id,
		action : "delete"
	}
	if(confirm("Are you sure you want to delete?")){
		post(id, obj);
	}
}

function post(id, obj){
	var examId = "#exam" + id;
	$j.post(nullPath, obj, function(data){
		var $exam = $j(examId, $j(data));
		if($exam.length){
			$j(examId).replaceWith($exam);
		} else {
			$j(examId).remove();
		}
	})
}

$j(function(){
	addPlaceholders();
});
</script>
<h2>Exams</h2>
<div id="examList">
	<c:forEach var="exam" items="${ exams }">
		<br>
		<c:set var="id" value="${ exam.id }" />
		<div id="exam${ id }">
			Type : ${ exam.type.type } <br>
			Name: ${ exam.name }
			<button type="button" onclick="loadExam(${ id })"></button>
			<br>
			Voided:
			<c:choose>
				<c:when test="${ exam.voided }">
					YES
				</c:when>
				<c:otherwise>
					NO
				</c:otherwise>
			</c:choose>
			<button type="button" onclick="voidExam(${ id })"></button>
			<br>
			<button type="button" onclick="deleteExam(${ id })">Delete</button>
		</div>
		<br> 
	</c:forEach>
</div>
<br>
<br>
<div id="examForm">
	<form:form method="post" modelAttribute="examModel" id="form">
		<button type="button" onclick="clear()">Clear Form</button>
		<button type="button" onclick="saveExam()">Save Exam</button>
		<br>
		<br>
		<spring:nestedPath path="exam">
			<form:hidden path="id" />
			<form:label path="type">Exam Type</form:label>
			<form:select path="type">
				<option value="0"></option>
				<form:options items="${ types }" itemLabel="type" itemValue="id" />
			</form:select>
			<br>
			<br>
			<form:label path="name">Exam Name</form:label>
			<form:input path="name" />
			<br>
		</spring:nestedPath>
		<c:forEach var="cat" items="${ categories }" varStatus="status">
			<spring:nestedPath path="categoryFees[${ status.index }]">
				<br>
				<h4>${ cat.category }</h4>
				<spring:bind path="category">
					<input type="hidden" name="${ status.expression }" value="${ cat.id }">
				</spring:bind>
				<form:input path="examFee" />
				<form:input path="readingFee" />
				<br>
			</spring:nestedPath>
		</c:forEach>
	</form:form>
</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>