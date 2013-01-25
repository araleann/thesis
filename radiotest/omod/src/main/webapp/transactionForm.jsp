<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var saveNotePath = modulePath + "/saveNote.htm #note"

function saveNote(){
	var $noteDiv = $j("<div></div>");
	$noteDiv.load(saveNotePath, $j("#transaction").serialize(), function(data){
		console.log($j($noteDiv));	
	});
}
//-->
</script>

Transaction Summary
<br>
<br>

Total Number of Exams: ${ transaction.numberOfExams }
<br>
<br>
Payment Details:
<br>
<br>
<c:forEach var="transExam" items="${ transaction.exams }" varStatus="status">
	Exam Number: ${ status.count }
	<br>
	
	Exam Type: ${ transExam.exam.type.type }
	<br>
	
	Exam Name: ${ transExam.exam.name }
	<br>
	
	Exam Fee: ${ transExam.examFee }
	<br>
	
	Reading Fee: ${ transExam.readingFee }
	<br>
	<br>
</c:forEach>
<br>

Total Amount Due: ${ transaction.total }
<br>
<br>

<button type="button">Print Assessment Form</button>
<br>
<br>

<div id="existingNotes">
	<c:forEach var="note" items="${ transaction.notes }">
		<c:choose>
			<c:when test="${ note.type.id == 0 }">
				${ note.description }
			</c:when>
			<c:otherwise>
				${ note.type.name }
			</c:otherwise>
		</c:choose>
		<br>
		${ note.note }
		<br>
		${ note.date }
		<br>
		<br>
	</c:forEach>
</div>
<br>

<form:form method="post" modelAttribute="transModel" id="transaction">
	<button type="button" id="save" onclick="saveNote()">Save Note</button>
	<br>
	<spring:bind path="transaction">
		<input type="hidden" name="${ status.expression }" value="${ transaction.id }">
	</spring:bind>
	<spring:nestedPath path="note">
		<form:select path="type">
			<form:options items="${ transModel.noteTypes }" itemLabel="name" itemValue="id" />
			<option value="0">Others</option>
		</form:select>
		<spring:bind path="description">
			<input type="text" name="${ status.expression }" hidden>
		</spring:bind>
		<br>
		<form:textarea path="note" />
	</spring:nestedPath>
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>