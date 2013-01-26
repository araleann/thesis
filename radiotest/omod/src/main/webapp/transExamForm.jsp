<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script>
var modulePath = openmrsContextPath + "/module/radiotest";
var addExamPath = modulePath + "/addExam.htm select:first-child";
var getExamsPath = modulePath + "/getExams.htm select:last-child";
var examIndex = 0;

function addNewExam(){
	examIndex++;
	
	var $examDiv = $j("<div id='exams" + examIndex + "'></div>");
	var $typeDiv = $j("<div id='type'></div>");
	var postObj = { index : examIndex };
	
	$typeDiv.load(addExamPath, postObj, function(data){
		$typeDiv.children('select').change(getExamsEvent);
		$typeDiv.appendTo($examDiv);
		$examDiv.appendTo("#transaction");
		updateDeleteButton();
	});
}

function deleteExam(){
	$j("div#exams" + examIndex).remove();
	examIndex--;
	
	updateDeleteButton();
}

function getExamsEvent(){
	var currIndex = $j(this).attr("name").match(/\d+/)[0];
	var $examDiv = $j("<div id='exam'></div>");
	var postObj = {
		index : currIndex,
		type : $j(this).val()
	};
	
	$examDiv.load(getExamsPath, postObj, function(data){
		var $exam = $j("div#exams" + currIndex);
		var $existing = $j("div#exam", $exam);
		if($existing.size()){
			$existing.replaceWith($examDiv);
		} else {
			$exam.append($examDiv);
		}
	});
}

function updateDeleteButton(){
	var $delete = $j("button#delete");
	var disabledState = examIndex == 0;
	var isDisabled = $delete.attr("disabled");
	
	if(disabledState != isDisabled){
		if(disabledState){
			$delete.attr("disabled", "disabled");
		} else {
			$delete.removeAttr("disabled");
		}
	}
}

$j(function(){
	$j("select").change(getExamsEvent);
});
</script>

<form:form method="post" modelAttribute="transModel" id="transaction">
	<button type="button" id="add" onclick="addNewExam()">Add Exam</button>
	<button type="button" id="delete" onclick="deleteExam()" disabled>Delete Exam</button>
	<button type="submit">Done</button>
	<spring:bind path="transaction.patient">
		<input type="hidden" name="${ status.expression }" value="${ id }">
	</spring:bind>
	<spring:nestedPath path="exams[0]">
		<div id="exams0">
			<div id="type">
				<form:select path="exam.type">
					<option value="0"></option>
					<form:options items="${ examTypes }" itemLabel="type" itemValue="id" />
				</form:select>
			</div>
		</div>
	</spring:nestedPath>
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>