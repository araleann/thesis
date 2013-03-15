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

<div id="details">
<p>
<c:if test="${ empty patient }">
	<c:set var="patient" value="${ trans.patient }" />
</c:if>
</p>

<c:choose>
	<c:when test="${ empty trans }">
		<h3>Transactions</h3>
		<table id="patient">
		<tr>
		<th>Transaction No</th>
		<th>Date</th>
		<th>Exams w/ Results</th>
		<th>Status</th>
		</tr>
		
		<c:forEach var="trans" items="${ transList }">
		<tr>
			<td> ${ trans.id } </td>
			<td> ${ trans.visitDate } </td>
			<td><center>${ trans.doneExams }/${ trans.numberOfExams }</center></td>
			<td>
			<c:choose>
				<c:when test="${ trans.pending }">
					PENDING
					<img border="0" src="images/pending.JPG"/>
				</c:when>
				<c:otherwise>
					DONE
					<img border="0" src="images/done.jpg">
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		</c:forEach>
		</table>
		<br>
		<h3>View/Update Results</h3>
		<form id="trans" action="javascript:getExams()">
			<label>Transaction Number:</label><input class="patientinputshort" type="text" name="transId">
			
			<button type="button" class="buttondesignmediumshort" onclick="getExams()">View</button>
		</form>
		<br>
	</c:when>
	<c:otherwise>
		<h3>Transaction</h3>		
		<c:set var="id" value="${ trans.id }" />
		<label>Transaction No.:</label> ${ id } <br>
		<label>Date:</label>${ trans.visitDate } <br>
		<label>Exams w/ Results:</label>${ trans.doneExams }/${ trans.numberOfExams } <br>
		<label>Status:</label>PENDING
	</c:otherwise>
</c:choose>

<form:form method="post" id="exam">
	<input type="hidden" id="examId" name="examId">
	<input type="hidden" id="count" name="count">
</form:form>

<h3>Exam List</h3>
<div id="exams">
	<c:if test="${ not empty trans }">
		<c:forEach var="transExam" items="${ trans.exams }" varStatus="status">
			<c:set var="c" value="${ status.count }" />
			<c:set var="id" value="${ transExam.id }" />
			<div id="exam${ id }">
				<label>Exam Number:</label>${ c }
				<button type="button" onclick="result(${ id }, ${ c })"></button>
				<br>
				<c:set var="e" value="${ transExam.exam }" />
				<label>Exam Type:<${ e.type.type }
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
</div>