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
<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
<br>
<h2>Exams</h2>
<div id="examForm">
	<form:form method="post" modelAttribute="examModel" id="form">
		<spring:nestedPath path="exam">
			<form:hidden path="id" />
			<label>Exam Type</label>
			<form:select path="type" cssClass="patientinput">
				<option value="0"></option>
				<form:options items="${ types }" itemLabel="type" itemValue="id" />
			</form:select>
			<br>
			<br>
			<label>Exam Name</label>
			<form:input path="name" cssClass="patientinput"/>
			<br>
		</spring:nestedPath>
		<c:forEach var="cat" items="${ categories }" varStatus="status">
			<spring:nestedPath path="categoryFees[${ status.index }]">
				<br>
				<h4>${ cat.category }</h4>
				<spring:bind path="category">
					<input type="hidden" name="${ status.expression }" value="${ cat.id }">
				</spring:bind>
				<form:input path="examFee" cssClass="patientinput" />
				<form:input path="readingFee" cssClass="patientinput" />
				<br>
			</spring:nestedPath>
		</c:forEach>
	</form:form>
	<br>
	<button type="button" onclick="clear()" class="buttondesign">Clear Form</button>
	<button type="button" onclick="saveExam()" class="buttondesign">Save Exam</button>
</div>
<br>
<div id="examList">
	<c:forEach var="exam" items="${ exams }">
		<br>
		<c:set var="id" value="${ exam.id }" />
		<div id="exam${ id }">
			Type : ${ exam.type.type } <br>
			Name: ${ exam.name }
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
			<button type="button" onclick="voidExam(${ id })" class="buttondesignvoid">Void</button>
			<br><br>
			<button type="button" onclick="deleteExam(${ id })" class="buttondesignsmall">Delete</button>
			<button type="button" onclick="loadExam(${ id })" class="buttondesignsmall">Edit</button>
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