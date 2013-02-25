<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var getExamsPath = modulePath + "/getExamList.htm";
var borrowPath = modulePath + "/borrowResults.htm";
var resultPath = modulePath + "/results.htm";

function getExams(){
	console.log($j("#trans").serialize());
	$j.post(getExamsPath, $j("#trans").serialize(), function(data){
		var $list = $j("div#exams", $j(data));
		$j("#exams").replaceWith($list);
	});
}

function result(id, count){
	$j("#examId").val(id);
	$j("#count").val(count);
	$j("#exam")
		.attr("action", resultPath)
		.submit();
}

function borrow(id){
	var examId = "#exam" + id;
	$j.post(borrowPath, { examId: id }, function(data){
		var $updatedDiv = $j(examId, data);
		$j(examId).replaceWith($updatedDiv);
	});
}
//-->
</script>

<p>
${ patient.fullName } <br>
<c:set var ="a" value="${ patient.alias }" />
${ a.alias } <br>
${ a.category.category } <br>
</p>

<c:choose>
	<c:when test="${ empty trans }">
		<h2>Transactions</h2>
		
		<c:forEach var="trans" items="${ transList }">
			Transaction No: ${ trans.id }
			<br>
			Date: ${ trans.visitDate }
			<br>
			Number of Exams w/ Results: ${ trans.doneExams }/${ trans.numberOfExams }
			<br>
			Status:
			<c:choose>
				<c:when test="${ trans.pending }">
					PENDING
				</c:when>
				<c:otherwise>
					DONE
				</c:otherwise>
			</c:choose>
			<br>
			<br>
		</c:forEach>
		
		View/Update Results
		<br>
		<br>
		<form id="trans" action="javascript:getExams()">
			Transaction Number: <input type="text" name="transId">
			<br>
			<button type="button" onclick="getExams()">View Exams</button>
		</form>
		<br>
	</c:when>
	<c:otherwise>
		<h2>Transaction</h2>
		
		<c:set var="id" value="${ trans.id }" />
		Transaction No.: ${ id } <br>
		Date: ${ trans.visitDate } <br>
		Number of Exams w/ Results: ${ trans.doneExams }/${ trans.numberOfExams } <br>
		Status: PENDING
	</c:otherwise>
</c:choose>

<form:form method="post" id="exam">
	<input type="hidden" id="examId" name="examId">
	<input type="hidden" id="count" name="count">
</form:form>

<h2>Exam List</h2>
<div id="exams">
	<c:if test="${ not empty trans }">
		<c:forEach var="transExam" items="${ trans.exams }" varStatus="status">
			<c:set var="c" value="${ status.count }" />
			<c:set var="id" value="${ transExam.id }" />
			<div id="exam${ id }">
				Exam Number: ${ c }
				<button type="button" onclick="result(${ id }, ${ c })"></button>
				<br>
				<c:set var="e" value="${ transExam.exam }" />
				Exam Type: ${ e.type.type }
				<br>
				Exam Name: ${ e.name }
				<br>
				Borrowed: 
					<c:choose>
						<c:when test="${ transExam.borrowed }">
							YES
						</c:when>
						<c:otherwise>
							NO
						</c:otherwise>
					</c:choose>
				<button type="button" onclick="borrow(${ id })"></button>
			</div>
			<br>
		</c:forEach>
	</c:if>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>