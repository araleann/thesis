<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var getExamsPath = modulePath + "/getExamList.htm";
var resultsPath = modulePath + "/results.htm";
var borrowPath = modulePath + "/borrowResults.htm";

function getExams(){
	$j.post(getExamsPath, $j("#trans").serialize(), function(data){
		var $list = $j("div#exams", $j(data));
		$j("#exams").replaceWith($list);
	});
}

function result(id, count){
	$j("#examId").val(id);
	$j("#count").val(count);
	$j("#exam")
		.attr("action", resultsPath)
		.submit();
}

function borrow(id){
	$j("#examId").val(id);
	$j.post(borrowPath, $j("#exam").serialize(), function(data){
		var $list = $j("div#exams", $j(data));
		$j("#exams").replaceWith($list);
	});
}

$j(function(){
	var type = $j("#transId").attr("type");
	if(type == "hidden"){
		getExams();
	}
});
//-->
</script>

<c:set var="id" value="${ trans.id }" />

<c:choose>
	<c:when test="${ empty id }">
		<h2>Transactions</h2>
		<br>
		<br>
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
		<form id="trans">
			Transaction Number: <input type="text" id="transId" name="transId">
			<br>
			<button type="button" onclick="getExams()">View Exams</button>
		</form>
		<br>
	</c:when>
	<c:otherwise>
		<h2>Transaction</h2>
		<br>
		
		<p>
			Transaction No: ${ id } <br>
			Date: ${ trans.visitDate } <br>
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
		</p>
		<form id="trans">
			<input type="hidden" id="transId" name="transId" value="${ id }">
		</form>
		<h2>Exams</h2>
		<br>
		<br>
	</c:otherwise>
</c:choose>

<form:form method="post" id="exam">
	<input type="hidden" id="examId" name="examId">
	<input type="hidden" id="count" name="count">
</form:form>

<div id="exams">

</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>