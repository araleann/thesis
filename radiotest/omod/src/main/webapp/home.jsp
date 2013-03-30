<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>
<%@ include file="template/tables.jsp" %>
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/transaction.js" />

<script type="text/javascript">
$j(function(){
	$j("#payment").dataTable();
    $j("#result").dataTable();
    $j("#claim").dataTable();
});
//-->
</script>

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<form:form id="transaction">
	<input type="hidden" name="transId" id="transId">
</form:form>

<h2>Pending</h2>

<h3>Payment</h3>
<table id="payment" class="tbldesign">
	<thead>
		<tr>
			<th> Transaction Number </th>
			<th> Patient Name </th>
			<th> Date of Visit </th>
			<th> Status </th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="t" items="${ payment }">
		<c:set var="id" value="${ t.id }" />
		<tr onclick="viewTransaction(${ id })">
			<td><center> ${ id } </center></td>
			<td> ${ t.patient.fullName } </td>
			<td> ${ t.visitDate } </td>
			<td> NOT PAID </td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<br>
<br>
<hr>
<h3>Result</h3>
<table id="result" class="tbldesign">
	<thead>
		<tr>
			<th> Transaction Number </th>
			<th> Patient Name </th>
			<th> Date of Visit </th>
			<th> Exam Count </th>
			<th> Borrowed </th>
			<th> Status </th>
		</tr>
	</thead>
	<tbody>
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
	</tbody>
</table>
<br>
<br>
<hr>
<div id="claimDiv">
<h3>Claiming</h3>
<table id="claim" class="tbldesign">
	<thead>
		<tr>
			<th> Transaction Number </th>
			<th> Patient Name </th>
			<th> Date of Visit </th>
			<th> Status </th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="t" items="${ claim }">
		<c:set var="id" value="${ t.id }" />
		<tr onclick="claim(${ id })">
			<td><center> ${ id } </center></td>
			<td> ${ t.patient.fullName } </td>
			<td> ${ t.visitDate } </td>
			<td> NOT CLAIMED </td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
<br>
</div>
</div>
<div class="col2">
<div class="sideholder">
		<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
</div>
</div>
</div>
</div>