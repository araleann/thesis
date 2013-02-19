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
		<script type="text/javascript">
			$j(function(){
				getExams();
			});
		</script>
		<h2>Transaction</h2>
		
		<c:set var="id" value="${ trans.id }" />
		Transaction No.: ${ id } <br>
		Date: ${ trans.visitDate } <br>
		Number of Exams w/ Results: ${ trans.doneExams }/${ trans.numberOfExams } <br>
		Status: PENDING
		
		<form id="trans">
			<input type="hidden" name="transId" value="${ id }">
		</form>
	</c:otherwise>
</c:choose>

<form:form method="post" id="exam">
	<input type="hidden" id="examId" name="examId">
	<input type="hidden" id="count" name="count">
</form:form>

<h2>Exam List</h2>
<div id="exams">

</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>