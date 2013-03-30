<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/tables.jsp" %>
<openmrs:htmlInclude file="/moduleResources/radiotest/GeneralUtils.js" />

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var viewPath = modulePath + "/viewTransaction.htm";

function viewTransaction(id){
	$j("#transId").val(id);
	$j("#transaction")
		.attr("action", viewPath)
		.submit();
}
function createTransaction(){
	GeneralUtils.redirect("transExamForm.htm");
}
function load_transaction(){
	GeneralUtils.redirect("transactions.htm");
}
function load_profile(){
	GeneralUtils.redirect("patientProfile.htm");
}
function load_results(){
	GeneralUtils.redirect("results.htm");
}
$(document).ready( function () {
    $j("#tran").dataTable();
});
//-->
</script>
<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<c:set var="p" value="${ patient }" />
<fieldset>
<legend><h2>${ p.fullName }</h2></legend>
	<jsp:include page="/WEB-INF/view/topnav.jsp"/>
<div id="details">
<p>
<form id="transaction" method="post">
	<input type="hidden" name="transId" id="transId">
</form>
<p>
	<button type="button" class="buttondesignmedium" onclick="createTransaction()">New Transaction</button>
</p>
<h3>Transactions</h3>
<table id="tran" class="tbldesign">
	<thead>
		<tr>
			<th>Transaction Number</th>
			<th>Date</th>
			<th>No. of Exams</th>
			<th>Status</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="trans" items="${ transList }">
		<c:set var="id" value="${ trans.id }" />
		<tr onclick="viewTransaction(${ id })">
			<td>${ id }</td>
			<td>${ trans.visitDate }</td>
			<td>${ trans.numberOfExams }</td>
			<td>
			<c:choose>
				<c:when test="${ trans.paid }">
					PAID
				</c:when>
				<c:otherwise>
					NOT PAID
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<br><br>
</div>

</div>
</div>
<div class="col2">
<div class="sideholder">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
</div>
</div>
</div>
</div>