<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>
<%@ include file="template/tables.jsp" %>
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/transaction.js" />

<openmrs:require privilege="Authenticated" otherwise="/login.htm" redirect="/module/radiotest/home.htm" />

<script type="text/javascript">
$j(function(){
	$j("#payment").dataTable();
    $j("#result").dataTable();
    $j("#claim").dataTable();
    $j("#expired").dataTable();
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
<div id="notif">
	<c:forEach var="i" items="${ items }">
		<c:choose>
			<c:when test="${ i.atLimit }">
				<div class="notif">
					<div id="tab">
					<b> WARNING! </b> ${ i.name }  is at threshold.
					</div>
				</div>
			</c:when>
		</c:choose>
	</c:forEach>
</div>
<h2>Pending</h2>

<h3>Payment</h3>
<table id="payment" class="tbldesign">
	<thead>
		<tr>
			<th> Transaction Number </th>
			<th> Patient Name </th>
			<th> Date of Visit </th>
			<th> Status </th>
			<th> Void </th>
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
			<td> <button type="button" class="void" onclick="voidTransaction(${ id })">Void</button> </td>
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
			<th> Void </th>
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
				<td> <button type="button" class="void" onclick="voidTransaction(${ id })">Void</button> </td>
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
			<th> Void </th>
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
			<td> <button type="button" class="void" onclick="voidTransaction(${ id })">Void</button> </td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
<br>
<br>
<hr>
<div id="expiredDiv">
<h3>Expired</h3>
<table id="expired" class="tbldesign">
	<thead>
		<th> Transaction Number </th>
		<th> Patient Name </th>
		<th> Date of Visit </th>
		<th> Status </th>
		<th> Void </th>
	</thead>
	<tbody>
		<c:forEach var="t" items="${ expired }">
			<c:set var="id" value="${ t.id }" />
			<c:set var="type" value="${ t.paid? ( t.pending? 'PENDING' : 'NOT CLAIMED' ) : 'NOT PAID' }" />
			<tr>
				<td><center> ${ id } </center></td>
				<td> ${ t.patient.fullName } </td>
				<td> ${ t.visitDate } </td>
				<td> ${ type } </td>
				<td> <button type="button" class="void" onclick="voidTransaction(${ id })">Void</button> </td>
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