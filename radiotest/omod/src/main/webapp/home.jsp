<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var transactionPath = modulePath + "/viewTransaction.htm";
var resultPath = modulePath + "/viewExams.htm";
var claimPath = modulePath + "/claimResults.htm";

function viewTransaction(id){
	$j("#transId").val(id);
	$j("#transaction")
		.attr("action", transactionPath)
		.submit();
}

function viewExams(id){
	$j("#transId").val(id);
	$j("#transaction")
		.attr("action", resultPath)
		.submit();
}

function claim(id){
	$j("#transId").val(id);
	$j.post(claimPath, $j("#transaction").serialize(), function(data){
		$unclaimed = $j("#claim", $j(data));
		$j("#claim").replaceWith($unclaimed);
	});
}
//-->
</script>

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
<br>
<form:form id="transaction">
	<input type="hidden" name="transId" id="transId">
</form:form>

<h2>Pending</h2>
<br>

<p>
	<h2>Payment</h2>
	<br>
	
	<c:forEach var="t" items="${ payment }">
		<br>
		<c:set var="id" value="${ t.id }" />
		Transaction Number: ${ id }
		<button type="button" onclick="viewTransaction(${ id })"></button><br>
		Patient Name: ${ t.patient.fullName } <br>
		Date of Visit: ${ t.visitDate } <br>
		Status: NOT PAID
		<br>
	</c:forEach>
</p>

<p>
	<h2>Result</h2>
	<br>
	
	<div id="result">
	<c:forEach var="t" items="${ results }">
		<br>
		<c:set var="id" value="${ t.id }" />
		Transaction Number: ${ id }
		<button type="button" onclick="viewExams(${ id })"></button><br>
		Patient Name: ${ t.patient.fullName } <br>
		Timestamp: ${ t.visitDate } <br>
		Count: ${ t.doneExams }/${ t.numberOfExams } <br>
		Borrowed: 
		<c:choose>
			<c:when test="${ t.borrowed }">
				YES
			</c:when>
			<c:otherwise>
				NO
			</c:otherwise>
		</c:choose>
		<br>
		Status: PENDING
		<br>
	</c:forEach>
	</div>
</p>

<p>
	<h2>Claiming</h2>
	<br>
	
	<div id="claim">
	<c:forEach var="t" items="${ claim }">
		<br>
		<c:set var="id" value="${ t.id }" />
		Transaction Number: ${ id }
		<button type="button" onclick="claim(${ id })"></button><br>
		Patient Name: ${ t.patient.fullName } <br>
		Timestamp: ${ t.visitDate } <br>
		Status: NOT CLAIMED
		<br>
	</c:forEach>
	</div>
</p>
</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>

<%@ include file="/WEB-INF/template/footer.jsp"%>