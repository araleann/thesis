<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var savePath = modulePath + "/resultsForm.htm";
var editPath = modulePath + "/editResultForm.htm";
var template = "${ template }";

function saveDraft(){
	$j.post(savePath, $j("#result").serialize(), function(data){
		alert("Saved as draft!");
	});
}

function save(){
	$j("#draft").val(false);
	$j.post(savePath, $j("#result").serialize(), function(data){
		alert("Saved!");
		var $results = $j("#results", $j(data));
		$j("#results").replaceWith($results);
	});
}

function edit(){
	if(confirm("Are you sure you want to edit?")){
		$j.post(editPath, $j("#result").serialize(), function(data){
			var $results = $j("#results", $j(data));
			$j("#results").replaceWith($results);
		});	
	}
}

function writeTemplate(){
	var isNegative = $j("#positive2").attr("checked");
	var $findings = $j("#findings");
	
	if (isNegative){
		$findings.val(template);
	} else {
		$findings.val("");
	}
}
//-->
</script>

<h2>Exam Result Details</h2>
<br>
<br>

Exam Number: ${ count }
<br>
<c:set var="exam" value="${ transExam.exam }" />
Exam Type: ${ exam.type.type }
<br>
Exam Name: ${ exam.name }
<br>
<br>

<div id="results">
	<c:choose>
		<c:when test="${ result.draft }">
			<form:form method="post" modelAttribute="result" id="result">
				<input type="hidden" name="examId" value="${ transExam.id }">
				<form:hidden path="id" />
				<form:hidden path="draft" id="draft" />
				Result: 
				<form:radiobutton path="positive" label="Positive" value="true" onclick="writeTemplate()" />
				<form:radiobutton path="positive" label="Negative" value="false" onclick="writeTemplate()" />
				<br>
				<spring:bind path="findings">
					<textarea id="findings" name="${ status.expression }">${ exam.type.template }</textarea>
				</spring:bind>
				<br>
				<br>
				<button type="button" onclick="save()">Save</button>
				<button type="button" onclick="saveDraft()">Save As Draft</button>
			</form:form>
		</c:when>
		<c:otherwise>
			<form id="result">
				<input type="hidden" name="examId" value="${ transExam.id }">
			</form>
			<c:choose>
				<c:when test="${ result.positive }">
					POSITIVE
				</c:when>
				<c:otherwise>
					NEGATIVE
				</c:otherwise>
			</c:choose>
			<br>
			<br>
			${ findings }
			<br>
			<br>
			<button type="button" onclick="edit()">Edit</button>
		</c:otherwise>
	</c:choose>
</div>
<br>
<br>
<button type="button">Print Results Form</button>

<%@ include file="/WEB-INF/template/footer.jsp"%>