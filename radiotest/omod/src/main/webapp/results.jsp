<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var getExamsPath = modulePath + "/getExamList.htm";

function getExams(){
	console.log($j("#trans").serialize());
	$j.post(getExamsPath, $j("#trans").serialize(), function(data){
		var $list = $j("div#exams", $j(data));
		$j("#exams").replaceWith($list);
	});
}

function submit(id, count){
	$j("#examId").val(id);
	$j("#count").val(count);
	$j("#exam").submit();
}
//-->
</script>

Transactions
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
<form id="trans" action="getExams()">
	Transaction Number: <input type="text" name="transId">
	<br>
	<button type="button" onclick="getExams()">View Exams</button>
</form>
<br>

<form:form method="post" id="exam">
	<input type="hidden" id="examId" name="examId">
	<input type="hidden" id="count" name="count">
</form:form>

<div id="exams">

</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>