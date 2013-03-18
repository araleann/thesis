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

<h3>Payment</h3>
<table id="patient">
<tr>
<th> Transaction Number </th>
<th> Patient Name </th>
<th> Date of Visit </th>
<th> Status </th>
</tr>
<c:forEach var="t" items="${ payment }">
	<c:set var="id" value="${ t.id }" />
	<tr onclick="viewTransaction(${ id })">
	<td><center> ${ id } </center></td>
	<td> ${ t.patient.fullName } </td>
	<td> ${ t.visitDate } </td>
	<td> NOT PAID </td>
	</tr>
</c:forEach>
</table>
<br>

<h3>Result</h3>
<div id="result">
<table id="patient">
<tr>
<th> Transaction Number </th>
<th> Patient Name </th>
<th> Date of Visit </th>
<th> Exam Count </th>
<th> Borrowed </th>
<th> Status </th>
</tr>
<c:forEach var="t" items="${ results }">
	<c:set var="id" value="${ t.id }" />
	<tr onclick="viewExams(${ id })">
	<td><center> ${ id } </center></td>
	<td> ${ t.patient.fullName } </td>
	<td> ${ t.visitDate } </td>
	<td><center> ${ t.doneExams }/${ t.numberOfExams } </center></td>
	<td> 
	<c:choose>
		<c:when test="${ t.borrowed }">
			YES
		</c:when>
		<c:otherwise>
			NO
		</c:otherwise>
	</c:choose>
	</td>
	<td> PENDING </td>
	</tr>
</c:forEach>
</table>
</div>
<br>

<h3>Claiming</h3>
<div id="claim">
<table id="patient">
<tr>
<th> Transaction Number </th>
<th> Patient Name </th>
<th> Date of Visit </th>
<th> Status </th>
</tr>
<c:forEach var="t" items="${ claim }">
	<c:set var="id" value="${ t.id }" />
	<tr onclick="claim(${ id })">
	<td><center> ${ id } </center></td>
	<td> ${ t.patient.fullName } </td>
	<td> ${ t.visitDate } </td>
	<td> NOT CLAIMED </td>
</c:forEach>
</table>
</div>
<br>

</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>